package ukhalid.dev.flooringpos_api.dtos;

public class CreateAddressDTO {
    private Integer customerId;
    private String addressLine1;
    private String addressLine2;
    private String postcode;

    public Integer getCustomerId() {
        return customerId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getPostcode() {
        return postcode;
    }
}