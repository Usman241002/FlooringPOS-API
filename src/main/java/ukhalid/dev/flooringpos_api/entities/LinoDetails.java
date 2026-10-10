package ukhalid.dev.flooringpos_api.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "lino_details")
public class LinoDetails {
    @Id
    @Column(name = "product_id")
    private Integer productId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "roll_width_m", nullable = false,
            precision = 5, scale = 2)
    private BigDecimal rollWidthM;

    @Column(name = "stock_linear_metres", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal stockLinearMetres = BigDecimal.ZERO;

    public LinoDetails() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getRollWidthM() {
        return rollWidthM;
    }

    public void setRollWidthM(BigDecimal rollWidthM) {
        this.rollWidthM = rollWidthM;
    }

    public BigDecimal getStockLinearMetres() {
        return stockLinearMetres;
    }

    public void setStockLinearMetres(BigDecimal stockLinearMetres) {
        this.stockLinearMetres = stockLinearMetres;
    }
}


