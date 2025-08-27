package ru.yo_key.product.repository.impl;

import org.springframework.stereotype.Repository;
import ru.yo_key.product.entity.Image;
import ru.yo_key.product.entity.Product;
import ru.yo_key.product.repository.ProductRepository;
import ru.yo_key.util.SimpleDataSource;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryJdbcImpl implements ProductRepository {
    private final SimpleDataSource simpleDataSource;

    //language=SQL
    private final String SQL_INSERT = "INSERT INTO products " +
            "(name_text, " +
            "standard_price_value, " +
            "discount_percent, " +
            "segment_id) " +
            "VALUES (?, ?, ?, ?)";
    //language=SQL
    private final String SQL_INSERT_IMAGE = "INSERT INTO images " +
            "(image_link, " +
            "is_main, " +
            "product_id) " +
            "VALUES (?, ?, ?)";
    //language=SQL
    private final String SQL_INSERT_PRODUCT_SIZE = "INSERT INTO product_size " +
            "(product_id, " +
            "size_id) " +
            "VALUES (?, ?)";

    //language=SQL
    private final String SQL_SELECT_PRODUCT = "SELECT " +
            "products.id, " +
            "products.name_text, " +
            "products.standard_price_value, " +
            "products.discount_percent, " +
            "products.segment_id " +
            "FROM products " +
            "WHERE id=?";

    //language=SQL
    private final String SQL_SELECT_PRODUCTS_BY_SEGMENT_NAME = "SELECT " +
            "products.id, " +
            "products.name_text, " +
            "products.standard_price_value, " +
            "products.discount_percent, " +
            "products.segment_id " +
            "FROM products " +
            "WHERE segment_value=?";

    //language=SQL
    private final String SQL_SELECT_ALL_PRODUCTS = "SELECT " +
            "products.id, " +
            "products.name_text, " +
            "products.standard_price_value, " +
            "products.discount_percent, " +
            "products.segment_id " +
            "FROM products";

    //language=SQL
    private final String SQL_SELECT_SIZES_BY_PRODUCT_ID = "SELECT " +
            "sizes.id, " +
            "sizes.value " +
            "FROM sizes " +
            "LEFT JOIN product_size ON sizes.id = product_size.size_id " +
            "WHERE product_size.product_id=?";

    //language=SQL
    private final String SQL_SELECT_IMAGES_BY_PRODUCT_ID = "SELECT " +
            "id, " +
            "image_link, " +
            "is_main " +
            "FROM images " +
            "WHERE product_id=?";

    //language=SQL
    final String SQL_DELETE = "DELETE FROM products WHERE id=?";

    public ProductRepositoryJdbcImpl(SimpleDataSource simpleDataSource) {
        this.simpleDataSource = simpleDataSource;
    }

    @Override
    public Integer save(Product product) {
        try (Connection connection = simpleDataSource.getConnection()) {
            Integer productId = null;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getNameText());
            preparedStatement.setDouble(2, product.getStandardPriceValue());
            preparedStatement.setInt(3, product.getDiscountPercent());
            preparedStatement.setInt(4, product.getSegmentId());
            preparedStatement.executeUpdate();

            if (preparedStatement.getGeneratedKeys().next()) {
                productId = preparedStatement.getGeneratedKeys().getInt(1);
            }

            for (Image image : product.getImages()) {
                PreparedStatement preparedStatementImage = connection.prepareStatement(SQL_INSERT_IMAGE);
                preparedStatementImage.setString(1, image.getImageLink());
                preparedStatementImage.setBoolean(2, image.isMain());
                preparedStatementImage.setInt(3, productId);
                preparedStatementImage.executeUpdate();
            }

            for (Integer sizeId : product.getSizeIds()) {
                PreparedStatement preparedStatementProductSize = connection.prepareStatement(SQL_INSERT_PRODUCT_SIZE);
                preparedStatementProductSize.setInt(1, productId);
                preparedStatementProductSize.setInt(2, sizeId);
                preparedStatementProductSize.executeUpdate();
            }

            return productId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBySegmentName(String segmentName) {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = simpleDataSource.getConnection().prepareStatement(SQL_SELECT_PRODUCTS_BY_SEGMENT_NAME);
            preparedStatement.setString(1, segmentName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        null,
                        null,
                        null);

                PreparedStatement preparedStatementSize = simpleDataSource.getConnection().prepareStatement(SQL_SELECT_SIZES_BY_PRODUCT_ID);
                preparedStatementSize.setInt(1, product.getId());
                ResultSet rsSize = preparedStatementSize.executeQuery();
                List<Integer> sizeIds = new ArrayList<>();
                List<String> sizeValues = new ArrayList<>();
                while (rsSize.next()) {
                    sizeIds.add(rsSize.getInt(1));
                    sizeValues.add(rsSize.getString(2));
                }
                product.setSizeIds(sizeIds);
                product.setSizeValues(sizeValues);

                PreparedStatement preparedStatementImage = simpleDataSource.getConnection().prepareStatement(SQL_SELECT_IMAGES_BY_PRODUCT_ID);
                preparedStatementImage.setInt(1, product.getId());
                ResultSet rsImage = preparedStatementImage.executeQuery();
                List<Image> images = new ArrayList<>();
                while (rsImage.next()) {
                    images.add(new Image(
                            rsImage.getInt(1),
                            rsImage.getString(2),
                            rsImage.getBoolean(3)
                    ));
                }
                product.setImages(images);

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = simpleDataSource.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_PRODUCTS);
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        null,
                        null,
                        null);

                PreparedStatement preparedStatementSize = simpleDataSource.getConnection().prepareStatement(SQL_SELECT_SIZES_BY_PRODUCT_ID);
                preparedStatementSize.setInt(1, product.getId());
                ResultSet rsSize = preparedStatementSize.executeQuery();
                List<Integer> sizeIds = new ArrayList<>();
                List<String> sizeValues = new ArrayList<>();
                while (rsSize.next()) {
                    sizeIds.add(rsSize.getInt(1));
                    sizeValues.add(rsSize.getString(2));
                }
                product.setSizeIds(sizeIds);
                product.setSizeValues(sizeValues);

                PreparedStatement preparedStatementImage = simpleDataSource.getConnection().prepareStatement(SQL_SELECT_IMAGES_BY_PRODUCT_ID);
                preparedStatementImage.setInt(1, product.getId());
                ResultSet rsImage = preparedStatementImage.executeQuery();
                List<Image> images = new ArrayList<>();
                while (rsImage.next()) {
                    images.add(new Image(
                            rsImage.getInt(1),
                            rsImage.getString(2),
                            rsImage.getBoolean(3)
                    ));
                }
                product.setImages(images);

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = simpleDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}