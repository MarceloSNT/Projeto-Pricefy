package project.pricefy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.pricefy.model.PriceModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<PriceModel, Long> {

    Optional<PriceModel> findById(Long id);

    List<PriceModel> findByProduct_Id (Long idProduct);

    void deleteByProduct_Id(Long id);


}
