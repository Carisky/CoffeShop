package org.example.models.dao.OrderDAO;

import org.example.database.database;

import org.example.models.Order.Order;
import org.example.models.Order.OrderDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderDAOTest {
    private OrderDAO orderDAO;
    private final database DB = new database();
    @BeforeEach
    void setUp() {
        orderDAO = new OrderDAO();
    }

    @AfterEach
    void tearDown() {
        DB.REFRESH();
    }

    @Test
    void read_ShouldReturnOrder_WhenCalled() {
        Order expected = new Order(1,1, 1, 1, Timestamp.valueOf("2024-03-15 10:00:00"));
        Order actual = orderDAO.read(1);

        assertEquals(expected,actual);
    }

    @Test
    void create_ShouldCreateOrderInDB_WhenCalled() {
        Order expected = new Order(4,1, 1,1,Timestamp.valueOf("2024-03-15 10:00:00"));
        orderDAO.create(expected);
        Order actual = orderDAO.read(4);

        assertEquals(expected,actual);
    }

    @Test
    void update_ShouldUpdateOrderInDB_WhenCalled() {
        Order expected = new Order(1,2, 2,2,Timestamp.valueOf("2024-02-15 10:20:00"));
        orderDAO.update(expected);
        Order actual = orderDAO.read(1);

        assertEquals(expected,actual);

    }

    @Test
    void delete_ShouldDeleteOrderInDB_WhenCalled() {
        Order expected = null;
        orderDAO.delete(1);

        Order actual = orderDAO.read(1);

        assertNull(actual);
    }

    @Test
    void testGetAll() {

    }
}
