package rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs;

public class DeliveryAddressDTO {
    private String addressLine;
    private String city;
    private String postalCode;
    private String country;

    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }
}
