package rs.ac.uns.ftn.nistagram.domain.invoice;
import javax.persistence.*;

@Entity(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="invoice_collection_id", referencedColumnName = "id")
    private InvoiceCollection invoiceCollection;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "invoice")
    private InvoiceUnitCollection invoiceUnitCollection;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "invoice")
    private ShippingDetails shippingDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InvoiceCollection getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(InvoiceCollection invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    public InvoiceUnitCollection getInvoiceUnitCollection() {
        return invoiceUnitCollection;
    }

    public void setInvoiceUnitCollection(InvoiceUnitCollection invoiceUnitCollection) {
        this.invoiceUnitCollection = invoiceUnitCollection;
    }

    public ShippingDetails getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(ShippingDetails shippingDetails) {
        this.shippingDetails = shippingDetails;
    }
}
