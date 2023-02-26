package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectDateException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectOperationException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectPriceException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.NotFoundException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.WrongAuctionOwnerException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Auction;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.user.User;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.AuctionRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.BidRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.UserRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.AuctionService;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper.AuctionDtoMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class AuctionServiceImpl implements AuctionService {

    private static final int PAGE_SIZE = 20;
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;
    private final UserRepository userRepository;

    public Auction createAuction(AuctionRequest auctionRequest, UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new NotFoundException("User not found"));

        Auction auction = AuctionDtoMapper.mapAuctionRequestToAuction(auctionRequest);
        auction.setUser(user);

        if (auction.getAuctionEndTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectDateException("Auction end time cannot be in the past");
        }

        if (auction.getAuctionEndTime().minusDays(1).compareTo(auction.getAuctionStartTime()) < 0) {
            throw new IncorrectDateException(
                    "Auction end time must be at least one day after start time");
        }

        if (auction.getStartingPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IncorrectPriceException("Min price cannot be negative");
        }

        return auctionRepository.save(auction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Auction> getAllAuctions(Specification<Auction> spec, int pageNo, String sortBy,
                                        String sortDir) {
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, sortDir.equalsIgnoreCase(
                Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending());
        Page<Auction> auctions = auctionRepository.findAll(spec, pageable);

        return auctions.getContent();
    }

    @Override
    @CacheEvict(value = "auctions", key = "#id")
    public void deleteAuction(Long id, UserDetails userDetails) {
        Auction auctionToDelete = auctionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Offer with id " + id + " not found"));

        if (!auctionToDelete.getUser().getUsername().equals(userDetails.getUsername())) {
            throw new WrongAuctionOwnerException("Cannot delete auction that is not yours");
        }

        if (auctionToDelete.getAuctionEndTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectDateException("Cannot delete auction that has already ended");
        }
        int bidsForAuctionCount = bidRepository.findByBiddingAuctionId(id).size();

        if (bidsForAuctionCount != 0) {
            throw new IncorrectOperationException("Cannot delete auction with bids");
        }
        auctionRepository.deleteById(id);
    }

    @Override
    @CachePut(value = "auctions", key = "#id")
    public Auction updateAuction(Long id, AuctionUpdate auctionUpdate, UserDetails userDetails) {
        Auction auctionToUpdate = auctionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Auction with id " + id + " not found"));

        if (!auctionToUpdate.getUser().getUsername().equals(userDetails.getUsername())) {
            throw new IncorrectOperationException("Cannot update auction that is not yours");
        }

        Optional.ofNullable(auctionUpdate.getDescription())
                .ifPresent(auctionToUpdate::setDescription);

        return auctionRepository.save(auctionToUpdate);
    }

    @Override
    @Cacheable(value = "auctions", key = "#id")
    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Auction with id " + id + " not found"));
    }

}

