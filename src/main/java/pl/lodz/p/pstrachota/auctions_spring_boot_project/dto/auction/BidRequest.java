package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.validators.PriceConstraint;

@Data
@Builder
public class BidRequest {

    @Schema(example = "sample@mail.com")
    @Email(regexp = "[^@]+@[^@]+\\.[^@.]+", message = "Email is not valid")
    private String email;

    @PriceConstraint
    private BigDecimal bidPrice;

}
