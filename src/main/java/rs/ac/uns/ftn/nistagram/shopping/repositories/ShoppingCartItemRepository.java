package rs.ac.uns.ftn.nistagram.shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.nistagram.shopping.domain.cart.ShoppingCartItem;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    @Modifying
    @Query(value = "delete from ShoppingCartItem ITEM where ITEM.shoppingCart.id = :cartId")
    void deleteFromCartById(@Param("cartId") long cartId);
}
