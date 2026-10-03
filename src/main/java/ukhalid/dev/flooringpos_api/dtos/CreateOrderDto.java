package ukhalid.dev.flooringpos_api.dtos;

import ukhalid.dev.flooringpos_api.enums.StatusEnum;

import java.math.BigDecimal;

public class CreateOrderDto {

    private Integer customerId;
    private StatusEnum status;
    private BigDecimal discount;
    private BigDecimal amountPaid;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
}