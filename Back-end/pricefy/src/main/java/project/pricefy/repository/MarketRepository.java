package project.pricefy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.pricefy.entity.MarketModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<MarketModel, Long> {

    Optional<MarketModel> findById (Long id);

    List<MarketModel> findByName (String name);
}
