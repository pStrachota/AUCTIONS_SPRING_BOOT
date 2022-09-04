package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.impl;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectDateException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectOperationException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectPriceException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.NotFoundException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.AuctionRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.BidRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.AuctionService;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper.AuctionDtoMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class AuctionServiceImpl implements AuctionService {

    private static final int PAGE_SIZE = 20;
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;

    public Auction createAuction(AuctionRequest auctionRequest) {

        Auction auction = AuctionDtoMapper.mapAuctionRequestToAuction(auctionRequest);

        if (auction.getAuctionEndTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectDateException("Auction end time cannot be in the past");
        }

        if (auction.getAuctionEndTime().minusDays(1).compareTo(auction.getAuctionStartTime()) < 0) {
            throw new IncorrectDateException(
                    "Auction end time must be at least one day after start time");
        }

        if (auction.getCurrentPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IncorrectPriceException("Min price cannot be negative");
        }

        return auctionRepository.save(auction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Auction> getAllAuctions(int pageNo, String sortBy,
                                        String sortDir) {
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, sortDir.equalsIgnoreCase(
                Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending());
        Page<Auction> auctions = auctionRepository.findAll(pageable);

        return auctions.getContent();
    }

    @Override
    public Auction deleteAuction(Long id) {
        Auction auctionToDelete = auctionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Offer with id " + id + " not found"));

        if (auctionToDelete.getAuctionEndTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectDateException("Cannot delete auction that has already ended");
        }
        long bidsForAuctionCount = bidRepository.findByRelatedOfferId(id).size();

        if (bidsForAuctionCount != 0) {
            throw new IncorrectOperationException("Cannot delete auction with bids");
        }
        auctionRepository.deleteById(id);
        return auctionToDelete;
    }

    @Override
    public Auction updateAuction(Long id, AuctionUpdate auctionUpdate) {
        Auction auctionToUpdate = auctionRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Auction with id " + id + " not found"));

        Optional.ofNullable(auctionUpdate.getDescription())
                .ifPresent(auctionToUpdate::setDescription);
        Optional.ofNullable(auctionUpdate.getEmail()).ifPresent(auctionToUpdate::setEmail);

        return auctionRepository.save(auctionToUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Auction> findByDescriptionContains(String description, int page) {
        return auctionRepository
                .findByDescriptionContains(description, PageRequest.of(page, PAGE_SIZE));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Auction> findByItemCategory(ItemCategory itemCategory, int page) {
        return auctionRepository
                .findByItemCategory(itemCategory, PageRequest.of(page, PAGE_SIZE));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Auction> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, int page,
                                            String sortDir) {
        return auctionRepository.findAllByCurrentPriceBetween(minPrice, maxPrice,
                PageRequest.of(page, PAGE_SIZE, sortDir.equalsIgnoreCase(
                        Sort.Direction.ASC.name()) ? Sort.by("currentPrice").ascending() :
                        Sort.by("price").descending()));
    }

}

