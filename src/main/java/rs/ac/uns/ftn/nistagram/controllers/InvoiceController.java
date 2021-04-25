package rs.ac.uns.ftn.nistagram.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.nistagram.controllers.DTOs.invoice.InvoiceCollectionDTO;
import rs.ac.uns.ftn.nistagram.controllers.DTOs.invoice.InvoiceRequestDTO;
import rs.ac.uns.ftn.nistagram.controllers.DTOs.invoice.InvoiceDTO;
import rs.ac.uns.ftn.nistagram.services.InvoiceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    private final InvoiceService service;
    private final ModelMapper mapper;

    public InvoiceController(InvoiceService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceCollectionDTO>> getAll(){
        var invoiceCollections = service.getAll()
                                .stream()
                                .map(ic -> mapper.map(ic, InvoiceCollectionDTO.class))
                                .collect(Collectors.toList());
        return ResponseEntity.ok(invoiceCollections);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> get(@PathVariable String username){
        var invoices = mapper.map(service.get(username), InvoiceCollectionDTO.class);
        return ResponseEntity.ok(invoices);
    }

    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody InvoiceRequestDTO invoiceRequest){
        service.checkout(invoiceRequest);
        return ResponseEntity.ok("Successfully checked out");
    }

    @PutMapping("/reject/{invoiceId}")
    public ResponseEntity<?> reject(@PathVariable long invoiceId){
        service.reject(invoiceId);
        return ResponseEntity.ok("Order rejected");
    }

    @PutMapping("/accept/{invoiceId}")
    public ResponseEntity<?> accept(@PathVariable long invoiceId){
        service.accept(invoiceId);
        return ResponseEntity.ok("Order accepted");
    }
}
