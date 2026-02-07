package project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.pricefy.dto.request.ProductRequestDto;
import project.pricefy.dto.request.ProductRequestEditDto;
import project.pricefy.dto.response.ProductResponseDto;
import project.pricefy.entity.ProductModel;
import project.pricefy.repository.PriceRepository;
import project.pricefy.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;

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

    @Transactional
    public List<ProductResponseDto> listAll() {
        return productRepository.findAll()
                .stream()
                .map(productModel -> new ProductResponseDto(
                        productModel.getId(),
                        productModel.getName(),
                        productModel.getVlUnity(),
                        productModel.getVlAmount()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductResponseDto> listByName(String name) {
        return productRepository.findByName(name)
                .stream()
                .map(productModel -> new ProductResponseDto(
                        productModel.getId(),
                        productModel.getName(),
                        productModel.getVlUnity(),
                        productModel.getVlAmount()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDto edit(Long id, ProductRequestEditDto productRequestEdit) {

        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (productRequestEdit.name() != null) {
            product.setName(productRequestEdit.name());
        }
        if (productRequestEdit.vlUnity() != null) {
            product.setVlUnity(productRequestEdit.vlUnity());
        }
        if (productRequestEdit.vlAmount() != null) {
            product.setVlAmount(productRequestEdit.vlAmount());
        }

        productRepository.save(product);
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getVlUnity(),
                product.getVlAmount()
        );
    }

    @Transactional
    public void delete(Long id) {

        priceRepository.deleteByProduct_Id(id);
        productRepository.deleteById(id);
    }
}
