package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

/**
 * 2.5.3. LSP
 * 1. Хранилище продуктов [#852]
 * Класс реализует сортировку продуктов в хранилище.
 *
 * @author Dmitry.
 * @since 09.02.2022.
 */
public class ControllQuality implements Sorter<Product> {
    private List<Storage<Product>> storages;

    public ControllQuality(List<Storage<Product>> storages) {
        this.storages = storages;
    }

    /**
     * Метод распределяет продукты по условию в разные хранилища.
     *
     * @param product Product.
     */
    public void sorterQuality(Product product) {
        for (Storage<Product> storage : this.storages) {
            sorter(product, storage);
        }
    }

    /**
     * Метод добавляет в хранилище на основании условия.
     *
     * @param product Product.
     * @param storage Storage.
     */
    @Override
    public void sorter(Product product, Storage<Product> storage) {
        if (storage.accept(product)) {
            storage.add(product);
        }
    }

    /**
     * Метод извлекает все продукты и перераспределять их заново.
     */
    @Override
    public void resort() {
        List<Product> products = new ArrayList<>();
        this.storages.forEach(s -> products.addAll(s.findAll()));
        for (Storage<Product> s : this.storages) {
            products.forEach(p -> sorter(p, s));
        }
        products.clear();
    }
}
