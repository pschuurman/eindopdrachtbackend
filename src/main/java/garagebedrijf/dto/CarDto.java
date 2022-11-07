package garagebedrijf.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarDto {

    @NotNull
    public Long id;

    @NotBlank
    public String brand;
    @NotBlank
    public String type;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
