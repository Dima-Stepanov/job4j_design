package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 2.5.3. LSP
 * 1. Хранилище продуктов [#852]
 * Класс реализует хранилище Shop.
 *
 * @author Dmitry
 * @since 09.02.2022
 */
public class Shop implements Storage<Product> {
    private List<Product> shopStore = new ArrayList<>();

    /**
     * Метод добавляет type хранилище
     *
     * @param type Product
     */
    @Override
    public void add(Product type) {
        shopStore.add(type);
    }

    /**
     * Метод добавляет товар в хранилище с присвоением скидки.
     *
     * @param type     Product
     * @param discount float
     */
    @Override
    public void add(Product type, float discount) {
        type.setDiscount(discount);
        add(type);
    }

    /**
     * Метод возвращает все содержимое хранилища.
     *
     * @return List
     */
    @Override
    public List<Product> findAll() {
        return this.shopStore;
    }
}
