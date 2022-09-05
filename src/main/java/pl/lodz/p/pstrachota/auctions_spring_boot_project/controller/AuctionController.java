package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
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
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.AuctionService;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.specArgResAnnotation.AuctionSpec;

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

    @GetMapping("/auctions")
    public List<Auction> getAllAuctions(
            @RequestParam(value = "descr", required = false) String description,
            @RequestParam(value = "itemCategory", required = false) String itemCategory,
            @RequestParam(value = "priceFrom", required = false) Double priceFrom,
            @RequestParam(value = "priceTo", required = false) Double priceTo,
            @RequestParam(value = "auctionType", required = false) String auctionType,
            @RequestParam(value = "itemStatus", required = false) String itemStatus,
            @Parameter(hidden = true) AuctionSpec auctionSpec,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "sortBy", defaultValue = "currentPrice", required = false)
                    String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false)
                    String sortDir) {
        return auctionService.getAllAuctions(auctionSpec, pageNo, sortBy, sortDir);
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
