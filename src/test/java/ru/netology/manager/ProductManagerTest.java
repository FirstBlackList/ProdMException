package ru.netology.manager;

import ru.netology.repository.ProductRepository;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book = new Book(1, "Samsung. Change management strategies from a global leader in innovation and design",
            760, "Song, Lee");
    Product book2 = new Book(2, "Метро 2033", 100, "Д. Глуховский");
    Product smartphone = new Smartphone(3, "SONY Xperia", 125_000, "Sony Mobile");
    Product smartphone2 = new Smartphone(4, "Samsung", 140_000, "Samsung Group");
    Product product = new Product(5, "Watch", 44000);

    @Test
    void shouldAddMin() {
        manager.addProduct(smartphone);
        Assertions.assertArrayEquals(new Product[]{smartphone}, repository.findAll());
    }

    @Test
    void shouldAddAllMax() {
        manager.addProduct(book);
        manager.addProduct(book2);
        manager.addProduct(smartphone);
        manager.addProduct(smartphone2);
        manager.addProduct(product);
        Assertions.assertArrayEquals(new Product[]{book, book2, smartphone, smartphone2, product}, repository.findAll());
    }

    @Test
    void searchForOneProduct() {
        manager.addProduct(book);
        manager.addProduct(book2);
        manager.addProduct(smartphone);
        manager.addProduct(smartphone2);
        manager.addProduct(product);
        Assertions.assertArrayEquals(new Product[]{book2}, manager.searchByName("Метро 2033"));
    }


    @Test
    void searchForTwoProduct() {
        manager.addProduct(book);
        manager.addProduct(book2);
        manager.addProduct(smartphone);
        manager.addProduct(smartphone2);
        manager.addProduct(product);
        Assertions.assertArrayEquals(new Product[]{book, smartphone2}, manager.searchByName("Samsung"));
    }

    @Test
    void searchForProductAvailability() {
        manager.addProduct(book);
        manager.addProduct(book2);
        manager.addProduct(smartphone);
        manager.addProduct(smartphone2);
        manager.addProduct(product);
        Assertions.assertArrayEquals(new Product[]{}, manager.searchByName("Sony Playstation"));
    }
}
