package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.user.ChangePasswordDto;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.user.User;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.security.CurrentUser;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.security.UserDetailsImpl;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.UserService;

@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@CurrentUser UserDetailsImpl userDetails,
                                            ChangePasswordDto changePasswordDto) {
        userService.changePassword(userDetails.getId(), changePasswordDto.getOldPassword(),
                changePasswordDto.getNewPassword());
        return ResponseEntity.ok("Password changed successfully");

    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
