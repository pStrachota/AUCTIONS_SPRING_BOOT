package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.AuctionType;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemStatus;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.AuctionService;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.specArgResAnnotation.AuctionSpec;

@RestController
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @Operation(summary = "Create new auction")
    @PostMapping("/auctions")
    public ResponseEntity<Auction> createAuction(
            @RequestBody @Valid AuctionRequest auctionRequest) {
        return new ResponseEntity<>(auctionService.createAuction(auctionRequest),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Get all auctions")
    @GetMapping("/auctions")
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
            @RequestParam(value = "sortBy", defaultValue = "currentPrice", required = false)
                    String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false)
                    String sortDir) {
        return auctionService.getAllAuctions(auctionSpec, pageNo, sortBy, sortDir);
    }


    @Operation(summary = "Update auction info by id")
    @PutMapping("/auctions/{id}")
    public ResponseEntity<Auction> updateAuction(@RequestBody @Valid AuctionUpdate auctionUpdate,
                                                 @PathVariable Long id) {
        return new ResponseEntity<>(auctionService.updateAuction(id, auctionUpdate),
                HttpStatus.OK);
    }

    @Operation(summary = "Delete auction by id")
    @DeleteMapping("/auctions/{id}")
    public ResponseEntity<Auction> deleteAuction(@PathVariable Long id) {
        return new ResponseEntity<>(auctionService.deleteAuction(id), HttpStatus.OK);
    }

}
