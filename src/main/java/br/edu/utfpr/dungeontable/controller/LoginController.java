package br.edu.utfpr.dungeontable.controller;

import br.edu.utfpr.dungeontable.security.AuthRequest;
import br.edu.utfpr.dungeontable.security.AuthResponse;
import br.edu.utfpr.dungeontable.security.AuthService;
import br.edu.utfpr.dungeontable.security.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
