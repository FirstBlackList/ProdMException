package ru.netology.repository;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;

public class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    Product book = new Book(44, "Harry Potter", 760, "J.K. Rowling");
    Product smartphone = new Smartphone(35, "Samsung", 140_000, "Samsung Group");
    Product smartphone2 = new Smartphone(41, "SONY Xperia", 125_000, "Sony Mobile");
    Product product = new Product(3, "Watch", 44000);

    @Test
    public void shouldSaveOneItem() {
        repository.saveProduct(smartphone);
        Assertions.assertArrayEquals(new Product[]{smartphone}, repository.findAll());
    }

    @Test
    public void shouldFindAll() {
        repository.saveProduct(book);
        repository.saveProduct(smartphone);
        repository.saveProduct(smartphone2);
        repository.saveProduct(product);
        Assertions.assertArrayEquals(new Product[]{book, smartphone, smartphone2, product}, repository.findAll());
    }

    @Test
    public void shouldRemoveById() {
        repository.saveProduct(book);
        repository.saveProduct(smartphone);
        repository.saveProduct(smartphone2);
        repository.saveProduct(product);
        repository.removeById(41);
        Assertions.assertArrayEquals(new Product[]{book, smartphone, product}, repository.findAll());
    }

    @Test
    public void shouldRemoveAllById() {
        repository.saveProduct(book);
        repository.saveProduct(smartphone);
        repository.saveProduct(smartphone2);
        repository.saveProduct(product);
        repository.removeById(44);
        repository.removeById(35);
        repository.removeById(41);
        repository.removeById(3);
        Assertions.assertArrayEquals(new Product[]{}, repository.findAll());
    }

    @Test
    public void testNotFoundExceptionByTheSpecifiedId() {
        repository.saveProduct(book);
        repository.saveProduct(smartphone);
        repository.saveProduct(product);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(41);
        });
    }

    @Test
    public void testNotFoundExceptionIdDoesNotExist() {
        repository.saveProduct(book);
        repository.saveProduct(smartphone);
        repository.saveProduct(smartphone2);
        repository.saveProduct(product);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(1001);
        });
    }

    @Test//testNotFoundExceptionIdDoesNotExist
    public void testNotFoundExceptionIdDoesNotExistOnTheBorder() {
        repository.saveProduct(book);
        repository.saveProduct(smartphone);
        repository.saveProduct(smartphone2);
        repository.saveProduct(product);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(-1);
        });
    }

    @Test
    public void testAlreadyExistsExceptionProductHasAlreadyBeenAdded() {
        repository.saveProduct(book);
        repository.saveProduct(smartphone);
        repository.saveProduct(smartphone2);
        repository.saveProduct(product);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.saveProduct(product);
        });
    }
}
