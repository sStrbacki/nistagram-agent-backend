package rs.ac.uns.ftn.nistagram.shopping.services;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.nistagram.auth.repository.UserRepository;
import rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.invoice.InvoiceRequestDTO;
import rs.ac.uns.ftn.nistagram.shopping.domain.cart.ShoppingCart;
import rs.ac.uns.ftn.nistagram.shopping.domain.cart.ShoppingCartItem;
import rs.ac.uns.ftn.nistagram.shopping.domain.invoice.InvoiceCollection;
import rs.ac.uns.ftn.nistagram.exceptions.EntityNotFoundException;
import rs.ac.uns.ftn.nistagram.exceptions.InvalidStateException;
import rs.ac.uns.ftn.nistagram.shopping.repositories.InvoiceCollectionRepository;
import rs.ac.uns.ftn.nistagram.shopping.repositories.InvoiceRepository;
import rs.ac.uns.ftn.nistagram.shopping.repositories.ShoppingCartRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceCollectionRepository invoiceCollectionRepository;
    private final InvoiceRepository invoiceRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    public InvoiceService(InvoiceCollectionRepository invoiceCollectionRepository,
                          InvoiceRepository invoiceRepository,
                          ShoppingCartRepository shoppingCartRepository,
                          UserRepository userRepository) {
        this.invoiceCollectionRepository = invoiceCollectionRepository;
        this.invoiceRepository = invoiceRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void checkout(String username, InvoiceRequestDTO invoiceRequest){
        validateUser(username);
        var shoppingCart = shoppingCartRepository
                .findShoppingCartByOwnersId(username);
        validateCart(shoppingCart);
        populateInvoices(invoiceRequest, username, shoppingCart.getShoppingCartItems());
        clearCart(shoppingCart);

    }

    public List<InvoiceCollection> getAll() {
        return invoiceCollectionRepository.findAll();
    }
    public InvoiceCollection get(String username) {
        return invoiceCollectionRepository.findInvoicesByOwnersId(username);
    }

    private void populateInvoices(InvoiceRequestDTO invoiceRequest,
                                  String username,
                                  List<ShoppingCartItem> items) {
        var invoices = invoiceCollectionRepository.findInvoicesByOwnersId(username);
        if(invoices == null)
            invoices = new InvoiceCollection(userRepository.getOne(username));
        invoices.addInvoices(items, invoiceRequest.getAddress());
        invoiceCollectionRepository.saveAndFlush(invoices);
    }

    public void reject(long invoiceId) {
        validateInvoice(invoiceId);
        var invoice = invoiceRepository.getOne(invoiceId);
        invoice.reject();
        invoiceRepository.saveAndFlush(invoice);
    }
    public void accept(long invoiceId) {
        validateInvoice(invoiceId);
        var invoice = invoiceRepository.getOne(invoiceId);
        invoice.accept();
        invoiceRepository.saveAndFlush(invoice);
    }


    private void validateInvoice(long invoiceId) {
        if(!invoiceRepository.existsById(invoiceId))
            throw new EntityNotFoundException("Invoice with a given id doesn't exist");
        if(!invoiceRepository.getOne(invoiceId).isPending())
            throw new InvalidStateException("Requested invoice is in invalid state.");
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
