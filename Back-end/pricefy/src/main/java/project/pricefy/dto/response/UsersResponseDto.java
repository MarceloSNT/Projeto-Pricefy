package project.pricefy.dto.response;

public record UsersResponseDto(
        Long idUser,
        String username,
        String dsEmail,
        String dsPassword
) {
}
