package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String token;

    private Long id;

    private String username;

    private String email;

    private String role;

}
