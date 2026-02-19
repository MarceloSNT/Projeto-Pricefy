package project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.pricefy.dto.request.MarketRequestDto;
import project.pricefy.dto.response.MarketResponseDto;
import project.pricefy.model.MarketModel;
import project.pricefy.repository.MarketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository marketRepository;

    @Transactional
    public MarketResponseDto save(MarketRequestDto marketRequest) {
        MarketModel market = new MarketModel();
        market.setName(marketRequest.name());

        MarketModel saved = marketRepository.save(market);

        return new MarketResponseDto(
                saved.getId(),
                saved.getName()
        );
    }

    @Transactional
    public List<MarketResponseDto> listAll(){
        return marketRepository.findAll()
                .stream()
                .map(marketModel -> new MarketResponseDto(
                        marketModel.getId(),
                        marketModel.getName()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        marketRepository.deleteById(id);
    }
}
