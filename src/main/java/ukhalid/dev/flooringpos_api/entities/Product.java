package ukhalid.dev.flooringpos_api.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal sellingPriceM2;
    @Column
    private BigDecimal costPriceM2;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSellingPriceM2() {
        return sellingPriceM2;
    }

    public void setSellingPriceM2(BigDecimal sellingPriceM2) {
        this.sellingPriceM2 = sellingPriceM2;
    }

    public BigDecimal getCostPriceM2() {
        return costPriceM2;
    }

    public void setCostPriceM2(BigDecimal costPriceM2) {
        this.costPriceM2 = costPriceM2;
    }
}

