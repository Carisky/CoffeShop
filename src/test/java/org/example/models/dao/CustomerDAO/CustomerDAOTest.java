package org.example.models.dao.CustomerDAO;

import org.example.database.database;
import org.example.models.Customer.Customer;
import org.example.models.Customer.CustomerDAO;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDAOTest {
    private CustomerDAO customerDAO;
    private database DB = new database();
    @BeforeEach
    void setUp() {
        customerDAO = new CustomerDAO();
    }

    @AfterEach
    void tearDown() {
        DB.REFRESH();
    }

    @Test
    void read_ShouldReturnCutomer_WhenCalled() {
        Customer expected = new Customer(1,"John Doe", Date.valueOf("1990-01-01"), "123456789", "john@example.com", new BigDecimal("0.050"));
        Customer actual = customerDAO.read(1);

        System.out.println("expected "+expected);
        System.out.println("actual "+actual);

        assertEquals(expected,actual);
    }

    @Test
    void create_ShouldCreateCustomerInDB_WhenCalled() {
        Customer expected = new Customer(4,"John Don", Date.valueOf("1995-03-02"), "123456789", "johnd@example.com", new BigDecimal("0.060"));
        customerDAO.create(expected);

        Customer actual = customerDAO.read(4);

        System.out.println("expected "+expected);
        System.out.println("actual "+actual);

        assertEquals(expected,actual);


    }

    @Test
    void update_ShouldUpdateCustomerInDB_WhenCalled() {
        Customer expected = new Customer(3,"John Dot", Date.valueOf("1995-03-02"), "123456789", "johnd@example.com", new BigDecimal("0.060"));
        customerDAO.update(expected);

        Customer actual = customerDAO.read(3);

        System.out.println("expected "+expected);
        System.out.println("actual "+actual);

        assertEquals(expected,actual);

    }

    @Test
    void delete_ShouldDeleteCustomerInDB_WhenCalled() {
        Customer expected = null;
        customerDAO.delete(1);

        Customer actual = customerDAO.read(1);

        System.out.println("expected "+expected);
        System.out.println("actual "+actual);

        assertNull(actual);

    }

    @Test
    void testGetAll() {

    }
}
