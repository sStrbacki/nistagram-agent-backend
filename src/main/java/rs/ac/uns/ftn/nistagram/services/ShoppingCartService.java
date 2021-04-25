package rs.ac.uns.ftn.nistagram.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.user.UserRepository;
import rs.ac.uns.ftn.nistagram.controllers.DTOs.ShoppingCartEntryDTO;
import rs.ac.uns.ftn.nistagram.controllers.DTOs.ShoppingCartItemRemovalDTO;
import rs.ac.uns.ftn.nistagram.domain.cart.ShoppingCart;
import rs.ac.uns.ftn.nistagram.domain.cart.ShoppingCartItem;
import rs.ac.uns.ftn.nistagram.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.nistagram.repository.ProductRepository;
import rs.ac.uns.ftn.nistagram.repository.ShoppingCartRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,
                               UserRepository userRepository,
                               ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void add(ShoppingCartEntryDTO shoppingCartEntry) {

        validateUser(shoppingCartEntry.getUsername());

        var cart = shoppingCartRepository
                .findShoppingCartByOwnersId(shoppingCartEntry.getUsername());
        var product = productRepository.findById(shoppingCartEntry.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product with a given id doesn't exist"));

        if(cart == null)
            cart = new ShoppingCart(userRepository.getOne(shoppingCartEntry.getUsername()));

        cart.addProduct(product, shoppingCartEntry.getQuantity());
        shoppingCartRepository.saveAndFlush(cart);
    }

    public void delete(ShoppingCartItemRemovalDTO removal) {
        validateUser(removal.getUsername());
        var cart = shoppingCartRepository
                .findShoppingCartByOwnersId(removal.getUsername());

        validateForDeletion(cart, removal.getShoppingCartItemId());
        cart.removeItem(removal.getShoppingCartItemId());
        shoppingCartRepository.saveAndFlush(cart);
    }

    private void validateUser(String username) {
        if(!userRepository.existsById(username))
            throw new EntityNotFoundException("User with a given username doesn't exist");
    }
    private void validateForDeletion(ShoppingCart cart, long shoppingCartItemId){
        if(cart == null || cart.isEmpty() || !cart.hasItem(shoppingCartItemId))
            throw new EntityNotFoundException("U can't delete given shopping cart item.");
    }

    public List<ShoppingCartItem> getShoppingCartItems(String username) {
        var cart = shoppingCartRepository.findShoppingCartByOwnersId(username);
        return cart.getShoppingCartItems();
    }
}
