package project.allmuniz.tinderclone.dtos;

import java.time.LocalDate;

public record UserRequestDto(
        String name,
        String email,
        String password,
        String phone,
        LocalDate birthday,
        String address
) {
}
