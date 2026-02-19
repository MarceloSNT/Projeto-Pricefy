package project.pricefy.dto.response;

public record ProductResponseDto(
        Long id,
        String name,
        Double vlUnity,
        Double vlAmount,
        String user
) {
}
