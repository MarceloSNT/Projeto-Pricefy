package project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.pricefy.dto.request.ProductRequestDto;
import project.pricefy.dto.response.ProductResponseDto;
import project.pricefy.entity.ProductModel;
import project.pricefy.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto save(ProductRequestDto productRequest) {
        ProductModel product = new ProductModel();
        product.setName(productRequest.name());
        product.setVlUnity(productRequest.vlUnity());
        product.setVlAmount(productRequest.vlAmount());

        ProductModel saved = productRepository.save(product);

        return new ProductResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getVlUnity(),
                saved.getVlAmount()
        );
    }
}
