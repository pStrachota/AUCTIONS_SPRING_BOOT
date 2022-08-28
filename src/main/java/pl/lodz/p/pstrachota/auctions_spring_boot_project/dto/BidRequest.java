package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BidRequest {

    private String email;

    private BigDecimal bidPrice;

}
