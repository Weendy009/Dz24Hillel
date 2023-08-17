package com.example.repositories;

import com.example.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private final ProductRepository productRepository = new ProductRepository();
    private final List<Product> products = productRepository.products;
    public void addItem(Product product) {
        products.add(product);
    }

    public void removeItem(int productId) {
        products.removeIf(product -> product.getId() == productId);
    }

    public List<Product> getItems() {
        return products;
    }
}

