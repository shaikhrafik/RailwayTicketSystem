package dao;


import models.*;
import db.DBConnection;

import java.sql.*;
import java.util.*;

public class TicketDao {

	public boolean bookTicket(Passenger p, Train t) {
	    try (Connection con = DBConnection.getConnection()) {
	        String sql = "INSERT INTO tickets (passenger_id, train_id) VALUES (?, ?)";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, p.getPassengerId());
	        ps.setInt(2, t.getTrainId());
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


    public boolean cancelTicket(int ticketId) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM tickets WHERE ticket_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ticketId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ticket> getTicketsByPassenger(String email) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT t.ticket_id, p.*, tr.* FROM tickets t " +
                         "JOIN passengers p ON t.passenger_id = p.passenger_id " +
                         "JOIN trains tr ON t.train_id = tr.train_id " +
                         "WHERE p.email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Train train = new Train();
                train.setTrainId(rs.getInt("train_id"));
                train.setTrainName(rs.getString("train_name"));
                train.setSource(rs.getString("source"));
                train.setDestination(rs.getString("destination"));
                train.setAvailableSeats(rs.getInt("available_seats"));

                Passenger passenger = new Passenger(
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("contact_no"),
                    rs.getString("email"),
                    rs.getInt("passenger_id")
                );

                Ticket ticket = new Ticket(rs.getInt("ticket_id"), passenger, train);
                tickets.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }


    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT t.ticket_id, p.*, tr.* FROM tickets t " +
                         "JOIN passengers p ON t.passenger_id = p.passenger_id " +
                         "JOIN trains tr ON t.train_id = tr.train_id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Train train = new Train();
                train.setTrainId(rs.getInt("train_id"));
                train.setTrainName(rs.getString("train_name"));
                train.setSource(rs.getString("source"));
                train.setDestination(rs.getString("destination"));
                train.setAvailableSeats(rs.getInt("available_seats"));

                Passenger passenger = new Passenger(
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("contact_no"),
                    rs.getString("email"),
                    rs.getInt("passenger_id")
                );

                Ticket ticket = new Ticket(rs.getInt("ticket_id"), passenger, train);
                tickets.add(ticket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    }


