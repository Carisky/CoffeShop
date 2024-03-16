package org.example.models.Order;

import org.example.Connectionfactory.ConnectionFactory;
import org.example.interfaces.ICRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements ICRUD<Order> {
    private final Connection connection;

    public OrderDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void create(Order order) {
        try {
            String sql = "INSERT INTO coffeeshop.orders (customer_id, staff_id, menu_id, order_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, order.getCustomerId());
                statement.setInt(2, order.getStaffId());
                statement.setInt(3, order.getMenuId());
                statement.setTimestamp(4, order.getOrderDate());

                statement.executeUpdate();

                // Получаем сгенерированный ключ (ID)
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order read(int id) {
        try {
            String sql = "SELECT * FROM coffeeshop.orders WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return extractOrderFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Order order) {
        try {
            String sql = "UPDATE coffeeshop.orders SET customer_id = ?, staff_id = ?, menu_id = ?, order_date = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, order.getCustomerId());
                statement.setInt(2, order.getStaffId());
                statement.setInt(3, order.getMenuId());
                statement.setTimestamp(4, order.getOrderDate());
                statement.setInt(5, order.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM coffeeshop.orders WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.orders";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    orders.add(extractOrderFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private Order extractOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setCustomerId(resultSet.getInt("customer_id"));
        order.setStaffId(resultSet.getInt("staff_id"));
        order.setMenuId(resultSet.getInt("menu_id"));
        order.setOrderDate(resultSet.getTimestamp("order_date"));
        return order;
    }
}
