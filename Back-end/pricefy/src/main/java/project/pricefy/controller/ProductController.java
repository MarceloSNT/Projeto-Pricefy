package project.pricefy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.pricefy.dto.request.ProductRequestDto;
import project.pricefy.dto.request.ProductRequestEditDto;
import project.pricefy.dto.response.ProductResponseDto;
import project.pricefy.repository.ProductRepository;
import project.pricefy.service.PriceService;
import project.pricefy.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("pricefy/product/v1")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final PriceService priceService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto productRequest) {
        ProductResponseDto product = productService.save(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponseDto>> findAllProducts(){
        return ResponseEntity.ok().body(productService.listAll());
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<ProductResponseDto>> findProductsByName(@PathVariable String name){
        return ResponseEntity.ok().body(productService.listByName(name));
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<ProductResponseDto> editProduct(@PathVariable Long id,@RequestBody @Valid ProductRequestEditDto productRequestEdit){
        ProductResponseDto product = productService.edit(id, productRequestEdit);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        priceService.delete(id);
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllProduct(){

        priceService.deleteAll();
        productService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
