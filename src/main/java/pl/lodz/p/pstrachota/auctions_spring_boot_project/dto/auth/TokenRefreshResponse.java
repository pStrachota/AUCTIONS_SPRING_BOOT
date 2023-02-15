package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenRefreshResponse {

    private String accessToken;
    private String refreshToken;

}
