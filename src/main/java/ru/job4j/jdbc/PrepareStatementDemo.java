package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 2.3.5. JDBC
 * 0.2. PrepareStatement [#379307]
 * пример.
 * Клас вставки данных PrepareStatementDemo.
 *
 * @author Dmitry
 * @since 27.12.2021
 */
public class PrepareStatementDemo {
    private Connection connection;

    public PrepareStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        this.connection = DriverManager.getConnection(url, login, password);
    }

    /**
     * Создает пустую таблицу.
     *
     * @param nameTable String.
     */
    public void createTable(String nameTable) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "create table if not exist ?")) {
            statement.setString(1, nameTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавление столбца в таблицу.
     *
     * @param nameTable  String.
     * @param nameColumn String.
     * @param type       String.
     */
    public void addColumn(String nameTable, String nameColumn, String type) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "ALERT TABLE ? ADD COLUMN ? ?")) {
            statement.setString(1, nameTable);
            statement.setString(2, nameColumn);
            statement.setString(3, type);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Вставка данных в таблицу cities.
     *
     * @param city City
     */
    public void insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "insert into cities(name, population) values (?, ?))",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKey = statement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    city.setId(generatedKey.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Обновление таблицы
     *
     * @param city City
     * @return boolean
     */
    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Удаление записи из таблицы Cities
     *
     * @param id Int.
     * @return boolean.
     */
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }
}
