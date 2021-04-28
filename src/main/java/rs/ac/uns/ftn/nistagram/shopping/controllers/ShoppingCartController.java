package rs.ac.uns.ftn.nistagram.shopping.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.nistagram.auth.middle.HttpUtil;
import rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.cart.ShoppingCartEntryDTO;
import rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.cart.ShoppingCartItemDTO;
import rs.ac.uns.ftn.nistagram.shopping.services.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService service;
    private final ModelMapper mapper;
    private final HttpUtil httpUtil;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  ModelMapper mapper,
                                  HttpUtil httpUtil) {
        this.service = shoppingCartService;
        this.mapper = mapper;
        this.httpUtil = httpUtil;
    }

    @PreAuthorize("hasAuthority('ADD_CART_ENTRY')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody ShoppingCartEntryDTO shoppingCartEntry, HttpServletRequest request){
        String username = (String) httpUtil.getHttpRequestAttribute(request, httpUtil.USERNAME_KEY);
        service.add(username,shoppingCartEntry);
        return ResponseEntity.ok("New shopping cart entry has been successfully added");
    }

    @PreAuthorize("hasAuthority('REMOVE_CART_ENTRY')")
    @DeleteMapping("{shoppingCartItemId}")
    public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable long shoppingCartItemId){
        String username = (String) httpUtil.getHttpRequestAttribute(request, httpUtil.USERNAME_KEY);
        service.delete(username, shoppingCartItemId);
        return ResponseEntity.ok("Shopping cart entry has been successfully removed");
    }

    @PreAuthorize("hasAuthority('GET_CART_ENTRY')")
    @GetMapping
    public ResponseEntity<List<ShoppingCartItemDTO>> getShoppingCartItems(HttpServletRequest request){
        String username = (String) httpUtil.getHttpRequestAttribute(request, httpUtil.USERNAME_KEY);
        return ResponseEntity.ok(service.getShoppingCartItems(username)
                                        .stream()
                                        .map(item -> mapper.map(item, ShoppingCartItemDTO.class))
                                        .collect(Collectors.toList()));
    }
}
