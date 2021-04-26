package rs.ac.uns.ftn.nistagram.auth.controller.dto.cart;

public class ShoppingCartItemDTO {

    private long id;
    private ShoppingCartProductDTO product;
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShoppingCartProductDTO getProduct() {
        return product;
    }

    public void setProduct(ShoppingCartProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
