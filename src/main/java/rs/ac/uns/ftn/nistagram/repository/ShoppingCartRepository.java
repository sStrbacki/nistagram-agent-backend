package rs.ac.uns.ftn.nistagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.nistagram.domain.cart.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    @Query(value = "select distinct sc from shopping_carts sc where sc.owner.username = :username ")
    ShoppingCart findShoppingCartByOwnersId(String username);
}
