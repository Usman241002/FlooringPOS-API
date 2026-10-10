package ukhalid.dev.flooringpos_api.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "fitting_rules",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_fitting_rule_category_code",
                        columnNames = {"category_id", "code"}
                )
        }
)
public class FittingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal fittingPriceM2;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal minimumFittingCharge;

    public FittingRule() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFittingPriceM2() {
        return fittingPriceM2;
    }

    public void setFittingPriceM2(BigDecimal fittingPriceM2) {
        this.fittingPriceM2 = fittingPriceM2;
    }

    public BigDecimal getMinimumFittingCharge() {
        return minimumFittingCharge;
    }

    public void setMinimumFittingCharge(BigDecimal minimumFittingCharge) {
        this.minimumFittingCharge = minimumFittingCharge;
    }
}
