package com.example.bai4.service;

import com.example.bai4.model.Category;
import com.example.bai4.model.Product;
import com.example.bai4.repository.CategoryRepository;
import com.example.bai4.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}