package project.allmuniz.tinderclone.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.allmuniz.tinderclone.dtos.AuthDto;
import project.allmuniz.tinderclone.dtos.UserRequestDto;
import project.allmuniz.tinderclone.entities.User;
import project.allmuniz.tinderclone.repositories.UserRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserRequestDto userRequest) {
        if (userRepository.findByEmail(userRequest.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        checkAge(userRequest.birthday());

        User user = new User(userRequest, passwordEncoder.encode(userRequest.password()));
        return userRepository.save(user);
    }

    public AuthDto auth(AuthDto authDto) {
        User userEntity = this.findByEmail(authDto.getEmail());

        if (!this.passwordEncoder.matches(authDto.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String password = userEntity.getEmail() + ":" + userEntity.getPassword();

        return AuthDto.builder().email(userEntity.getEmail()).token(
                Base64.getEncoder().withoutPadding().encodeToString(password.getBytes())
        ).id(userEntity.getId()).build();
    }

    public void checkAge(LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("A idade mínima é 18 anos.");
        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usersOptional = this.userRepository.findByEmail(username);

        return usersOptional.map(users -> new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(), new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Optional<User> getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().toString();
        return userRepository.findByEmail(username);
    }
}
