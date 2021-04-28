package rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.invoice;


import java.util.List;

public class InvoiceDTO {

    private long id;
    private ShippingDetailsDTO shippingDetails;
    private List<InvoiceUnitDTO> invoiceUnits;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShippingDetailsDTO getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(ShippingDetailsDTO shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public List<InvoiceUnitDTO> getInvoiceUnits() {
        return invoiceUnits;
    }

    public void setInvoiceUnits(List<InvoiceUnitDTO> invoiceUnits) {
        this.invoiceUnits = invoiceUnits;
    }
}
