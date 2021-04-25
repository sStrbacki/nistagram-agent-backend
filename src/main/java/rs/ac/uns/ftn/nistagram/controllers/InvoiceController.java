package rs.ac.uns.ftn.nistagram.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.nistagram.controllers.DTOs.invoice.InvoiceRequestDTO;
import rs.ac.uns.ftn.nistagram.services.InvoiceService;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping
    private ResponseEntity<?> checkout(@RequestBody InvoiceRequestDTO invoiceRequest){
        service.checkout(invoiceRequest);
        return ResponseEntity.ok("Successfully checked out!");
    }
}
