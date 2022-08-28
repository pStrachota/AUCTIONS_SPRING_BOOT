package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto;

import static pl.lodz.p.pstrachota.auctions_spring_boot_project.service.properties.AppConstants.maxDescriptionLenght;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuctionUpdate {

    @Email(regexp = "[^@]+@[^@]+\\.[^@.]+", message = "Email is not valid")
    private String email;

    @Size(min = 1, max = maxDescriptionLenght, message = "Description must be provided")
    private String description;
}
