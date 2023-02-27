package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

import java.util.Optional;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.RefreshToken;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteByUserId(Long userId);
}
