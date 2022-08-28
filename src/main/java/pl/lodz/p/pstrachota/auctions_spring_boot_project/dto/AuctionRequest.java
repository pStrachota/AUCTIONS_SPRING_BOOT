package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto;

import static pl.lodz.p.pstrachota.auctions_spring_boot_project.service.properties.AppConstants.descriptionLenght;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.AuctionType;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemStatus;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.validators.PriceConstraint;

@Data
@Builder
public class AuctionRequest {

    @Email(regexp = "[^@]+@[^@]+\\.[^@.]+", message = "Email is not valid")
    private String email;

    @Size(min = 1, max = descriptionLenght, message = "Description must be provided")
    private String description;

    private AuctionType auctionType;

    @PriceConstraint
    private BigDecimal startingPrice;

    private ItemStatus itemStatus;

    private ItemCategory itemCategory;

    @Size(min = 1, message = "Days to end time must be provided")
    private int daysToEndTime;

}
