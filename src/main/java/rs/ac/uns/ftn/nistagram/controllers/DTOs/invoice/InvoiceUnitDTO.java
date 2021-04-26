package rs.ac.uns.ftn.nistagram.controllers.DTOs.invoice;


import rs.ac.uns.ftn.nistagram.auth.controller.dto.ProductDTO;

public class InvoiceUnitDTO {

    private long id;
    private ProductDTO product;
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
