package rs.ac.uns.ftn.nistagram.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.domain.Product;
import rs.ac.uns.ftn.nistagram.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.nistagram.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Product get(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public void update(long id, Product product) {
        if(productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Product with id " + id + " doesn't exist!");
        }
    }

    public void delete(long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Product with id " + id + " doesn't exist!");
        }

    }
}
