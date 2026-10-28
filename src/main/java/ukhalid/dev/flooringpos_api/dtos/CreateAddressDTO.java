package ukhalid.dev.flooringpos_api.dtos;

public class CreateAddressDTO {
    private Integer customerId;
    private String line1;
    private String line2;
    private String postcode;

    public Integer getCustomerId() {
        return customerId;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getPostcode() {
        return postcode;
    }
}