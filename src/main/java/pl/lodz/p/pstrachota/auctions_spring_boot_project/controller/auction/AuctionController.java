package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller.auction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.BiddingRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.BuyNowRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Auction;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Bidding;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.BuyNow;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemStatus;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.security.CurrentUser;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.AuctionService;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.specArgResAnnotation.AuctionSpec;

@RestController
@RequestMapping("/auctions")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class AuctionController {

    private final AuctionService auctionService;

    @Operation(summary = "Create new buy now auction")
    @PostMapping("/buy-now")
    public ResponseEntity<BuyNow> createBuyNowAuction(@CurrentUser UserDetails userDetails,
                                                      @RequestBody
                                                      @Valid BuyNowRequest buyNowRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body((BuyNow) auctionService.createAuction(buyNowRequest, userDetails));
    }

    @Operation(summary = "Create new bidding auction")
    @PostMapping("/bidding")
    public ResponseEntity<Bidding> createBiddingAuction(@CurrentUser UserDetails userDetails,
                                                        @RequestBody
                                                        @Valid BiddingRequest biddingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body((Bidding) auctionService.createAuction(biddingRequest, userDetails));
    }

    @Operation(summary = "Get all auctions")
    @GetMapping()
    public List<Auction> getAllAuctions(
            @RequestParam(value = "descr", required = false) String description,
            @RequestParam(value = "itemCategory", required = false) ItemCategory itemCategory,
            @RequestParam(value = "priceFrom", required = false, defaultValue = "0")
                    Double priceFrom,
            @RequestParam(value = "priceTo", required = false, defaultValue = "100") Double priceTo,
            @RequestParam(value = "auctionType", required = false) AuctionType auctionType,
            @RequestParam(value = "itemStatus", required = false) ItemStatus itemStatus,
            @Parameter(hidden = true) AuctionSpec auctionSpec,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "sortBy", defaultValue = "startingPrice", required = false)
                    String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false)
                    String sortDir) {
        return auctionService.getAllAuctions(auctionSpec, pageNo, sortBy, sortDir);
    }

    @GetMapping("/{auctionId}")
    @Operation(summary = "Get auction by id")
    public ResponseEntity<Auction> getAuctionById(@PathVariable Long auctionId) {
        return new ResponseEntity<>(auctionService.getAuctionById(auctionId), HttpStatus.OK);
    }


    @Operation(summary = "Update auction info by id")
    @PutMapping("/{auctionId}")
    public ResponseEntity<Auction> updateAuction(@CurrentUser UserDetails userDetails,
                                                 @RequestBody @Valid AuctionUpdate auctionUpdate,
                                                 @PathVariable Long auctionId) {
        return new ResponseEntity<>(auctionService.updateAuction(auctionId, auctionUpdate, userDetails),
                HttpStatus.OK);
    }

    @Operation(summary = "Delete auction by id")
    @DeleteMapping("/{auctionId}")
    public ResponseEntity<String> deleteAuction(@CurrentUser UserDetails userDetails,
                                                 @PathVariable Long auctionId) {
        auctionService.deleteAuction(auctionId, userDetails);
        return ResponseEntity.ok("Auction deleted successfully!");
    }

    private enum AuctionType {

        BUY_NOW,
        BIDDING
    }

}


