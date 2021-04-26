package rs.ac.uns.ftn.nistagram.controllers.DTOs.invoice;

import rs.ac.uns.ftn.nistagram.domain.invoice.ShippingStatus;

public class ShippingDetailsDTO {

    private ShippingAddressDTO shippingAddress;
    private ShippingStatus shippingStatus;

    public ShippingAddressDTO getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
}
