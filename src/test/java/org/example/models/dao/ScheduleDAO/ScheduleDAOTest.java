package org.example.models.dao.ScheduleDAO;

import org.example.database.database;
import org.example.models.Schedule.Schedule;
import org.example.models.Schedule.ScheduleDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ScheduleDAOTest {
    private ScheduleDAO scheduleDAO;
    private final database DB = new database();
    @BeforeEach
    void setUp() {
        DB.REFRESH();
        scheduleDAO = new ScheduleDAO();
    }

    @AfterEach
    void tearDown() {
        DB.REFRESH();
    }

    @Test
    void read_ShouldReturnSchedule_WhenCalled() {
        Schedule expected = new Schedule(1,1, "Monday", Time.valueOf("08:00:00") , Time.valueOf("16:00:00"));
        Schedule actual = scheduleDAO.read(1);

        assertEquals(expected,actual);
    }

    @Test
    void create_ShouldCreateScheduleInDB_WhenCalled() {
        Schedule expected = new Schedule(4,1, "Monday", Time.valueOf("08:00:00") , Time.valueOf("16:00:00"));
        scheduleDAO.create(expected);
        Schedule actual = scheduleDAO.read(4);

        assertEquals(expected,actual);
    }

    @Test
    void update_ShouldUpdateScheduleInDB_WhenCalled() {
        Schedule expected = new Schedule(1,1, "Tuesday", Time.valueOf("08:00:00") , Time.valueOf("16:00:00"));
        scheduleDAO.update(expected);
        Schedule actual = scheduleDAO.read(1);

        assertEquals(expected,actual);
    }

    @Test
    void delete_ShouldDeleteScheduleInDB_WhenCalled() {
        scheduleDAO.delete(1);

        Schedule actual = scheduleDAO.read(1);

        assertNull(actual);
    }

    @Test
    void getAll_ShouldReturnAllSchedulesFromDB_WhenCalled() {
        List<Schedule> expected = new ArrayList<>();
        expected.add(new Schedule(1,1, "Monday", Time.valueOf("08:00:00"), Time.valueOf("16:00:00")));
        expected.add(new Schedule(2,2, "Tuesday", Time.valueOf("09:00:00"), Time.valueOf("17:00:00")));
        expected.add(new Schedule(3,3, "Wednesday", Time.valueOf("10:00:00"), Time.valueOf("18:00:00")));

        List<Schedule> actual = new ArrayList<>(scheduleDAO.getAll());

        assertEquals(expected,actual);
    }
}
