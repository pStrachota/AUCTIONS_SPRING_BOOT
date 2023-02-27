package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

import java.util.List;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.user.User;

public interface UserService {

    void deleteUserById(Long id);

    void changePassword(Long userId, String oldPassword, String newPassword);

    boolean existsByUsername(String username);

    List<User> getAllUsers();

    boolean existsByEmail(String email);

    User createUser(User user);
}
