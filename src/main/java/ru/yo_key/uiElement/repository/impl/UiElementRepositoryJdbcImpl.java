package ru.yo_key.uiElement.repository.impl;

import org.springframework.stereotype.Repository;
import ru.yo_key.segment.entity.Segment;
import ru.yo_key.uiElement.entity.UiElement;
import ru.yo_key.uiElement.repository.UiElementRepository;
import ru.yo_key.util.SimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UiElementRepositoryJdbcImpl implements UiElementRepository {
    private final SimpleDataSource simpleDataSource;

    //language=SQL
    private final String SQL_INSERT = "INSERT INTO ui_elements (name, value) VALUES (?, ?) RETURNING id";
    //language=SQL
    private final String SQL_GET_ALL = "SELECT id, name, value FROM ui_elements";
    //language=SQL
    private final String SQL_GET = "SELECT id, name, value FROM ui_elements WHERE name=?";
    //language=SQL
    private final String SQL_DELETE = "DELETE FROM ui_elements WHERE name=?";

    public UiElementRepositoryJdbcImpl(SimpleDataSource simpleDataSource) {
        this.simpleDataSource = simpleDataSource;
    }

    @Override
    public Integer save(UiElement uiElement) {
        try (Connection connection = simpleDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, uiElement.getName());
            statement.setString(2, uiElement.getValue());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UiElement> getAll() {
        List<UiElement> uiElements = new ArrayList<>();
        try (Connection connection = simpleDataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_GET_ALL)) {
            while (rs.next()) {
                uiElements.add(new UiElement(rs.getInt("id"), rs.getString("name"), rs.getString("value")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uiElements;
    }

    @Override
    public UiElement get(String name) {
        try (Connection connection = simpleDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new UiElement(rs.getInt("id"), rs.getString("name"), rs.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String name) {
        try (Connection connection = simpleDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
