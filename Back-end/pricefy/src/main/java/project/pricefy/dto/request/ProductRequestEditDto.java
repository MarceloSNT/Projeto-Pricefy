package project.pricefy.dto.request;

public record ProductRequestEditDto(
        String name,
        Double vlUnity,
        Double vlAmount,
        Long user
) {
}
