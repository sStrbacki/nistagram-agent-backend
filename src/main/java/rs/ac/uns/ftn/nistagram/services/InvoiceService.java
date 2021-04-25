package rs.ac.uns.ftn.nistagram.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.user.UserRepository;
import rs.ac.uns.ftn.nistagram.controllers.DTOs.invoice.InvoiceRequestDTO;
import rs.ac.uns.ftn.nistagram.domain.cart.ShoppingCart;
import rs.ac.uns.ftn.nistagram.domain.cart.ShoppingCartItem;
import rs.ac.uns.ftn.nistagram.domain.invoice.InvoiceCollection;
import rs.ac.uns.ftn.nistagram.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.nistagram.repository.InvoiceCollectionRepository;
import rs.ac.uns.ftn.nistagram.repository.ShoppingCartRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceCollectionRepository invoiceRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    public InvoiceService(InvoiceCollectionRepository invoiceRepository,
                          ShoppingCartRepository shoppingCartRepository,
                          UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void checkout(InvoiceRequestDTO invoiceRequest){

        validateUser(invoiceRequest.getUsername());
        var shoppingCart = shoppingCartRepository
                .findShoppingCartByOwnersId(invoiceRequest.getUsername());
        validateCart(shoppingCart);
        populateInvoices(invoiceRequest, shoppingCart.getShoppingCartItems());
        clearCart(shoppingCart);

    }

    private void populateInvoices(InvoiceRequestDTO invoiceRequest, List<ShoppingCartItem> items) {
        var invoices = invoiceRepository.findInvoicesByOwnersId(invoiceRequest.getUsername());
        if(invoices == null)
            invoices = new InvoiceCollection(userRepository.getOne(invoiceRequest.getUsername()));
        invoices.addInvoices(items, invoiceRequest.getAddress());
        invoiceRepository.saveAndFlush(invoices);
    }

    private void clearCart(ShoppingCart shoppingCart) {
        shoppingCart.clearCart();
        shoppingCartRepository.saveAndFlush(shoppingCart);
    }

    private void validateUser(String username) {
        if(!userRepository.existsById(username))
            throw new EntityNotFoundException("User with a given username doesn't exist");
    }
    private void validateCart(ShoppingCart shoppingCart){
        if(shoppingCart == null || shoppingCart.isEmpty())
            throw new EntityNotFoundException("The user's shopping cart is currently empty ");
    }

}
