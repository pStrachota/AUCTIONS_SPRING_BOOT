package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller.auth;

import jakarta.validation.Valid;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auth.JwtResponse;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auth.LoginRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auth.SingUpRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auth.TokenRefreshRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auth.TokenRefreshResponse;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.NotFoundException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.RefreshToken;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.user.RoleEnum;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.user.User;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.security.JwtUtils;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.security.UserDetailsImpl;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.impl.UserDetailsServiceImpl;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.RefreshTokenService;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    final AuthenticationManager authenticationManager;

    final RefreshTokenService refreshTokenService;

    final UserDetailsServiceImpl userDetailsService;

    final UserService userService;

    final PasswordEncoder encoder;

    final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()).get(0);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(
            @Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {

        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new NotFoundException("Refresh token is not in database!"));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingUpRequest singupRequest) {

        if (userService.existsByUsername(singupRequest.getUsername())) {
            throw new IllegalArgumentException("Error: Username is already taken!");
        }

        if (userService.existsByEmail(singupRequest.getEmail())) {
            throw new IllegalArgumentException("Error: Email is already in use!");
        }

        User user = new User(singupRequest.getEmail(),
                encoder.encode(singupRequest.getPassword()),
                singupRequest.getUsername()
        );

        user.setRoleName(RoleEnum.ROLE_USER);
        userService.createUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
