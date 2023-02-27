package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
