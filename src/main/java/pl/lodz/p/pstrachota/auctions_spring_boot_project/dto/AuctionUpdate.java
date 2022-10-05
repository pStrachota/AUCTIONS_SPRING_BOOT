package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto;

import static pl.lodz.p.pstrachota.auctions_spring_boot_project.service.properties.AppConstants.maxDescriptionLenght;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
public class AuctionUpdate {

    @Schema(example = "sample@mail.com")
    @Email(regexp = "[^@]+@[^@]+\\.[^@.]+", message = "Email is not valid")
    private String email;

    @Schema(example = "new sample description")
    @Size(min = 1, max = maxDescriptionLenght, message = "Description must be provided")
    private String description;
}
