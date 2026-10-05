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
    private BigDecimal pricePerM2;

    @Column(nullable = false)
    private BigDecimal stockQuantityUnits;

    @Column(nullable = false)
    private BigDecimal m2PerUnit;

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

    public BigDecimal getPricePerM2() {
        return pricePerM2;
    }

    public void setPricePerM2(BigDecimal pricePerM2) {
        this.pricePerM2 = pricePerM2;
    }

    public BigDecimal getStockQuantityUnits() {
        return stockQuantityUnits;
    }

    public void setStockQuantityUnits(BigDecimal stockQuantityUnits) {
        this.stockQuantityUnits = stockQuantityUnits;
    }

    public BigDecimal getM2PerUnit() {
        return m2PerUnit;
    }

    public void setM2PerUnit(BigDecimal m2PerUnit) {
        this.m2PerUnit = m2PerUnit;
    }
}

