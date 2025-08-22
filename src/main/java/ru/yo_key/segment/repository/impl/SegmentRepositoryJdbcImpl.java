package ru.yo_key.segment.repository.impl;

import org.springframework.stereotype.Repository;
import ru.yo_key.segment.entity.Segment;
import ru.yo_key.segment.repository.SegmentRepository;
import ru.yo_key.util.SimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SegmentRepositoryJdbcImpl implements SegmentRepository {
    private final SimpleDataSource simpleDataSource;

    //language=SQL
    private final String SQL_INSERT = "INSERT INTO segments (value) VALUES (?) RETURNING id";
    //language=SQL
    private final String SQL_GET_ALL = "SELECT id, value FROM segments";
    //language=SQL
    private final String SQL_GET = "SELECT id, value FROM segments WHERE id=?";
    //language=SQL
    private final String SQL_DELETE = "DELETE FROM segments WHERE id=?";

    public SegmentRepositoryJdbcImpl(SimpleDataSource simpleDataSource) {
        this.simpleDataSource = simpleDataSource;
    }

    @Override
    public Integer save(Segment segment) {
        try (Connection connection = simpleDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, segment.getValue());
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
    public List<Segment> getAll() {
        List<Segment> segments = new ArrayList<>();
        try (Connection connection = simpleDataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_GET_ALL)) {
            while (rs.next()) {
                segments.add(new Segment(rs.getInt("id"), rs.getString("value")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return segments;
    }

    @Override
    public Segment get(Integer id) {
        try (Connection connection = simpleDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Segment(rs.getInt("id"), rs.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

