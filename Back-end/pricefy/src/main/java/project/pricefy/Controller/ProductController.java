package project.pricefy.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.pricefy.dto.request.ProductRequestDto;
import project.pricefy.dto.response.ProductResponseDto;
import project.pricefy.entity.ProductModel;
import project.pricefy.service.ProductService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("pricefy/products/v1")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto productRequest) {
        ProductResponseDto product = productService.save(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
