package org.example.DAO;

import org.example.DAO.Connectionfactory.ConnectionFactory;
import org.example.interfaces.ICRUD;
import org.example.models.Schedule.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO implements ICRUD<Schedule> {
    private final Connection connection;

    public ScheduleDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void create(Schedule schedule) {
        try {
            String sql = "INSERT INTO coffeeshop.schedule (staff_id, day_of_week, start_time, end_time) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, schedule.getStaffId());
                statement.setString(2, schedule.getDayOfWeek());
                statement.setTime(3, Time.valueOf(schedule.getStartTime().toLocalTime()));
                statement.setTime(4, Time.valueOf(schedule.getEndTime().toLocalTime()));

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Schedule read(int id) {
        try {
            String sql = "SELECT * FROM coffeeshop.schedule WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return extractScheduleFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Schedule schedule) {
        try {
            String sql = "UPDATE coffeeshop.schedule SET staff_id = ?, day_of_week = ?, start_time = ?, end_time = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, schedule.getStaffId());
                statement.setString(2, schedule.getDayOfWeek());
                statement.setTime(3, Time.valueOf(schedule.getStartTime().toLocalTime()));
                statement.setTime(4, Time.valueOf(schedule.getEndTime().toLocalTime()));
                statement.setInt(5, schedule.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM coffeeshop.schedule WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Schedule> getAll() {
        List<Schedule> schedules = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.schedule";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    schedules.add(extractScheduleFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

    public void deleteScheduleByStaffIdAndDay(int staffId, String dayOfWeek) {
        try {
            String sql = "DELETE FROM coffeeshop.schedule WHERE staff_id = ? AND day_of_week = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, staffId);
                statement.setString(2, dayOfWeek);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteScheduleBetweenDates(int staffId, Date startDate, Date endDate) {
        try {
            String sql = "DELETE FROM coffeeshop.schedule WHERE staff_id = ? AND day_of_week BETWEEN ? AND ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, staffId);
                statement.setDate(2, startDate);
                statement.setDate(3, endDate);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public List<Schedule> readByStaffId(int staffId) {
        List<Schedule> schedules = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.schedule WHERE staff_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, staffId);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    schedules.add(extractScheduleFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

    public List<Schedule> readByStaffIdAndDayOfWeek(int staffId, String dayOfWeek) {
        List<Schedule> schedules = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.schedule WHERE staff_id = ? AND day_of_week = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, staffId);
                statement.setString(2, dayOfWeek);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    schedules.add(extractScheduleFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }


    public List<Schedule> getBaristaScheduleForWeek(int staffId) {
        List<Schedule> schedules = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.schedule WHERE staff_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, staffId);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    schedules.add(extractScheduleFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

    // Запрос для получения расписания работы всех бариста на неделю
    public List<Schedule> getAllBaristasScheduleForWeek() {
        List<Schedule> schedules = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.schedule";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    schedules.add(extractScheduleFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

    // Запрос для получения расписания работы всех работников кафе на неделю
    public List<Schedule> getAllStaffScheduleForWeek() {
        List<Schedule> schedules = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.schedule";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    schedules.add(extractScheduleFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }


    private Schedule extractScheduleFromResultSet(ResultSet resultSet) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(resultSet.getInt("id"));
        schedule.setStaffId(resultSet.getInt("staff_id"));
        schedule.setDayOfWeek(resultSet.getString("day_of_week"));
        schedule.setStartTime(Time.valueOf(resultSet.getTime("start_time").toLocalTime()));
        schedule.setEndTime(Time.valueOf(resultSet.getTime("end_time").toLocalTime()));
        return schedule;
    }
}
