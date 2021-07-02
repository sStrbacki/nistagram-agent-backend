package rs.ac.uns.ftn.nistagram.shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.nistagram.shopping.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
