package project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.pricefy.dto.request.ProductRequestDto;
import project.pricefy.dto.request.ProductRequestEditDto;
import project.pricefy.dto.response.ProductResponseDto;
import project.pricefy.model.ProductModel;
import project.pricefy.model.UserModel;
import project.pricefy.repository.PriceRepository;
import project.pricefy.repository.ProductRepository;
import project.pricefy.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;
    private final UserRepository userRepository;

    @Transactional
    public ProductResponseDto save(ProductRequestDto productRequest) {
        ProductModel product = new ProductModel();

        UserModel user = userRepository.findByIdUser(productRequest.user())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        product.setName(productRequest.name());
        product.setVlUnity(productRequest.vlUnity());
        product.setVlAmount(productRequest.vlAmount());
        product.setUser(user);

        ProductModel saved = productRepository.save(product);

        return new ProductResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getVlUnity(),
                saved.getVlAmount(),
                saved.getUser().getUsername()
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
                        productModel.getVlAmount(),
                        productModel.getUser().getUsername()
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
                        productModel.getVlAmount(),
                        productModel.getUser().getUsername()
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
                product.getVlAmount(),
                product.getUser().getUsername()
        );
    }

    @Transactional
    public void delete(Long id) {
        priceRepository.deleteByProduct_Id(id);
        productRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        priceRepository.deleteAll();
        productRepository.deleteAll();
    }
}
