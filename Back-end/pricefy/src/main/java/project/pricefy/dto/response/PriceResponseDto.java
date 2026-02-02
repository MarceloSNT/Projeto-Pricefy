package project.pricefy.dto.response;

public record PriceResponseDto(
        Long id,
        Double vlPrice,
        String idProduct,
        String idMarket
) {
}
