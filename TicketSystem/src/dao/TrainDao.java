package dao;

import models.Train;
import db.DBConnection;

import java.sql.*;
import java.util.*;

public class TrainDao {

    public List<Train> getAllTrains() {
        List<Train> trains = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM trains";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Train train = new Train();
                train.setTrainId(rs.getInt("train_id"));
                train.setTrainName(rs.getString("train_name"));
                train.setSource(rs.getString("source"));
                train.setDestination(rs.getString("destination"));
                train.setAvailableSeats(rs.getInt("available_seats"));
                trains.add(train);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trains;
    }

    public boolean updateSeat(int trainId, int seats) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE trains SET available_seats = ? WHERE train_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, seats);
            ps.setInt(2, trainId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addTrain(Train train) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO trains (train_name, source, destination, available_seats) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, train.getTrainName());
            ps.setString(2, train.getSource());
            ps.setString(3, train.getDestination());
            ps.setInt(4, train.getAvailableSeats());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
