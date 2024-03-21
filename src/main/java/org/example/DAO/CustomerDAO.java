package org.example.DAO;

import org.example.DAO.Connectionfactory.ConnectionFactory;
import org.example.interfaces.ICRUD;
import org.example.models.Customer.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICRUD<Customer> {
    private final Connection connection;

    public CustomerDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void create(Customer customer) {
        try {
            String sql = "INSERT INTO coffeeshop.customers (full_name, birth_date, contact_phone, contact_email, discount) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, customer.getFullName());
                statement.setDate(2, new java.sql.Date(customer.getBirthDate().getTime()));
                statement.setString(3, customer.getContactPhone());
                statement.setString(4, customer.getContactEmail());
                statement.setBigDecimal(5, customer.getDiscount());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer read(int id) {
        try {
            String sql = "SELECT * FROM coffeeshop.customers WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return extractCustomerFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Customer customer) {
        try {
            String sql = "UPDATE coffeeshop.customers SET full_name = ?, birth_date = ?, contact_phone = ?, contact_email = ?, discount = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, customer.getFullName());
                statement.setDate(2, new java.sql.Date(customer.getBirthDate().getTime()));
                statement.setString(3, customer.getContactPhone());
                statement.setString(4, customer.getContactEmail());
                statement.setBigDecimal(5, customer.getDiscount());
                statement.setInt(6, customer.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM coffeeshop.customers WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.customers";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    customers.add(extractCustomerFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public Customer searchByFullName(String fullName) {
        try {
            String sql = "SELECT * FROM coffeeshop.customers WHERE full_name LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + fullName + "%");
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return extractCustomerFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setFullName(resultSet.getString("full_name"));
        customer.setBirthDate(resultSet.getDate("birth_date"));
        customer.setContactPhone(resultSet.getString("contact_phone"));
        customer.setContactEmail(resultSet.getString("contact_email"));
        customer.setDiscount(resultSet.getBigDecimal("discount"));
        return customer;
    }
}

