package org.example.models.dao.ScheduleDAO;

import org.example.database.database;
import org.example.models.Schedule.ScheduleDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ScheduleDAOTest {
    private ScheduleDAO scheduleDAO;
    private final database DB = new database();
    @BeforeEach
    void setUp() {
        scheduleDAO = new ScheduleDAO();
    }

    @AfterEach
    void tearDown() {
        DB.REFRESH();
    }

    @Test
    void read_ShouldReturnSchedule_WhenCalled() {

    }

    @Test
    void create_ShouldCreateOrderInDB_WhenCalled() {
    }

    @Test
    void update_ShouldUpdateOrderInDB_WhenCalled() {
    }

    @Test
    void delete_ShouldDeleteOrderInDB_WhenCalled() {
    }

    @Test
    void testGetAll() {

    }
}
