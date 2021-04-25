package rs.ac.uns.ftn.nistagram.controllers.DTOs.cart;

public class ShoppingCartItemRemovalDTO {

    private String username;
    private long shoppingCartItemId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getShoppingCartItemId() {
        return shoppingCartItemId;
    }

    public void setShoppingCartItemId(long shoppingCartItemId) {
        this.shoppingCartItemId = shoppingCartItemId;
    }
}
