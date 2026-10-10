package ukhalid.dev.flooringpos_api.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "laminate_details")
public class LaminateDetails {

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "thickness_mm", precision = 5, scale = 2)
    private BigDecimal thicknessMm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private LaminateStyle style = LaminateStyle.STANDARD;

    @Column(name = "m2_per_box", nullable = false,
            precision = 8, scale = 3)
    private BigDecimal m2PerBox;

    @Column(name = "stock_boxes", nullable = false)
    private Integer stockBoxes = 0;

    public LaminateDetails() {
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

    public BigDecimal getThicknessMm() {
        return thicknessMm;
    }

    public void setThicknessMm(BigDecimal thicknessMm) {
        this.thicknessMm = thicknessMm;
    }

    public LaminateStyle getStyle() {
        return style;
    }

    public void setStyle(LaminateStyle style) {
        this.style = style;
    }

    public BigDecimal getM2PerBox() {
        return m2PerBox;
    }

    public void setM2PerBox(BigDecimal m2PerBox) {
        this.m2PerBox = m2PerBox;
    }

    public Integer getStockBoxes() {
        return stockBoxes;
    }

    public void setStockBoxes(Integer stockBoxes) {
        this.stockBoxes = stockBoxes;
    }
}
