package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.user.User;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.UserRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

}
