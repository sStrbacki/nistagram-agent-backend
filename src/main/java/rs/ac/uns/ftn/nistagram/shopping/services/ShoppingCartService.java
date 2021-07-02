package rs.ac.uns.ftn.nistagram.shopping.services;

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
    public void add(String username, ShoppingCartEntryDTO shoppingCartEntry) {

        validateUser(username);

        var cart = shoppingCartRepository
                .findShoppingCartByOwnersId(username);
        var product = productRepository.findById(shoppingCartEntry.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product with a given id doesn't exist"));

        product.setQuantity(product.getQuantity() - shoppingCartEntry.getQuantity());
        productRepository.save(product);

        if(cart == null)
            cart = new ShoppingCart(userRepository.findById(username).orElseThrow());

        cart.addProduct(product, shoppingCartEntry.getQuantity());
        shoppingCartRepository.save(cart);
    }

    public void delete(String username, long shoppingCartItemId) {
        validateUser(username);
        var cart = shoppingCartRepository
                .findShoppingCartByOwnersId(username);

        validateForDeletion(cart, shoppingCartItemId);

        ShoppingCartItem cartItem = cart.findCartItemById(shoppingCartItemId);
        Product removedProduct = cartItem.getProduct();
        int quantity = cartItem.getQuantity();

        cart.removeItem(shoppingCartItemId);
        shoppingCartRepository.save(cart);

        removedProduct.setQuantity(removedProduct.getQuantity() + quantity);
        productRepository.save(removedProduct);
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
        if (cart == null) return List.of();
        return cart.getShoppingCartItems();
    }
}
