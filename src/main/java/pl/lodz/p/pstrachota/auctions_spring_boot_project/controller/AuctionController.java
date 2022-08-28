package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.AuctionService;

@RestController
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("/auctions")
    public ResponseEntity<Auction> createAuction(
            @RequestBody @Valid AuctionRequest auctionRequest) {
        return new ResponseEntity<Auction>(auctionService.createAuction(auctionRequest),
                HttpStatus.CREATED);
    }

    @GetMapping("/auctions-description")
    public ResponseEntity<List<Auction>> findByDescriptionContaining(
            @RequestParam(required = true) String description,
            @RequestParam int page) {
        return new ResponseEntity<List<Auction>>(
                auctionService.findByDescriptionContains(description, page), HttpStatus.OK);
    }

    @GetMapping("/auctions-category")
    public ResponseEntity<List<Auction>> findByCategory(
            @RequestParam(required = true) ItemCategory itemCategory,
            @RequestParam int page) {
        return new ResponseEntity<List<Auction>>(
                auctionService.findByItemCategory(itemCategory, page), HttpStatus.OK);
    }

    @GetMapping("/auctions-price-between")
    public ResponseEntity<List<Auction>> findByPriceBetween(
            @RequestParam(required = false, defaultValue = "0") String minPrice,
            @RequestParam(required = false, defaultValue = "10000") String maxPrice,
            @RequestParam int page,
            @RequestParam(required = false, defaultValue = "asc") String sortDir) {
        return new ResponseEntity<List<Auction>>(
                auctionService
                        .findByPriceBetween(new BigDecimal(minPrice), new BigDecimal(maxPrice),
                                page, sortDir), HttpStatus.OK);
    }

    @GetMapping("/auctions")
    public ResponseEntity<List<Auction>> getAllAuctions(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "sortBy", defaultValue = "currentPrice", required = false)
                    String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false)
                    String sortDir) {
        return new ResponseEntity<>(
                auctionService.getAllAuctions(pageNo, sortBy, sortBy), HttpStatus.OK);
    }

    @PutMapping("/auctions/{id}")
    public ResponseEntity<Auction> updateAuction(@RequestBody @Valid AuctionUpdate auctionUpdate,
                                                 @RequestParam Long id) {
        return new ResponseEntity<Auction>(auctionService.updateAuction(id, auctionUpdate),
                HttpStatus.OK);
    }

    @DeleteMapping("/auctions/{id}")
    public ResponseEntity<Auction> deleteAuction(@RequestParam Long id) {
        return new ResponseEntity<Auction>(auctionService.deleteAuction(id), HttpStatus.OK);
    }

}
