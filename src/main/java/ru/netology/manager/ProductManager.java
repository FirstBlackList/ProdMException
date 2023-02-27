package ru.netology.manager;

import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;

public class ProductManager {
    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(Product product) {
        repository.saveProduct(product);
    }

    public Product[] searchByName(String name) {
        Product[] result = new Product[0];
        for (Product product : repository.findAll()) {
            if (matches(product, name)) {
                Product[] tmp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        return product.getName().contains(search);
    }
}
