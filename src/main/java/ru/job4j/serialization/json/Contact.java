package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 2.2.4. Сериализация
 * 2. Формат JSON [#313164]
 * 4. JAXB. Преобразование XML в POJO. [#315063]
 * Модель данных Contact.
 * 5. Преобразование JSON в POJO. JsonObject [#315064]
 *
 * @author Dmitry
 * @since 06.12.2021
 */

@XmlRootElement(name = "contact")
public class Contact {
    @XmlAttribute
    private String phone;

    public Contact() {
    }

    public Contact(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{" + "phone='"
                + phone + '\'' + '}';
    }
}
