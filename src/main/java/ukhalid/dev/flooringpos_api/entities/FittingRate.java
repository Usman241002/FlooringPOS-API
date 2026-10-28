package ukhalid.dev.flooringpos_api.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "fitting_rates")
public class FittingRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "thickness_mm")
    private Integer thicknessMm;            // null = any thickness

    @Column(name = "is_herringbone", nullable = false)
    private boolean isHerringbone;

    @Column(name = "price_m2", nullable = false)
    private BigDecimal priceM2;

    @Column(name = "min_charge", nullable = false)
    private BigDecimal minCharge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMinCharge() {
        return minCharge;
    }

    public void setMinCharge(BigDecimal minCharge) {
        this.minCharge = minCharge;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPriceM2() {
        return priceM2;
    }

    public void setPriceM2(BigDecimal priceM2) {
        this.priceM2 = priceM2;
    }

    public Integer getThicknessMm() {
        return thicknessMm;
    }

    public void setThicknessMm(Integer thicknessMm) {
        this.thicknessMm = thicknessMm;
    }

    public void setIsHerringbone(boolean isHerringbone) {
        this.isHerringbone = isHerringbone;
    }

}