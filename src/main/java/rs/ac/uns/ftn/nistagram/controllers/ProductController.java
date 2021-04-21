package rs.ac.uns.ftn.nistagram.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.nistagram.controllers.DTOs.ProductDTO;
import rs.ac.uns.ftn.nistagram.domain.Product;
import rs.ac.uns.ftn.nistagram.services.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper mapper;

    public ProductController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping
    @Secured("ROLE_GUEST")
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<ProductDTO> productDtoList =
                this.productService.getAll().stream()
                        .map(product -> mapper.map(product, ProductDTO.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(productDtoList);
    }

    @PostMapping
    public ProductDTO add(@Valid @RequestBody ProductDTO dto) {
        return mapper.map(
                productService.add(mapper.map(dto, Product.class)),
                ProductDTO.class);
    }

    @GetMapping("{id}")
    public ProductDTO get(@PathVariable long id) {
        return mapper.map(this.productService.get(id), ProductDTO.class);
    }

    @PutMapping("{id}")
    public void update(@PathVariable long id, @Valid @RequestBody ProductDTO dto) {
        productService.update(id, mapper.map(dto, Product.class));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        productService.delete(id);
    }
}
