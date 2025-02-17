package com.challenge.techforb.User.Auth;

import com.challenge.techforb.GlobalExceptionHandler.CustomException;
import com.challenge.techforb.User.Jwt.JwtService;
import com.challenge.techforb.User.UserEntity.Role;
import com.challenge.techforb.User.UserEntity.UserEntity;
import com.challenge.techforb.User.UserEntity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username,request.password));
        }catch (BadCredentialsException exception){
            throw new CustomException("Usuario o contraseña incorrecta", HttpStatus.UNAUTHORIZED.value());
        }

        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->new CustomException("Usuario no encotnrado", HttpStatus.NOT_FOUND.value()));

        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new CustomException("El usuario ya está registrado", HttpStatus.BAD_REQUEST.value());
        }

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Role.ADMIN)
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .username(user.getUsername())
                .build();
    }
}
