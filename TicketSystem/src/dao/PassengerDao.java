package dao;


import db.DBConnection;
import models.Passenger;

import java.sql.*;

public class PassengerDao {

    public int saveOrFetchPassenger(Passenger p) {
        int passengerId = -1;
        try (Connection con = DBConnection.getConnection()) {


            String sql = "INSERT INTO passengers (name, age, contact_no, email) VALUES (?, ?, ?, ?) " +
                         "ON DUPLICATE KEY UPDATE name=VALUES(name), age=VALUES(age), contact_no=VALUES(contact_no)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getContactNo());
            ps.setString(4, p.getEmail());
            ps.executeUpdate();

            
            String selectSql = "SELECT passenger_id FROM passengers WHERE email = ?";
            PreparedStatement selectPs = con.prepareStatement(selectSql);
            selectPs.setString(1, p.getEmail());
            ResultSet rs = selectPs.executeQuery();
            if (rs.next()) {
                passengerId = rs.getInt("passenger_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return passengerId;
    }
}

