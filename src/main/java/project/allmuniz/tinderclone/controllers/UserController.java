package project.allmuniz.tinderclone.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.allmuniz.tinderclone.dtos.UserRequestDto;
import project.allmuniz.tinderclone.dtos.UserResponseDto;
import project.allmuniz.tinderclone.entities.User;
import project.allmuniz.tinderclone.services.UserService;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@Tag(name = "Users", description = "Gerenciamento dos usuários")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint de Registro de Usuário
    @PostMapping("/register")
    @Operation(summary = "Save user",
            description = "This function is responsible for register a user.")
    public UserResponseDto register(@RequestBody UserRequestDto userRequest) {
        User savedUser = userService.createUser(userRequest);
        return new UserResponseDto(savedUser.getName(), savedUser.getPhone(), savedUser.getAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
