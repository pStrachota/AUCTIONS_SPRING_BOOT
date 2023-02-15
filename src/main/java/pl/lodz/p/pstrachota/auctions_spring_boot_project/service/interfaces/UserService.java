package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.user.User;

public interface UserService {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User createUser(User user);
}
