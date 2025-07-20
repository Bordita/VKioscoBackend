package com.techlab.backend.service.implementation;

import com.techlab.backend.dto.ProductDto;
import com.techlab.backend.model.Product;
import com.techlab.backend.model.Beverage;
import com.techlab.backend.model.Food;
import com.techlab.backend.repository.ProductRepository;
import com.techlab.backend.service.api.ProductService;
import com.techlab.backend.exception.ProductNotFoundException;
import com.techlab.backend.exception.ProductAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }
    
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public Product createProductByType(ProductDto productDto) {
        if (productDto == null) {
            throw new IllegalArgumentException("ProductDto no puede ser nulo");
        }
        
        if (productDto.getName() == null || productDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
        }
        
        Product existingProduct = productRepository.findByNameIgnoreCase(productDto.getName().trim());
        if (existingProduct != null) {
            throw new ProductAlreadyExistsException("Ya existe un producto con el nombre: " + productDto.getName());
        }
        
        if (productDto.getPrice() == null || productDto.getPrice() < 0) {
            throw new IllegalArgumentException("El precio del producto debe ser no negativo");
        }
        if (productDto.getStock() == null || productDto.getStock() < 0) {
            throw new IllegalArgumentException("El stock del producto debe ser no negativo");
        }
        if (productDto.getProductType() == null || productDto.getProductType().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de producto debe ser especificado. Use 'Beverage' o 'Food'");
        }
        
        Product product;
        String type = productDto.getProductType().trim();
        
        switch (type) {
            case "Beverage":
                product = new Beverage(
                    productDto.getName(), 
                    productDto.getPrice(), 
                    productDto.getStock(), 
                    productDto.getDescripcion(),
                    productDto.getImagen(),
                    productDto.getVolume()
                );
                break;
            case "Food":
                product = new Food(
                    productDto.getName(), 
                    productDto.getPrice(), 
                    productDto.getStock(), 
                    productDto.getDescripcion(),
                    productDto.getImagen(),
                    productDto.getExpireDate()
                );
                break;
            default:
                throw new IllegalArgumentException("Tipo de producto inválido: " + type + ". Use 'Beverage' o 'Food'");
        }
        
        return productRepository.save(product);
    }
    
    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Producto con ID " + id + " no encontrado");
        }
        productRepository.deleteById(id);
    }
    
    @Override
    public List<Beverage> findAllBeverages() {
        return productRepository.findByProductType(Beverage.class)
            .stream()
            .map(product -> (Beverage) product)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Food> findAllFoods() {
        return productRepository.findByProductType(Food.class)
            .stream()
            .map(product -> (Food) product)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Product> findProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Override
    public List<Product> findProductsWithLowStock(int threshold) {
        return productRepository.findByStockLessThan(threshold);
    }
    
    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Producto con ID " + id + " no encontrado"));
        
        if (productDto.getName() == null || productDto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es requerido para actualización completa");
        }

        Product productWithSameName = productRepository.findByNameIgnoreCase(productDto.getName().trim());
        if (productWithSameName != null && !productWithSameName.getId().equals(id)) {
            throw new ProductAlreadyExistsException("Ya existe otro producto con el nombre: " + productDto.getName());
        }
        
        if (productDto.getPrice() == null || productDto.getPrice() < 0) {
            throw new IllegalArgumentException("El precio del producto es requerido y debe ser no negativo para actualización completa");
        }
        if (productDto.getStock() == null || productDto.getStock() < 0) {
            throw new IllegalArgumentException("El stock del producto es requerido y debe ser no negativo para actualización completa");
        }
        
        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setStock(productDto.getStock());
        existingProduct.setDescripcion(productDto.getDescripcion()); 
        existingProduct.setImagen(productDto.getImagen());
        
        return productRepository.save(existingProduct);
    }
    
    @Override
    public Product patchProduct(Long id, ProductDto productDto) {

        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Producto con ID " + id + " no encontrado"));
        
        // Actualizar solo los campos que no son null en el DTO
        if (productDto.getName() != null && !productDto.getName().trim().isEmpty()) {
            // Verificar que no exista otro producto con el mismo nombre (excluyendo el actual)
            Product productWithSameName = productRepository.findByNameIgnoreCase(productDto.getName().trim());
            if (productWithSameName != null && !productWithSameName.getId().equals(id)) {
                throw new ProductAlreadyExistsException("Ya existe otro producto con el nombre: " + productDto.getName());
            }
            existingProduct.setName(productDto.getName());
        }
        if (productDto.getPrice() != null && productDto.getPrice() >= 0) {
            existingProduct.setPrice(productDto.getPrice());
        }
        if (productDto.getStock() != null && productDto.getStock() >= 0) {
            existingProduct.setStock(productDto.getStock());
        }
        if (productDto.getDescripcion() != null) {
            existingProduct.setDescripcion(productDto.getDescripcion());
        }
        if (productDto.getImagen() != null) {
            existingProduct.setImagen(productDto.getImagen());
        }
        
        return productRepository.save(existingProduct);
    }
}
