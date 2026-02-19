package project.pricefy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.pricefy.dto.request.MarketRequestDto;
import project.pricefy.dto.response.MarketResponseDto;
import project.pricefy.service.MarketService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("pricefy/market/v1")
public class MarketController {

    private final MarketService marketService;

    @PostMapping("/create")
    public ResponseEntity<MarketResponseDto> createMarket(@RequestBody @Valid MarketRequestDto marketRequest){
        MarketResponseDto market = marketService.save(marketRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(market);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<MarketResponseDto>> findAllMarkets(){
        return ResponseEntity.ok().body(marketService.listAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMarket(@PathVariable Long id){
        marketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
