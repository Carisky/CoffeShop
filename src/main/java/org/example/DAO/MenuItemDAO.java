package org.example.DAO;

import org.example.DAO.Connectionfactory.ConnectionFactory;
import org.example.interfaces.ICRUD;
import org.example.models.MenuItem.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO implements ICRUD<MenuItem> {
    private final Connection connection;

    public MenuItemDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void create(MenuItem menuItem) {
        try {
            String sql = "INSERT INTO coffeeshop.menu (type, name, price) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, menuItem.getType());
                statement.setString(2, menuItem.getName());
                statement.setBigDecimal(3, menuItem.getPrice());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MenuItem read(int id) {
        try {
            String sql = "SELECT * FROM coffeeshop.menu WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return extractMenuItemFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(MenuItem menuItem) {
        try {
            String sql = "UPDATE coffeeshop.menu SET type = ?, name = ?,price = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, menuItem.getType());
                statement.setString(2, menuItem.getName());
                statement.setBigDecimal(3, menuItem.getPrice());
                statement.setInt(4, menuItem.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM coffeeshop.menu WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> getAll() {
        List<MenuItem> menuItems = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.menu";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    menuItems.add(extractMenuItemFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    public List<MenuItem> searchByType(String type) {
        List<MenuItem> menuItems = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.menu WHERE type = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, type);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    menuItems.add(extractMenuItemFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    public MenuItem searchByName(String name) {
        try {
            String sql = "SELECT * FROM coffeeshop.menu WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return extractMenuItemFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private MenuItem extractMenuItemFromResultSet(ResultSet resultSet) throws SQLException {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(resultSet.getInt("id"));
        menuItem.setType(resultSet.getString("type"));
        menuItem.setName(resultSet.getString("name"));
        menuItem.setPrice(resultSet.getBigDecimal("price"));
        return menuItem;
    }


}
