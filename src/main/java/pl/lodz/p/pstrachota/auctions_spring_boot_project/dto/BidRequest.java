package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.validators.PriceConstraint;

@Data
@Builder
public class BidRequest {

    @Email(regexp = "[^@]+@[^@]+\\.[^@.]+", message = "Email is not valid")
    private String email;

    @PriceConstraint
    private BigDecimal bidPrice;

}
