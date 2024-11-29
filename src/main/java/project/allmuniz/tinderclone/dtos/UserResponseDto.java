package project.allmuniz.tinderclone.dtos;

public record UserResponseDto(
        Long id,
        String name,
        String phone,
        String address
) {

    public UserResponseDto(String name, String phone, String address) {
        this(null, name, phone, address);
    }
}
