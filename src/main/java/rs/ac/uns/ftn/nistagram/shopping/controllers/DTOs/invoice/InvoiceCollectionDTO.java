package rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.invoice;

import rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.UserDTO;

import java.util.List;

public class InvoiceCollectionDTO {

    private long id;
    private UserDTO owner;
    private List<InvoiceDTO> invoices;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public List<InvoiceDTO> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceDTO> invoices) {
        this.invoices = invoices;
    }
}
