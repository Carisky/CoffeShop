package org.example.models.StaffMember;

import org.example.Connectionfactory.ConnectionFactory;
import org.example.interfaces.ICRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffMemberDAO implements ICRUD<StaffMember> {
    private final Connection connection;

    public StaffMemberDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void create(StaffMember staffMember) {
        try {
            String sql = "INSERT INTO coffeeshop.staff (full_name, contact_phone, contact_email, position) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, staffMember.getFullName());
                statement.setString(2, staffMember.getContactPhone());
                statement.setString(3, staffMember.getContactEmail());
                statement.setString(4, staffMember.getPosition());

                statement.executeUpdate();

                // Получаем сгенерированный ключ (ID)
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    staffMember.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StaffMember read(int id) {
        try {
            String sql = "SELECT * FROM coffeeshop.staff WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return extractStaffMemberFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(StaffMember staffMember) {
        try {
            String sql = "UPDATE coffeeshop.staff SET full_name = ?, contact_phone = ?, contact_email = ?, position = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, staffMember.getFullName());
                statement.setString(2, staffMember.getContactPhone());
                statement.setString(3, staffMember.getContactEmail());
                statement.setString(4, staffMember.getPosition());
                statement.setInt(5, staffMember.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM coffeeshop.staff WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StaffMember> getAll() {
        List<StaffMember> staffMembers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.staff";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    staffMembers.add(extractStaffMemberFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffMembers;
    }

    public List<StaffMember> searchByFullName(String fullName) {
        List<StaffMember> staffMembers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM coffeeshop.staff WHERE full_name LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + fullName + "%");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    staffMembers.add(extractStaffMemberFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffMembers;
    }

    public StaffMember searchByFullNameAndPosition(String fullName, String position) {
        try {
            String sql = "SELECT * FROM coffeeshop.staff WHERE full_name LIKE ? AND position = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + fullName + "%");
                statement.setString(2, position);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return extractStaffMemberFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private StaffMember extractStaffMemberFromResultSet(ResultSet resultSet) throws SQLException {
        StaffMember staffMember = new StaffMember();
        staffMember.setId(resultSet.getInt("id"));
        staffMember.setFullName(resultSet.getString("full_name"));
        staffMember.setContactPhone(resultSet.getString("contact_phone"));
        staffMember.setContactEmail(resultSet.getString("contact_email"));
        staffMember.setPosition(resultSet.getString("position"));
        return staffMember;
    }
}
