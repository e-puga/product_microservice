package com.eypa.msa.product_microservice.controller;

import com.eypa.msa.product_microservice.entity.ProductEntity;
import com.eypa.msa.product_microservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository _productRepository;

    @Autowired
    public  ProductController(ProductRepository _productRepository) {
        this._productRepository = _productRepository;
    }

    /// Primer endpoint de tipo GET para listar los productos
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getProducts() {
        return _productRepository.findAll();
    }

    /// Segundo endpoint de tipo POSST para añadir un producto
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductEntity productEntity) {
        _productRepository.save(productEntity);
    }

    @PutMapping("/updateProducto")
    @ResponseStatus(HttpStatus.OK)
    public ProductEntity updateProduct(@RequestBody ProductEntity productEntity ) {
        Optional<ProductEntity> optionalProduct = _productRepository.findById(productEntity.getId());
        if (optionalProduct.isPresent()) {
            ProductEntity productodb = optionalProduct.get();
            productodb.setProductName(productEntity.getProductName());
            productodb.setProductDescription(productEntity.getProductDescription());
            productodb.setUnitPrice(productEntity.getUnitPrice());

            return _productRepository.save(productodb);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
    }

    @DeleteMapping("/deleteProducto")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204: eliminado correctamente sin cuerpo
    public void deleteProduct(@RequestParam String id) {
        if (!_productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        _productRepository.deleteById(id);
    }
}
