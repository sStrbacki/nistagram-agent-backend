package rs.ac.uns.ftn.nistagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.nistagram.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
