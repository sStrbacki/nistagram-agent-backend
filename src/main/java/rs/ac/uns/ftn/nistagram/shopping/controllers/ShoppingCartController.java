package rs.ac.uns.ftn.nistagram.shopping.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.nistagram.auth.middle.HttpUtil;
import rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.DeliveryAddressDTO;
import rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.cart.ShoppingCartEntryDTO;
import rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.cart.ShoppingCartItemDTO;
import rs.ac.uns.ftn.nistagram.shopping.domain.checkout.Address;
import rs.ac.uns.ftn.nistagram.shopping.services.ShoppingCartService;
import rs.ac.uns.ftn.nistagram.shopping.services.checkout.CheckoutService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/shopping-cart")
@AllArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService cartService;
    private final CheckoutService checkoutService;
    private final ModelMapper mapper;
    private final HttpUtil httpUtil;

    @PreAuthorize("hasAuthority('ADD_CART_ENTRY')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody ShoppingCartEntryDTO shoppingCartEntry, HttpServletRequest request){
        String username = (String) httpUtil.getHttpRequestAttribute(request, httpUtil.USERNAME_KEY);
        cartService.add(username,shoppingCartEntry);
        return ResponseEntity.ok("New shopping cart entry has been successfully added");
    }

    @PreAuthorize("hasAuthority('REMOVE_CART_ENTRY')")
    @DeleteMapping("{shoppingCartItemId}")
    public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable long shoppingCartItemId){
        String username = (String) httpUtil.getHttpRequestAttribute(request, httpUtil.USERNAME_KEY);
        cartService.delete(username, shoppingCartItemId);
        return ResponseEntity.ok("Shopping cart entry has been successfully removed");
    }

    @PreAuthorize("hasAuthority('GET_USER_CART')")
    @GetMapping
    public ResponseEntity<List<ShoppingCartItemDTO>> getShoppingCartItems(HttpServletRequest request){
        String username = (String) httpUtil.getHttpRequestAttribute(request, httpUtil.USERNAME_KEY);
        return ResponseEntity.ok(cartService.getShoppingCartItems(username)
                                        .stream()
                                        .map(item -> mapper.map(item, ShoppingCartItemDTO.class))
                                        .collect(Collectors.toList()));
    }

    @PostMapping("checkout")
    public void checkout(HttpServletRequest request, @RequestBody DeliveryAddressDTO dto) {
        String username = (String) httpUtil.getHttpRequestAttribute(request, httpUtil.USERNAME_KEY);
        checkoutService.checkout(username, mapper.map(dto, Address.class));
    }
}
