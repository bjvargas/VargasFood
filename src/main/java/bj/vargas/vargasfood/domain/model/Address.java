package bj.vargas.vargasfood.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Embeddable
public class Address {

    @Column(name = "address_zip_code")
    private String zipCode;

    @Column(name = "address_road")
    private String road;

    @Column(name = "address_number")
    private String number;

    @Column(name = "address_complement")
    private String complement;

    @ManyToOne
    @JoinColumn(name = "address_city_id")
    private City city;

}
