package rs.ac.uns.ftn.nistagram.domain.invoice;

import rs.ac.uns.ftn.nistagram.domain.Product;

import javax.persistence.*;

@Entity(name = "invoice_units")
public class InvoiceUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="invoice_unit_collection_id", referencedColumnName = "id")
    private InvoiceUnitCollection invoiceUnitCollection;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InvoiceUnitCollection getInvoiceUnitCollection() {
        return invoiceUnitCollection;
    }

    public void setInvoiceUnitCollection(InvoiceUnitCollection invoiceUnitCollection) {
        this.invoiceUnitCollection = invoiceUnitCollection;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
