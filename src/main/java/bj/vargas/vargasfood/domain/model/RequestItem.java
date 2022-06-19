package bj.vargas.vargasfood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class RequestItem {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal unityPrice;
    private BigDecimal totalPrice;
    private Integer quantity;
    private String obs;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Request request;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
}
