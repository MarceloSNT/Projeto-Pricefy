package project.pricefy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBPRODUCT")
public class ProductModel {

    @Column(name = "IDPRODUCT")
    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAMEPRODUCT")
    private String name;

    @Column(name = "VLUNITY")
    private Double vlUnity;

    @Column(name = "VLAMOUNT")
    private Double vlAmount;
}
