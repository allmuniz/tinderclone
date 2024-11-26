package project.allmuniz.tinderclone.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.allmuniz.tinderclone.dtos.AuthDto;
import project.allmuniz.tinderclone.dtos.UserRequestDto;
import project.allmuniz.tinderclone.dtos.UserResponseDto;
import project.allmuniz.tinderclone.entities.User;
import project.allmuniz.tinderclone.services.UserService;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint de Registro de Usuário
    @PostMapping("/register")
    public User register(@RequestBody UserRequestDto userRequest) {
        return userService.createUser(userRequest);
    }

    // Endpoint de Login
    @PostMapping("/login")
    public ResponseEntity<AuthDto> auth(@RequestBody AuthDto user) {
        return ResponseEntity.ok(this.userService.auth(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getLoggedUser() {
        Optional<User> user = userService.getUser();
        return user.map(value -> ResponseEntity.ok(new UserResponseDto(value.getName(), value.getPhone(), value.getAddress()))).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}