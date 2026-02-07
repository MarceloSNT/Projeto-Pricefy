package project.pricefy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.pricefy.entity.PriceModel;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceModel, Long> {

    Optional<PriceModel> findById(Long id);

    List<PriceModel> findByProduct_Id (Long idProduct);

    void deleteByProduct_Id(Long id);


}
