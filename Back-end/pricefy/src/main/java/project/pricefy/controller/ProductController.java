package project.pricefy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.pricefy.dto.request.ProductRequestDto;
import project.pricefy.dto.response.ProductResponseDto;
import project.pricefy.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("pricefy/product/v1")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto productRequest) {
        ProductResponseDto product = productService.save(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponseDto>> findAllProducts(){
        return ResponseEntity.ok().body(productService.listAll());
    }
}
