package com.techlab.backend.service.api;

import com.techlab.backend.dto.ProductDto;
import com.techlab.backend.model.Product;
import com.techlab.backend.model.Beverage;
import com.techlab.backend.model.Food;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    
    /**
     * Encuentra todos los productos (Beverages y Foods)
     * @return Lista de todos los productos
     */
    List<Product> findAllProducts();
    
    /**
     * Encuentra un producto por su ID
     * @param id ID del producto
     * @return Optional con el producto si existe
     */
    Optional<Product> findProductById(Long id);
    
    /**
     * Guarda un producto
     * @param product Producto a guardar
     * @return Producto guardado
     */
    Product saveProduct(Product product);
    
    /**
     * Crea un producto basado en el tipo especificado en el DTO
     * @param productDto DTO con la información del producto a crear
     * @return Producto creado y guardado
     */
    Product createProductByType(ProductDto productDto);
    
    /**
     * Elimina un producto por su ID
     * @param id ID del producto a eliminar
     */
    void deleteProduct(Long id);

    
    /**
     * Encuentra solo las bebidas
     * @return Lista de bebidas
     */
    List<Beverage> findAllBeverages();
    
    /**
     * Encuentra solo los alimentos
     * @return Lista de alimentos
     */
    List<Food> findAllFoods();
    
    /**
     * Encuentra productos por nombre (búsqueda parcial)
     * @param name Nombre o parte del nombre
     * @return Lista de productos que coinciden
     */
    List<Product> findProductsByName(String name);
    
    /**
     * Encuentra productos con stock bajo
     * @param threshold Umbral de stock bajo
     * @return Lista de productos con stock menor al umbral
     */
    List<Product> findProductsWithLowStock(int threshold);
    
    /**
     * Actualiza un producto usando los campos proporcionados en el DTO
     * @param id ID del producto a actualizar
     * @param productDto DTO con los campos a actualizar
     * @return Producto actualizado
     */
    Product updateProduct(Long id, ProductDto productDto);
    
    /**
     * Actualiza parcialmente un producto (solo campos no null del DTO)
     * @param id ID del producto a actualizar
     * @param productDto DTO con los campos a actualizar parcialmente
     * @return Producto actualizado
     */
    Product patchProduct(Long id, ProductDto productDto);
}
