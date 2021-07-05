package rs.ac.uns.ftn.nistagram.shopping.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.repository.UserRepository;
import rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.cart.ShoppingCartEntryDTO;
import rs.ac.uns.ftn.nistagram.shopping.domain.Product;
import rs.ac.uns.ftn.nistagram.shopping.domain.cart.ShoppingCart;
import rs.ac.uns.ftn.nistagram.shopping.domain.cart.ShoppingCartItem;
import rs.ac.uns.ftn.nistagram.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.nistagram.shopping.repositories.ProductRepository;
import rs.ac.uns.ftn.nistagram.shopping.repositories.ShoppingCartRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void add(String username, ShoppingCartEntryDTO shoppingCartEntry) {
        validateUser(username);

        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findShoppingCartByOwnersId(username);
        Product product = productRepository.findById(shoppingCartEntry.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product with a given id doesn't exist"));

        product.setQuantity(product.getQuantity() - shoppingCartEntry.getQuantity());
        productRepository.save(product);

        ShoppingCart cart;
        if(optionalCart.isEmpty())
            cart = new ShoppingCart(userRepository.findById(username).orElseThrow());
        else cart = optionalCart.get();

        cart.addProduct(product, shoppingCartEntry.getQuantity());
        shoppingCartRepository.save(cart);
    }

    public void delete(String username, long shoppingCartItemId) {
        validateUser(username);
        ShoppingCart optionalCart = shoppingCartRepository.findShoppingCartByOwnersId(username).orElseThrow();

        validateForDeletion(optionalCart, shoppingCartItemId);

        ShoppingCartItem cartItem = optionalCart.findCartItemById(shoppingCartItemId);
        Product removedProduct = cartItem.getProduct();
        int quantity = cartItem.getQuantity();

        optionalCart.removeItem(shoppingCartItemId);
        shoppingCartRepository.save(optionalCart);

        removedProduct.setQuantity(removedProduct.getQuantity() + quantity);
        productRepository.save(removedProduct);
    }

    private void validateUser(String username) {
        if(!userRepository.existsById(username))
            throw new EntityNotFoundException("User with a given username doesn't exist");
    }
    private void validateForDeletion(ShoppingCart cart, long shoppingCartItemId){
        if(cart.isEmpty() || !cart.hasItem(shoppingCartItemId))
            throw new EntityNotFoundException("U can't delete given shopping cart item.");
    }

    public List<ShoppingCartItem> getShoppingCartItems(String username) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findShoppingCartByOwnersId(username);
        if (optionalCart.isEmpty())
            return List.of();
        return optionalCart.get().getShoppingCartItems();
    }
}
