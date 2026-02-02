package project.pricefy.dto.request;

import jakarta.validation.constraints.NotNull;

public record PriceRequestDto(
        Long id,

        @NotNull(message = "O preço é obrigatório")
        Double vlPrice,

        @NotNull(message = "O produto é obrigatório")
        Long idProduct,

        @NotNull(message = "O mercado é obrigatório")
        Long idMarket
) {
}
