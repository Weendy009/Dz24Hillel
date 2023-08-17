package com.example.repositories;

import com.example.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    protected List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new Product(1, "Apple", 10.0));
        products.add(new Product(2, "Chicken", 20.0));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
