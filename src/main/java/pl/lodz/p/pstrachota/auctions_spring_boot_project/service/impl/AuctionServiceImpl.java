package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.impl;

import java.util.List;
import java.util.Optional;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import org.springframework.stereotype.Service;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.AuctionRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.AuctionService;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper.AuctionDtoMapper;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;

    public Auction createAuction(AuctionRequest auctionRequest) {

        Auction auction = AuctionDtoMapper.mapAuctionRequestToAuction(auctionRequest);

        if (auction.getAuctionEndTime().isBefore(LocalDateTime.now())) {
            throw new InvalidParameterException("Auction end time cannot be in the past");
        }

        if (auction.getAuctionEndTime().minusDays(1).compareTo(auction.getAuctionStartTime()) < 0) {
            throw new InvalidParameterException(
                    "Auction end time must be at least one day after start time");
        }

        if (auction.getCurrentPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidParameterException("Min price cannot be negative");
        }

        return auctionRepository.save(auction);
    }

    @Override
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    @Override
    public Auction deleteAuction(Long id) {
        Auction auctionToDelete = auctionRepository.findById(id).orElseThrow(
                () -> new InvalidParameterException("Offer with id " + id + " not found"));

        if (auctionToDelete.getAuctionEndTime().isBefore(LocalDateTime.now())) {
            throw new InvalidParameterException("Cannot delete auction that has already ended");
        }

        auctionRepository.deleteById(id);
        return auctionToDelete;
    }

    @Override
    public Auction updateAuction(Long id, AuctionUpdate auctionUpdate) {
        Auction auctionToUpdate = auctionRepository.findById(id).orElseThrow(
                () -> new InvalidParameterException("Auction with id " + id + " not found"));

        Optional.ofNullable(auctionUpdate.getDescription())
                .ifPresent(auctionToUpdate::setDescription);
        Optional.ofNullable(auctionUpdate.getEmail()).ifPresent(auctionToUpdate::setEmail);

        return auctionRepository.save(auctionToUpdate);
    }

}

