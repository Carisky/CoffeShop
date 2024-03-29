package org.example.DAO;

import org.example.DAO.Connectionfactory.ConnectionFactory;
import org.example.interfaces.ICRUD;
import org.example.models.Order.Order;

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

    public List<Order> getOrdersByDate(Date date) {
        List<Order> orders = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.orders WHERE DATE(order_date) = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, date);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    orders.add(extractOrderFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public void deleteOrdersByMenuName(String menuName) {
        try {
            String sql = "DELETE FROM coffeeshop.orders WHERE menu_id IN (SELECT id FROM coffeeshop.menu WHERE name = ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, menuName);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrdersByDessertName(String dessertName) {
        List<Order> orders = new ArrayList<>();

        try {
            String sql = "SELECT o.* FROM coffeeshop.orders o " +
                    "INNER JOIN coffeeshop.menu m ON o.menu_id = m.id " +
                    "WHERE m.name = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, dessertName);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    orders.add(extractOrderFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> getOrdersByDateRange(Date startDate, Date endDate) {
        List<Order> orders = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.orders WHERE DATE(order_date) BETWEEN ? AND ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, new java.sql.Date(startDate.getTime()));
                statement.setDate(2, new java.sql.Date(endDate.getTime()));
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    orders.add(extractOrderFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public int getCountOfDessertOrdersByDate(Date date) {
        int count = 0;

        try {
            String sql = "SELECT COUNT(*) AS dessert_count FROM coffeeshop.orders o " +
                    "JOIN coffeeshop.menu m ON o.menu_id = m.id " +
                    "WHERE m.type = 'dessert' AND DATE(o.order_date) = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, new java.sql.Date(date.getTime()));
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    count = resultSet.getInt("dessert_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public int getCountOfDrinkOrdersByDate(Date date) {
        int count = 0;

        try {
            String sql = "SELECT COUNT(*) AS drink_count FROM coffeeshop.orders o " +
                    "JOIN coffeeshop.menu m ON o.menu_id = m.id " +
                    "WHERE m.type = 'drink' AND DATE(o.order_date) = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, new java.sql.Date(date.getTime()));
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    count = resultSet.getInt("drink_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
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
