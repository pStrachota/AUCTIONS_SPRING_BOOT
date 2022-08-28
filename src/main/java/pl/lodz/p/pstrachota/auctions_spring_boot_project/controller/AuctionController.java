package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;

import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import lombok.RequiredArgsConstructor;
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


}
