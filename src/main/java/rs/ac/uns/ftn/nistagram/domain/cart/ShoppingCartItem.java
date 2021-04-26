package rs.ac.uns.ftn.nistagram.domain.cart;

import rs.ac.uns.ftn.nistagram.domain.Product;
import rs.ac.uns.ftn.nistagram.domain.cart.ShoppingCart;

import javax.persistence.*;

@Entity(name = "shopping_cart_items")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="shopping_cart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

    private int quantity;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Product product, int quantity, ShoppingCart shoppingCart) {
        this.product = product;
        this.quantity = quantity;
        this.shoppingCart = shoppingCart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementBy(int quantity) {
        this.quantity += quantity;
    }

}
