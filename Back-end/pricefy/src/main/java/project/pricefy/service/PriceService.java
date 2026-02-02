package project.pricefy.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import project.pricefy.dto.request.PriceRequestDto;
import project.pricefy.dto.response.PriceResponseDto;
import project.pricefy.entity.MarketModel;
import project.pricefy.entity.PriceModel;
import project.pricefy.entity.ProductModel;
import project.pricefy.repository.MarketRepository;
import project.pricefy.repository.PriceRepository;
import project.pricefy.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;
    private final MarketRepository marketRepository;
    private final ProductRepository productRepository;

    @Transactional
    public PriceResponseDto save(@RequestBody @Valid PriceRequestDto priceRequest){

        ProductModel product = productRepository.findById(priceRequest.idProduct())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        MarketModel market = marketRepository.findById(priceRequest.idMarket())
                .orElseThrow(() -> new RuntimeException("Mercado não encontrado"));

        PriceModel price = new PriceModel();
        price.setVlPrice(priceRequest.vlPrice());
        price.setIdProduct(product);
        price.setIdMarket(market);

        PriceModel saved = priceRepository.save(price);
        return new PriceResponseDto(
                saved.getId(),
                saved.getVlPrice(),
                saved.getIdProduct().getName(),
                saved.getIdMarket().getName()
        );
    }

    @Transactional
    public List<PriceResponseDto> listAll(){
        return priceRepository.findAll()
                .stream()
                .map(priceModel -> new PriceResponseDto(
                        priceModel.getId(),
                        priceModel.getVlPrice(),
                        priceModel.getIdProduct().getName(),
                        priceModel.getIdMarket().getName()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PriceResponseDto> listLowetsPrice() {

        return priceRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        p -> p.getIdProduct().getName(),
                        Collectors.minBy(Comparator.comparing(PriceModel::getVlPrice))
                ))
                .values()
                .stream()
                .flatMap(Optional::stream)
                .map(priceModel -> new PriceResponseDto(
                        priceModel.getId(),
                        priceModel.getVlPrice(),
                        priceModel.getIdProduct().getName(),
                        priceModel.getIdMarket().getName()
                ))
                .toList();
    }

}
