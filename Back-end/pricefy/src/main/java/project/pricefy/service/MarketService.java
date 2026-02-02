package project.pricefy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.pricefy.dto.request.MarketRequestDto;
import project.pricefy.dto.response.MarketResponseDto;
import project.pricefy.entity.MarketModel;
import project.pricefy.repository.MarketRepository;

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
}
