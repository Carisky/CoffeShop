package org.example.models.dao.StaffMemberDAO;

import org.example.database.database;
import org.example.models.StaffMember.StaffMember;
import org.example.DAO.StaffMemberDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StaffMemberDAOTest {
    private StaffMemberDAO staffMemberDAO;
    private final database DB = new database();
    @BeforeEach
    void setUp() {
        DB.REFRESH();
        staffMemberDAO = new StaffMemberDAO();
    }

    @AfterEach
    void tearDown() {
        DB.REFRESH();
    }

    @Test
    void read_ShouldReturnStaff_WhenCalled() {
        StaffMember expected = new StaffMember(1,"Mary Brown", "741852963", "mary@example.com", "Barista");
        StaffMember actual = staffMemberDAO.read(1);

        assertEquals(expected,actual);
    }

    @Test
    void create_ShouldCreateStaffInDB_WhenCalled() {
        StaffMember expected = new StaffMember(4,"Mary Browne", "914678924", "mary@test.com", "Barista");
        staffMemberDAO.create(expected);
        StaffMember actual = staffMemberDAO.read(4);

        assertEquals(expected,actual);
    }

    @Test
    void update_ShouldUpdateStaffInDB_WhenCalled() {
        StaffMember expected = new StaffMember(1,"Mary Browning", "914678924", "mary@update.com", "Barista");
        staffMemberDAO.update(expected);
        StaffMember actual = staffMemberDAO.read(1);

        assertEquals(expected,actual);
    }

    @Test
    void delete_ShouldDeleteStaffInDB_WhenCalled() {
        staffMemberDAO.delete(1);

        StaffMember actual = staffMemberDAO.read(1);

        assertNull(actual);
    }

    @Test
    void getAll_ShouldReturnAllStaffMembersFromDB_WhenCalled() {
        List<StaffMember> expected = new ArrayList<>();
        expected.add(new StaffMember(1,"Mary Brown", "741852963", "mary@example.com", "Barista"));
        expected.add(new StaffMember(2,"Tom Wilson", "852369741", "tom@example.com", "Waiter"));
        expected.add(new StaffMember(3,"Emily Jones", "369852147", "emily@example.com", "Chef"));

        List<StaffMember> actual = new ArrayList<>(staffMemberDAO.getAll());

        assertEquals(expected,actual);
    }
}
