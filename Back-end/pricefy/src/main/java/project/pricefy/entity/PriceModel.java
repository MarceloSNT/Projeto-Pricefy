package project.pricefy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBPRICE")
public class PriceModel {

    @Column(name = "IDPRICE")
    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "VLPRICE")
    private Double vlPrice;

    @ManyToOne
    @JoinColumn(name = "IDPRODUCT")
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "IDMARKET")
    private MarketModel idMarket;
}
