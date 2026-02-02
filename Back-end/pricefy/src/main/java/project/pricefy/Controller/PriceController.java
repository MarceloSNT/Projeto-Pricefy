package project.pricefy.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.pricefy.dto.request.PriceRequestDto;
import project.pricefy.dto.response.PriceResponseDto;
import project.pricefy.repository.PriceRepository;
import project.pricefy.service.PriceService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("pricefy/price/v1")
public class PriceController {

    private final PriceService priceService;

    @PostMapping("/create")
    public ResponseEntity<PriceResponseDto> createPrice(@RequestBody @Valid PriceRequestDto priceRequest){
        PriceResponseDto price = priceService.save(priceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(price);
    }
}
