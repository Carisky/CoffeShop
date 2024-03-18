package org.example.models.dao.CustomerDAO;

import org.example.database.database;
import org.example.models.Customer.Customer;
import org.example.models.Customer.CustomerDAO;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDAOTest {
    private CustomerDAO customerDAO;
    private final database DB = new database();
    @BeforeEach
    void setUp() {
        DB.REFRESH();
        customerDAO = new CustomerDAO();
    }

    @AfterEach
    void tearDown() {
        DB.REFRESH();
    }

    @Test
    void read_ShouldReturnCustomer_WhenCalled() {
        Customer expected = new Customer(1,"John Doe", Date.valueOf("1990-01-01"), "123456789", "john@example.com", new BigDecimal("0.050"));
        Customer actual = customerDAO.read(1);

        assertEquals(expected,actual);
    }

    @Test
    void create_ShouldCreateCustomerInDB_WhenCalled() {
        Customer expected = new Customer(4,"John Don", Date.valueOf("1995-03-02"), "123456789", "johnd@example.com", new BigDecimal("0.060"));
        customerDAO.create(expected);

        Customer actual = customerDAO.read(4);

        assertEquals(expected,actual);
    }

    @Test
    void update_ShouldUpdateCustomerInDB_WhenCalled() {
        Customer expected = new Customer(3,"John Dot", Date.valueOf("1995-03-02"), "123456789", "johnd@example.com", new BigDecimal("0.060"));
        customerDAO.update(expected);

        Customer actual = customerDAO.read(3);

        assertEquals(expected,actual);
    }

    @Test
    void delete_ShouldDeleteCustomerInDB_WhenCalled() {
        customerDAO.delete(1);

        Customer actual = customerDAO.read(1);

        assertNull(actual);
    }

    @Test
    void getAll_ShouldReturnAllCustomersFromDB_WhenCalled() {
        List<Customer> expected = new ArrayList<>();
        expected.add(new Customer(1,"John Doe", Date.valueOf("1990-01-01"), "123456789", "john@example.com", new BigDecimal("0.050")));
        expected.add(new Customer(2,"Alice Smith", Date.valueOf("1985-05-15"), "987654321", "alice@example.com", new BigDecimal("0.100")));
        expected.add(new Customer(3,"Bob Johnson", Date.valueOf("1978-10-20"), "456123789", "bob@example.com", new BigDecimal("0.000")));

        List<Customer> actual = new ArrayList<>(customerDAO.getAll());

        assertEquals(expected,actual);
    }
}
