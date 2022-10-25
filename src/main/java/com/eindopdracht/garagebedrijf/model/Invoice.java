package com.eindopdracht.garagebedrijf.model;

import javax.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue
    private Long id;

    private String partName;

    private short price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public short getPrice() {
        return price;
    }

    public void setPrice(short price) {
        this.price = price;
    }
}
