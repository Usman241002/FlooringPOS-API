package ukhalid.dev.flooringpos_api.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // room id

    @Column(name = "quantity_m2")
    private BigDecimal quantityM2;

    @Column(name = "unit_price_m2")
    private BigDecimal unitPriceM2;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "line_total")
    private BigDecimal lineTotal;

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public BigDecimal getQuantityM2() {
        return quantityM2;
    }

    public void setQuantityM2(BigDecimal quantityM2) {
        this.quantityM2 = quantityM2;
    }

    public BigDecimal getUnitPriceM2() {
        return unitPriceM2;
    }

    public void setUnitPriceM2(BigDecimal unitPriceM2) {
        this.unitPriceM2 = unitPriceM2;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}


