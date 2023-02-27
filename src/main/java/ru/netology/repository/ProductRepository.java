package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;

public class ProductRepository {
    private Product[] items = new Product[0];

    public void saveProduct(Product item) {
        if (findById(item.getId()) != null) {
            throw new AlreadyExistsException(
                    "Данный элемент с id: " + item.getId() + " уже добавлен в репозиторий"
            );
        }
        Product[] tmp = new Product[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        tmp[tmp.length - 1] = item;
        items = tmp;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Элемент с id: " + id + " не найден."
            );
        }
        Product[] tmp = new Product[items.length - 1];
        int index = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }

    public Product findById(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Product[] findAll() {
        return items;
    }
}
