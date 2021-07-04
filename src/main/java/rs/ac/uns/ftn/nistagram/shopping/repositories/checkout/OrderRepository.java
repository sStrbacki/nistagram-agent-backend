package rs.ac.uns.ftn.nistagram.shopping.repositories.checkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.nistagram.shopping.domain.checkout.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
