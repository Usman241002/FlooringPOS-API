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
    private BigDecimal lengthM;

    @Digits(integer = 7, fraction = 3)
    @DecimalMin(value = "0.001")
    @Column(name = "width_m", precision = 10, scale = 3)
    private BigDecimal widthM;

    public Room() {
    }

    public Room(Address address, String name,
                BigDecimal lengthM, BigDecimal widthM) {
        this.address = address;
        this.name = name;
        this.lengthM = lengthM;
        this.widthM = widthM;
    }

    @Transient
    @JsonProperty("areaM2")
    public BigDecimal getAreaM2() {
        if (lengthM == null || widthM == null) {
            return null;
        }

        return lengthM.multiply(widthM)
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

    public BigDecimal getLengthM() {
        return lengthM;
    }

    public void setLengthM(BigDecimal lengthM) {
        this.lengthM = lengthM;
    }

    public BigDecimal getWidthM() {
        return widthM;
    }

    public void setWidthM(BigDecimal widthM) {
        this.widthM = widthM;
    }
}
