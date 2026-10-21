package ukhalid.dev.flooringpos_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    @JsonIgnore
    private Address address;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String name;

    @Digits(integer = 7, fraction = 3)
    @DecimalMin(value = "0.001")
    @Column(name = "length_m", precision = 10, scale = 3)
    private BigDecimal length;

    @Digits(integer = 7, fraction = 3)
    @DecimalMin(value = "0.001")
    @Column(name = "width_m", precision = 10, scale = 3)
    private BigDecimal width;

    public Room() {
    }

    public Room(Address address, String name,
                BigDecimal length, BigDecimal width) {
        this.address = address;
        this.name = name;
        this.length = length;
        this.width = width;
    }

    @Transient
    @JsonProperty("areaM2")
    public BigDecimal getAreaM2() {
        if (length == null || width == null) {
            return null;
        }

        return length.multiply(width)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public Integer getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }
}
