package ru.yo_key.size.repository.impl;

import org.springframework.stereotype.Repository;
import ru.yo_key.size.entity.Size;
import ru.yo_key.size.repository.SizeRepository;
import ru.yo_key.util.SimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SizeRepositoryJdbcImpl implements SizeRepository {
    private final SimpleDataSource simpleDataSource;

    //language=SQL
    private final String SQL_INSERT = "INSERT INTO sizes (value) VALUES (?) RETURNING id";
    //language=SQL
    private final String SQL_GET_ALL = "SELECT id, value FROM sizes";
    //language=SQL
    private final String SQL_DELETE = "DELETE FROM sizes WHERE id=?";

    public SizeRepositoryJdbcImpl(SimpleDataSource simpleDataSource) {
        this.simpleDataSource = simpleDataSource;
    }

    @Override
    public Integer save(Size size) {
        try (Connection connection = simpleDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, size.getValue());
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
    public List<Size> getAll() {
        List<Size> sizes = new ArrayList<>();
        try (Connection connection = simpleDataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_GET_ALL)) {
            while (rs.next()) {
                sizes.add(new Size(rs.getInt("id"), rs.getString("value")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizes;
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = simpleDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}