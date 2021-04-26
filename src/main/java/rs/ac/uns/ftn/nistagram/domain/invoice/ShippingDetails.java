package rs.ac.uns.ftn.nistagram.domain.invoice;

import javax.persistence.*;

@Entity(name = "shipping_details")
public class ShippingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    private ShippingStatus shippingStatus;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public ShippingDetails() {
    }

    public ShippingDetails(Address address, Invoice invoice) {
        this.invoice = invoice;
        this.address = address;
        this.shippingStatus = ShippingStatus.PENDING;
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

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void reject() {
        this.shippingStatus = ShippingStatus.REJECTED;
    }
    public void accept() {
        this.shippingStatus = ShippingStatus.SHIPPED;
    }
    public boolean isPending() {
        return this.shippingStatus == ShippingStatus.PENDING;
    }


}
