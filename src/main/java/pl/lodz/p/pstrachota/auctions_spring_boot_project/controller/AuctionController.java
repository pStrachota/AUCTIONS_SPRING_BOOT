package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import lombok.RequiredArgsConstructor;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.AuctionService;

@RestController
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping("/auctions")
    public ResponseEntity<Auction> createAuction(@RequestBody AuctionRequest auctionRequest) {
        return new ResponseEntity<Auction>(auctionService.createAuction(auctionRequest),
                HttpStatus.CREATED);
    }

    @GetMapping("/auctions")
    public ResponseEntity<List<Auction>> getAllAuctions() {
        return new ResponseEntity<>(
                auctionService.getAllAuctions(), HttpStatus.OK);
    }

    @PutMapping("/auctions/{id}")
    public ResponseEntity<Auction> updateAuction(@RequestBody AuctionUpdate auctionUpdate,
                                                 @RequestParam Long id) {
        return new ResponseEntity<Auction>(auctionService.updateAuction(id, auctionUpdate),
                HttpStatus.OK);
    }

    @DeleteMapping("/auctions/{id}")
    public ResponseEntity<Auction> deleteAuction(@RequestParam Long id) {
        return new ResponseEntity<Auction>(auctionService.deleteAuction(id), HttpStatus.OK);
    }

}
