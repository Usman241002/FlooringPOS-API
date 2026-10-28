package ukhalid.dev.flooringpos_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_variants")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "thickness_mm")
    private Integer thicknessMm;

    @Column(name = "roll_width_m", precision = 5, scale = 2)
    private BigDecimal rollWidthM;

    @Column(name = "sqm_per_box", precision = 8, scale = 3)
    private BigDecimal sqmPerBox;

    @Column(nullable = false, length = 10)
    private String unit;

    @Column(name = "cost_price_m2", precision = 12, scale = 2)
    private BigDecimal costPriceM2;

    @Column(name = "selling_price_m2", nullable = false, precision = 12, scale = 2)
    private BigDecimal sellingPriceM2;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}