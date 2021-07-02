package rs.ac.uns.ftn.nistagram.shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.nistagram.shopping.domain.cart.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    @Query(value = "select distinct sc from shopping_carts sc where sc.owner.username = :username ")
    ShoppingCart findShoppingCartByOwnersId(String username);
}
