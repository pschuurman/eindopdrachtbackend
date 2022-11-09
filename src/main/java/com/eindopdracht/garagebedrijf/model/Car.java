package com.eindopdracht.garagebedrijf.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String type;

    @OneToOne(mappedBy = "car")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Car() {

    }


    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() { return invoice; }

    public void setInvoice(Invoice invoice) { this.invoice = invoice; }
}

