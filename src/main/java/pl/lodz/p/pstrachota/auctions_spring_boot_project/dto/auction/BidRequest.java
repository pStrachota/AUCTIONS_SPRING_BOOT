package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.validators.PriceConstraint;

@Data
@Builder
public class BidRequest {

    @PriceConstraint
    private BigDecimal bidPrice;

}
