package org.example.DAO;

import org.example.DAO.Connectionfactory.ConnectionFactory;
import org.example.interfaces.ICRUD;
import org.example.models.Customer.Customer;

import java.math.BigDecimal;
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

    public BigDecimal getMinDiscount() {
        BigDecimal minDiscount = null;
        try {
            String sql = "SELECT MIN(discount) AS min_discount FROM coffeeshop.customers";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    minDiscount = resultSet.getBigDecimal("min_discount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return minDiscount;
    }

    public BigDecimal getMaxDiscount() {
        BigDecimal maxDiscount = null;
        try {
            String sql = "SELECT MAX(discount) AS max_discount FROM coffeeshop.customers";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    maxDiscount = resultSet.getBigDecimal("max_discount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxDiscount;
    }

    public List<Customer> getCustomersWithMinDiscount() {
        List<Customer> customers = new ArrayList<>();
        BigDecimal minDiscount = getMinDiscount();
        if (minDiscount != null) {
            try {
                String sql = "SELECT * FROM coffeeshop.customers WHERE discount = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setBigDecimal(1, minDiscount);
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        customers.add(extractCustomerFromResultSet(resultSet));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    public List<Customer> getCustomersWithMaxDiscount() {
        List<Customer> customers = new ArrayList<>();
        BigDecimal maxDiscount = getMaxDiscount();
        if (maxDiscount != null) {
            try {
                String sql = "SELECT * FROM coffeeshop.customers WHERE discount = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setBigDecimal(1, maxDiscount);
                    ResultSet resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        customers.add(extractCustomerFromResultSet(resultSet));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    public BigDecimal getAverageDiscount() {
        BigDecimal averageDiscount = null;
        try {
            String sql = "SELECT AVG(discount) AS avg_discount FROM coffeeshop.customers";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    averageDiscount = resultSet.getBigDecimal("avg_discount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageDiscount;
    }

    public Customer getYoungestCustomer() {
        Customer youngestCustomer = null;
        try {
            String sql = "SELECT * FROM coffeeshop.customers ORDER BY birth_date ASC LIMIT 1";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    youngestCustomer = extractCustomerFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return youngestCustomer;
    }

    public Customer getOldestCustomer() {
        Customer oldestCustomer = null;
        try {
            String sql = "SELECT * FROM coffeeshop.customers ORDER BY birth_date DESC LIMIT 1";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    oldestCustomer = extractCustomerFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oldestCustomer;
    }

    public List<Customer> getCustomersByBirthday(Date birthday) {
        List<Customer> customers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM coffeeshop.customers WHERE birth_date = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, birthday);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    customers.add(extractCustomerFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<Customer> getCustomersWithEmptyEmail() {
        List<Customer> customers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM coffeeshop.customers WHERE contact_email IS NULL OR contact_email = ''";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    customers.add(extractCustomerFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<Customer> getCustomersWhoOrderedDrinksToday() {
        List<Customer> customers = new ArrayList<>();

        try {
            String sql = "SELECT c.* FROM coffeeshop.customers c " +
                    "JOIN coffeeshop.orders o ON c.id = o.customer_id " +
                    "JOIN coffeeshop.menu m ON o.menu_id = m.id " +
                    "WHERE DATE(o.order_date) = CURRENT_DATE AND m.type = 'drink'";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    customers.add(extractCustomerFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public BigDecimal getAverageOrderAmountByDate(Date date) {
        BigDecimal averageAmount = BigDecimal.ZERO;
        try {
            String sql = "SELECT AVG(m.price) AS average_amount FROM coffeeshop.orders o " +
                    "JOIN coffeeshop.menu m ON o.menu_id = m.id " +
                    "WHERE DATE(o.order_date) = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, new java.sql.Date(date.getTime()));
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    averageAmount = resultSet.getBigDecimal("average_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageAmount;
    }

    public BigDecimal getMaxOrderAmountByDate(Date date) {
        BigDecimal maxAmount = BigDecimal.ZERO;
        try {
            String sql = "SELECT MAX(m.price) AS max_amount FROM coffeeshop.orders o " +
                    "JOIN coffeeshop.menu m ON o.menu_id = m.id " +
                    "WHERE DATE(o.order_date) = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, new java.sql.Date(date.getTime()));
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    maxAmount = resultSet.getBigDecimal("max_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxAmount;
    }

    public Customer getCustomerWithMaxOrderAmountByDate(Date date) {
        Customer customer = null;
        try {
            String sql = "SELECT c.* FROM coffeeshop.orders o " +
                    "JOIN coffeeshop.customers c ON o.customer_id = c.id " +
                    "JOIN coffeeshop.menu m ON o.menu_id = m.id " +
                    "WHERE DATE(o.order_date) = ? " +
                    "ORDER BY m.price DESC LIMIT 1";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDate(1, new java.sql.Date(date.getTime()));
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    customer = extractCustomerFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
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

