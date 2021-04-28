package rs.ac.uns.ftn.nistagram.shopping.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.nistagram.auth.middle.HttpUtil;
import rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.invoice.InvoiceCollectionDTO;
import rs.ac.uns.ftn.nistagram.shopping.controllers.DTOs.invoice.InvoiceRequestDTO;
import rs.ac.uns.ftn.nistagram.shopping.services.InvoiceService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    private final InvoiceService service;
    private final ModelMapper mapper;
    private final HttpUtil httpUtil;

    public InvoiceController(InvoiceService service, ModelMapper mapper, HttpUtil httpUtil) {
        this.service = service;
        this.mapper = mapper;
        this.httpUtil = httpUtil;
    }

    @PreAuthorize("hasAuthority('GET_ALL_INVOICES')")
    @GetMapping
    public ResponseEntity<List<InvoiceCollectionDTO>> getAll(){
        var invoiceCollections = service.getAll()
                                .stream()
                                .map(ic -> mapper.map(ic, InvoiceCollectionDTO.class))
                                .collect(Collectors.toList());
        return ResponseEntity.ok(invoiceCollections);
    }

    @PreAuthorize("hasAuthority('GET_USER_CART')")
    @GetMapping("/user/")
    public ResponseEntity<?> get(HttpServletRequest request){
        String username = (String) httpUtil.getHttpRequestAttribute(request, httpUtil.USERNAME_KEY);
        var invoices = mapper.map(service.get(username), InvoiceCollectionDTO.class);
        return ResponseEntity.ok(invoices);
    }

    @PreAuthorize("hasAuthority('CHECKOUT_INVOICE')")
    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody InvoiceRequestDTO invoiceRequest,
                                      HttpServletRequest request){
        String username = (String) httpUtil.getHttpRequestAttribute(request, httpUtil.USERNAME_KEY);
        service.checkout(username, invoiceRequest);
        return ResponseEntity.ok("Successfully checked out");
    }

    @PreAuthorize("hasAuthority('REJECT_INVOICE')")
    @PutMapping("/reject/{invoiceId}")
    public ResponseEntity<?> reject(@PathVariable long invoiceId){
        service.reject(invoiceId);
        return ResponseEntity.ok("Order rejected");
    }

    @PreAuthorize("hasAuthority('ACCEPT_INVOICE')")
    @PutMapping("/accept/{invoiceId}")
    public ResponseEntity<?> accept(@PathVariable long invoiceId){
        service.accept(invoiceId);
        return ResponseEntity.ok("Order accepted");
    }
}
