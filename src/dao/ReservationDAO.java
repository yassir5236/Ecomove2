package dao;

import dao.interfaces.IReservationDAO;
import model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import util.DatabaseConnection;


public class ReservationDAO implements IReservationDAO {
    private final Connection connection;

    public ReservationDAO() {
        // Assuming the connection is set up somewhere else, injected, or acquired through a utility class
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (id, clientid, statut_reservation) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, reservation.getId());
            ps.setObject(2, reservation.getClientId());
            ps.setString(3, reservation.getStatutReservation());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                reservations.add(mapResultSetToReservation(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public Optional<Reservation> getReservationById(UUID id) {
        String sql = "SELECT * FROM reservation WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToReservation(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE reservation SET clientid = ?, statut_reservation = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, reservation.getClientId());
            ps.setString(2, reservation.getStatutReservation());
            ps.setObject(3, reservation.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReservation(UUID id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reservation> searchReservationByStatut(String statutReservation) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE statut_reservation = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, statutReservation);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reservations.add(mapResultSetToReservation(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    private Reservation mapResultSetToReservation(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        UUID clientId = UUID.fromString(rs.getString("clientid"));
        String statutReservation = rs.getString("statut_reservation");

        return new Reservation(id, clientId, statutReservation);
    }
}
