package ru.job4j.serialization.json;

import org.json.JSONObject;

/**
 * 2.2.4. Сериализация
 * 5. Преобразование JSON в POJO. JsonObject [#315064]
 * Пример рекурсивного зацикливания.
 *
 * @author Dmitry
 * @since 06.11.2021
 */
public class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public static void main(String[] args) {
            A a = new A();
            B b = new B();
            a.setB(b);
            b.setA(a);
            System.out.println(new JSONObject(b));
    }
}
