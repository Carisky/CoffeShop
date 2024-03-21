package org.example.models.dao.OrderDAO;

import org.example.database.database;
import org.example.models.Order.Order;
import org.example.DAO.OrderDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderDAOTest {
    private OrderDAO orderDAO;
    private final database DB = new database();
    @BeforeEach
    void setUp() {
        DB.REFRESH();
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
        orderDAO.delete(1);

        Order actual = orderDAO.read(1);

        assertNull(actual);
    }

    @Test
    void getAll_ShouldReturnAllOrdersFromDB_WhenCalled() {
        List<Order> expected = new ArrayList<>();
        expected.add(new Order(1,1, 1, 1, Timestamp.valueOf("2024-03-15 10:00:00")));
        expected.add(new Order(2,2, 2, 2, Timestamp.valueOf("2024-03-16 11:30:00")));
        expected.add(new Order(3,3, 3, 3, Timestamp.valueOf("2024-03-17 12:45:00")));

        List<Order> actual = new ArrayList<>(orderDAO.getAll());

        assertEquals(expected,actual);
    }
}
