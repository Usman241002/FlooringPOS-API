package ukhalid.dev.flooringpos_api.dtos;

import java.math.BigDecimal;

public class CreateProductDTO {

    private Integer brand;
    private Integer category;
    private String name;
    private BigDecimal sellingPriceM2;
    private BigDecimal costPriceM2;
    private Boolean isActive;

    public CreateProductDTO() {
    }

    public BigDecimal getCostPriceM2() {
        return costPriceM2;
    }

    public void setCostPriceM2(BigDecimal costPriceM2) {
        this.costPriceM2 = costPriceM2;
    }

    public BigDecimal getSellingPriceM2() {
        return sellingPriceM2;
    }

    public void setSellingPriceM2(BigDecimal sellingPriceM2) {
        this.sellingPriceM2 = sellingPriceM2;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
