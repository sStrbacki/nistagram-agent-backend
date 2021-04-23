package rs.ac.uns.ftn.nistagram.controllers.DTOs;

public class ShoppingCartEntryDTO {

    //TODO : remove this field when jwt auth is enabled
    private String username;
    private long productId;
    private int quantity;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
