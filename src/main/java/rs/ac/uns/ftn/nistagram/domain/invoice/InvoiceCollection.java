package rs.ac.uns.ftn.nistagram.domain.invoice;

import rs.ac.uns.ftn.nistagram.auth.model.User;
import rs.ac.uns.ftn.nistagram.domain.cart.ShoppingCartItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "invoice_collections")
public class InvoiceCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "invoiceCollection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices;

    public InvoiceCollection(){}

    public InvoiceCollection(User owner) {
        this.owner = owner;
        this.invoices = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
    
    public void addInvoices(List<ShoppingCartItem> items, Address address) {
        invoices.add(new Invoice(items, address, this));
    }
}
