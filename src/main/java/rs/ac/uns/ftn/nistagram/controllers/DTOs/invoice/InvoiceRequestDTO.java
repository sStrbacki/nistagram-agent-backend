package rs.ac.uns.ftn.nistagram.controllers.DTOs.invoice;

import org.modelmapper.ModelMapper;
import rs.ac.uns.ftn.nistagram.domain.invoice.Address;

public class InvoiceRequestDTO {

    //TODO : remove this field when jwt auth is enabled
    private String username;
    private ShippingAddressDTO shippingAddress;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


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
