package project.pricefy.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(
        Long id,

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotNull(message = "A quantidade em unidade é obrigatório")
        Double vlUnity,

        @NotNull(message = "A quantidade é obrigatória")
        Double vlAmount
) {
}
