package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;

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
            @And({
                    @Spec(path = "description", params = "descr", spec = Like.class),
                    @Spec(path = "itemCategory", spec = EqualIgnoreCase.class),
                    @Spec(path = "currentPrice", params = {"priceFrom", "priceTo"},
                            spec = Between.class),
                    @Spec(path = "auctionType", spec = EqualIgnoreCase.class),
                    @Spec(path = "itemStatus", spec = EqualIgnoreCase.class)
            }) Specification<Auction> spec,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "sortBy", defaultValue = "currentPrice", required = false)
                    String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false)
                    String sortDir) {
        return auctionService.getAllAuctions(spec, pageNo, sortBy, sortDir);
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
