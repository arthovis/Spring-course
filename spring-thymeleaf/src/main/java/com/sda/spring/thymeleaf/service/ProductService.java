package com.sda.spring.thymeleaf.service;

import com.sda.spring.thymeleaf.model.Product;
import com.sda.spring.thymeleaf.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //find all
    public List<Product> findAll() {
        log.info("Finding all products");
        return productRepository.findAll();
    }

    //find by id
    public Product findById(long id) {
        log.info("Finding product by id: {}", id);
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
    }

    //save
    @Transactional
    public Product save(Product createRequest) {
        log.info("Save product: {}", createRequest);
        return productRepository.save(createRequest);
    }

    //update
    @Transactional
    public Product update(Product updateRequest, long id) {
        log.info("Updating product with id: {}" + " and request body: {}", id, updateRequest);
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));

        //copy properties from update request to product to update
        productToUpdate.setName(updateRequest.getName());
        productToUpdate.setPrice(updateRequest.getPrice());
        return productRepository.save(productToUpdate);
    }

    //delete
    @Transactional
    public void delete(long id) {
        log.info("deleting product with id: {}", id);
        productRepository.deleteById(id);
    }
}
