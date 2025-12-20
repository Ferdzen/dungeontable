package br.edu.utfpr.dungeontable.security;

import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.exception.ErrorCode;
import br.edu.utfpr.dungeontable.model.User;
import br.edu.utfpr.dungeontable.security.Role;
import br.edu.utfpr.dungeontable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    // REGISTER
    public void register(RegisterRequest request) {

        if (userRepository.findByEmailIgnoreCase(request.getEmail()).isPresent()) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // Validação de senha
        String password = request.getPassword();
        String regex = "^(?=.*\\d).{6,}$";
        if (!password.matches(regex)) {
            throw new BusinessException(
                    ErrorCode.ATTRIBUTE_REQUIRED,
                    "A senha deve conter pelo menos 6 caracteres e 1 número."
            );
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(password));


        user.setRole(Role.USER);


        user.setProfiles(List.of());

        userRepository.save(user);
    }

    // LOGIN
    public AuthResponse authenticate(AuthRequest request) {

        User user = userRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.NOT_FOUND, "Usuário não encontrado")
                );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }

        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setId(user.getId());
        userAuthentication.setEmail(user.getEmail());
        userAuthentication.setRole(user.getRole());

        String jwtToken = jwtService.generateToken(userAuthentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtToken);
        authResponse.setEmail(user.getEmail());
        authResponse.setUserId(user.getId());
        authResponse.setUsername(user.getUsername());
        authResponse.setExpires(new Date(System.currentTimeMillis() + 1000 * 60 * 24));

        return authResponse;
    }
}
