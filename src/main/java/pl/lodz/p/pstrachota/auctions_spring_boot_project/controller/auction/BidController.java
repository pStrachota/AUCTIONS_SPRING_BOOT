package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller.auction;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.BidRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Bid;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.security.CurrentUser;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.BidService;

@RestController
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    @Operation(summary = "Create new bid")
    @PostMapping("/{id}/bids")
    public ResponseEntity<Bid> createBid(@CurrentUser
                                                 UserDetails userDetails, @PathVariable Long id,
                                         @RequestBody @Valid BidRequest bidRequest) {

        return new ResponseEntity<>(bidService.createBid(bidRequest, id, userDetails),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Delete bid by id")
    @DeleteMapping("/{id}/bids/{bidId}")
    public ResponseEntity<Bid> deleteBid(@CurrentUser
                                                 UserDetails userDetails, @PathVariable Long id,
                                         @PathVariable Long bidId) {

        return new ResponseEntity<>(bidService.deleteBid(id, bidId, userDetails), HttpStatus.OK);
    }

    @GetMapping("/{auctionId}/bids")
    public ResponseEntity<List<Bid>> getBidsForAuction(@PathVariable Long auctionId) {
        return new ResponseEntity<>(bidService.getBidsForAuction(auctionId), HttpStatus.OK);
    }

}
