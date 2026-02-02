package project.pricefy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBMARKET")
public class MarketModel {

    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
}
