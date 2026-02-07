package project.pricefy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.pricefy.dto.request.PriceRequestDto;
import project.pricefy.dto.response.PriceResponseDto;
import project.pricefy.service.PriceService;

import java.util.List;

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

    @GetMapping("/findAll")
    public ResponseEntity<List<PriceResponseDto>> findAllPrice(){
        return ResponseEntity.ok().body(priceService.listAll());
    }

    @GetMapping("/lowestPrice")
    public ResponseEntity<List<PriceResponseDto>> findLowestPrice(){
        return ResponseEntity.ok().body(priceService.listLowetsPrice());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long id){
        priceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
