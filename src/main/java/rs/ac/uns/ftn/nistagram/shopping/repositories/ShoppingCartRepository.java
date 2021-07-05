package rs.ac.uns.ftn.nistagram.shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.nistagram.shopping.domain.cart.ShoppingCart;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    @Query(value = "select C from ShoppingCart C where C.owner.username = :username")
    Optional<ShoppingCart> findShoppingCartByOwnersId(@Param("username") String username);

    @Modifying
    @Query(value = "delete from ShoppingCart CART where CART.id = :id")
    void deleteViaId(@Param("id") long id);
}
