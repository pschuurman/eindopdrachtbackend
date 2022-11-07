package garagebedrijf.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InvoiceDto {

    @NotNull
    public Long id;

    @NotBlank
    public String partName;

    @NotNull
    public short price;

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
