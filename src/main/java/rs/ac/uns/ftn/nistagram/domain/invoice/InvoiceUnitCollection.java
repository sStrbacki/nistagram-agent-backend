package rs.ac.uns.ftn.nistagram.domain.invoice;

import rs.ac.uns.ftn.nistagram.domain.cart.ShoppingCartItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "invoice_unit_collections")
public class InvoiceUnitCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @OneToMany(mappedBy = "invoiceUnitCollection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceUnit> invoiceUnits;

    public InvoiceUnitCollection() {
    }

    public InvoiceUnitCollection(List<ShoppingCartItem> items, Invoice invoice) {
        populateInvoiceUnits(items);
        this.invoice = invoice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<InvoiceUnit> getInvoiceUnits() {
        return invoiceUnits;
    }

    public void setInvoiceUnits(List<InvoiceUnit> invoiceUnits) {
        this.invoiceUnits = invoiceUnits;
    }


    private void populateInvoiceUnits(List<ShoppingCartItem> items) {
        if(items == null)
            this.invoiceUnits = new ArrayList<>();
        for(var item : items)
            invoiceUnits.add(new InvoiceUnit(item, this));
    }
}
