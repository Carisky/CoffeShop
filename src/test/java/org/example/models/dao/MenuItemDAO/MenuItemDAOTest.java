package org.example.models.dao.MenuItemDAO;


import org.example.database.database;
import org.example.models.MenuItem.MenuItem;
import org.example.models.MenuItem.MenuItemDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MenuItemDAOTest {
    private MenuItemDAO menuItemDAO;
    private final database DB = new database();
    @BeforeEach
    void setUp() {
        menuItemDAO = new MenuItemDAO();
    }

    @AfterEach
    void tearDown() {
        DB.REFRESH();
    }

    @Test
    void read_ShouldReturnMenuItem_WhenCalled() {
        MenuItem expected = new MenuItem(1,"Coffee", new BigDecimal("2.50"), "Espresso");
        MenuItem actual = menuItemDAO.read(1);

        assertEquals(expected,actual);
    }

    @Test
    void create_ShouldCreateMenuItemInDB_WhenCalled() {
        MenuItem expected = new MenuItem(4,"Cake", new BigDecimal("4.50"), "Interhise");
        menuItemDAO.create(expected);
        MenuItem actual = menuItemDAO.read(4);

        assertEquals(expected,actual);
    }

    @Test
    void update_ShouldUpdateMenuitemInDB_WhenCalled() {
        MenuItem expected = new MenuItem(1,"Coffee", new BigDecimal("2.50"), "Latte");
        menuItemDAO.update(expected);
        MenuItem actual = menuItemDAO.read(1);

        assertEquals(expected,actual);

    }

    @Test
    void delete_ShouldDeleteMenuItemInDB_WhenCalled() {
        menuItemDAO.delete(1);

        MenuItem actual = menuItemDAO.read(1);

        assertNull(actual);
    }

    @Test
    void getAll_ShouldReturnAllMenuItemsFromDB_WhenCalled() {
        List<MenuItem> expected = new ArrayList<>();
        expected.add(new MenuItem(1, "Coffee", new BigDecimal("2.50"),"Espresso"));
        expected.add(new MenuItem(2, "Tea",new BigDecimal("2.00"), "Green Tea"));
        expected.add(new MenuItem(3, "Pastry",new BigDecimal("1.75"), "Croissant"));

        List<MenuItem> actual = new ArrayList<>(menuItemDAO.getAll());

        assertEquals(expected,actual);
    }
}

