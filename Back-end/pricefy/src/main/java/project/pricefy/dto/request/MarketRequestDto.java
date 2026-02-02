package project.pricefy.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MarketRequestDto(
        Long id,

        @NotBlank(message = "O nome é obrigatório")
        String name
) {
}
