package rs.ac.uns.ftn.nistagram.controllers.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProductDTO {
    private long id;
    @NotBlank
    private String name;
    private String imageUrl;
    @Positive
    private double price;
    @Positive
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
