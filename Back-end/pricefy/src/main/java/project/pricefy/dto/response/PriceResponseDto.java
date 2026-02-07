package project.pricefy.dto.response;

public record PriceResponseDto(
        Long id,
        Double vlPrice,
        String product,
        String idMarket
) {
}
