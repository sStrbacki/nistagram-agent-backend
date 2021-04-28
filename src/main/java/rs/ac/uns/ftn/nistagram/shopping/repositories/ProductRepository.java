package rs.ac.uns.ftn.nistagram.shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.nistagram.shopping.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
