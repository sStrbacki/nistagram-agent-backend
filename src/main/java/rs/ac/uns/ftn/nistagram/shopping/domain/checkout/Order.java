package rs.ac.uns.ftn.nistagram.shopping.domain.checkout;

import rs.ac.uns.ftn.nistagram.auth.domain.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private Order.Status status;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderedProduct> products;
    @OneToOne
    private Address deliveryAddress;

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Order() {
        this.status = Status.CREATED;
    }

    public static enum Status {
        CREATED, SHIPPED
    }

    public void setShipped() {
        this.status = Status.SHIPPED;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProduct> products) {
        this.products = products;
    }
}
