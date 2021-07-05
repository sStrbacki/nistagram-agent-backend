package rs.ac.uns.ftn.nistagram.shopping.services.checkout;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.nistagram.auth.domain.User;
import rs.ac.uns.ftn.nistagram.shopping.domain.cart.ShoppingCart;
import rs.ac.uns.ftn.nistagram.shopping.domain.cart.ShoppingCartItem;
import rs.ac.uns.ftn.nistagram.shopping.domain.checkout.Order;
import rs.ac.uns.ftn.nistagram.shopping.domain.checkout.OrderedProduct;
import rs.ac.uns.ftn.nistagram.shopping.domain.checkout.Address;
import rs.ac.uns.ftn.nistagram.shopping.repositories.ShoppingCartItemRepository;
import rs.ac.uns.ftn.nistagram.shopping.repositories.ShoppingCartRepository;
import rs.ac.uns.ftn.nistagram.shopping.repositories.checkout.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderRepository orderRepository;
    private final ShoppingCartItemRepository cartItemRepository;


    @Transactional
    public void checkout(String username, Address deliveryAddress) {
        ShoppingCart cart = shoppingCartRepository.findShoppingCartByOwnersId(username).orElseThrow();
        List<ShoppingCartItem> cartItems = cart.getShoppingCartItems();

        Order order = new Order();
        order.setUser(initUserViaUsername(username));
        order.setDeliveryAddress(deliveryAddress);

        ArrayList<OrderedProduct> orderedProducts = new ArrayList<>();
        for (ShoppingCartItem cartItem : cartItems) {
            OrderedProduct orderedProduct = new OrderedProduct();

            orderedProduct.setProduct(cartItem.getProduct());
            orderedProduct.setQuantity(cartItem.getQuantity());
            orderedProduct.setOrder(order);

            orderedProducts.add(orderedProduct);
        }

        order.setProducts(orderedProducts);

        orderRepository.save(order);
        cartItemRepository.deleteFromCartById(cart.getId());
//        shoppingCartRepository.deleteViaId(cart.getId());
    }

    @Transactional
    public void markShipped(long orderId) {
        Order foundOrder = orderRepository.findById(orderId).orElseThrow();
        foundOrder.setShipped();
        orderRepository.save(foundOrder);
    }

    private User initUserViaUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }
}
