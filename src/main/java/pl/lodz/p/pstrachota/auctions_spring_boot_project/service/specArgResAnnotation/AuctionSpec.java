package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.specArgResAnnotation;

import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Auction;


@And({
        @Spec(path = "description", params = "descr", spec = Like.class),
        @Spec(path = "itemCategory", spec = EqualIgnoreCase.class),
        @Spec(path = "currentPrice", params = {"priceFrom", "priceTo"},
                spec = Between.class),
        @Spec(path = "auctionType", spec = EqualIgnoreCase.class),
        @Spec(path = "itemStatus", spec = EqualIgnoreCase.class)
})
public interface AuctionSpec extends Specification<Auction> {

}
