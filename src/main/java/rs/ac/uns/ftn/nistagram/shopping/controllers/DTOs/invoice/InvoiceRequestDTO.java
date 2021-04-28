package rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.invoice;

import org.modelmapper.ModelMapper;
import rs.ac.uns.ftn.nistagram.shopping.domain.invoice.Address;

public class InvoiceRequestDTO {

    private ShippingAddressDTO shippingAddress;

    public ShippingAddressDTO getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getAddress(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(shippingAddress,Address.class);
    }
}
