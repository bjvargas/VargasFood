package bj.vargas.vargasfood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Request {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal totalPrice;

    @Embedded
    private Address deliveryAddress;

    private StatusRequest status;

    @CreationTimestamp
    private LocalDateTime registerDate;

    private LocalDateTime confirmationDate;
    private LocalDateTime cancelDate;
    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_customer_id", nullable = false)
    private User customer;

    @OneToMany(mappedBy = "request")
    private List<RequestItem> requestItems = new ArrayList<>();


}
