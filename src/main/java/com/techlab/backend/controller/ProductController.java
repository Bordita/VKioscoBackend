package com.techlab.backend.controller;

import com.techlab.backend.dto.ProductDto;
import com.techlab.backend.dto.DtoMapper;
import com.techlab.backend.model.Product;
import com.techlab.backend.service.api.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    /**
     * GET /api/products - Obtener todos los productos o filtrar por tipo
     * @param type (opcional) - tipo de producto: "Beverage" o "Food"
     */
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String type) {
        List<Product> products;
        
        if (type == null || type.trim().isEmpty()) {
            // Si no se especifica tipo, devolver todos los productos
            products = productService.findAllProducts();
        } else {
            // Filtrar por tipo
            switch (type.toLowerCase()) {
                case "beverage":
                    products = productService.findAllBeverages().stream()
                        .map(b -> (Product) b)
                        .toList();
                    break;
                case "food":
                    products = productService.findAllFoods().stream()
                        .map(f -> (Product) f)
                        .toList();
                    break;
                default:
                    return ResponseEntity.badRequest().build();
            }
        }
        
        List<ProductDto> productDtos = DtoMapper.toProductDtoList(products);
        return ResponseEntity.ok(productDtos);
    }
    
    /**
     * GET /api/products/{id} - Obtener producto por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build(); 
            
        }
        Optional<Product> product = productService.findProductById(id);
        return product.map(p -> ResponseEntity.ok(DtoMapper.toProductDto(p)))
                     .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * GET /api/products/search?name={name} - Buscar productos por nombre
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.findProductsByName(name);
        List<ProductDto> productDtos = DtoMapper.toProductDtoList(products);
        return ResponseEntity.ok(productDtos);
    }
    
    /**
     * GET /api/products/low-stock?threshold={threshold} - Productos con stock bajo
     */
    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductDto>> getProductsWithLowStock(@RequestParam int threshold) {
        List<Product> products = productService.findProductsWithLowStock(threshold);
        List<ProductDto> productDtos = DtoMapper.toProductDtoList(products);
        return ResponseEntity.ok(productDtos);
    }
    
    /**
     * POST /api/products - Crear nuevo producto basado en el tipo especificado en el DTO
     * El campo productType debe ser "Beverage" o "Food"
     */
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product savedProduct = productService.createProductByType(productDto);
        ProductDto responseDto = DtoMapper.toProductDto(savedProduct);
        return ResponseEntity.ok(responseDto);
    }
    
    /**
     * PUT /api/products/{id} - Reemplazar completamente un producto
     * Requiere todos los campos obligatorios en el DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product updatedProduct = productService.updateProduct(id, productDto);
        ProductDto responseDto = DtoMapper.toProductDto(updatedProduct);
        return ResponseEntity.ok(responseDto);
    }
    
    /**
     * PATCH /api/products/{id} - Actualizar parcialmente un producto
     * Solo actualiza los campos no null del DTO
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patchProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product updatedProduct = productService.patchProduct(id, productDto);
        ProductDto responseDto = DtoMapper.toProductDto(updatedProduct);
        return ResponseEntity.ok(responseDto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
