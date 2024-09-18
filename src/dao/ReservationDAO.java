package dao;

import dao.interfaces.IReservationDAO;
import model.Reservation;

import java.sql.*;
import java.util.*;

import util.DatabaseConnection;


public class ReservationDAO implements IReservationDAO {
    private final Connection connection;

    public ReservationDAO() {
        // Assuming the connection is set up somewhere else, injected, or acquired through a utility class
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void addReservation(Reservation reservation, UUID billetId) {
        String sql1 = "INSERT INTO reservation (id, clientid, statut_reservation) VALUES (?, ?, ?)";
        String sql2 = "INSERT INTO billet_reservation (billet_id, reservation_id) VALUES (?, ?)";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(sql1)) {
                ps.setObject(1, reservation.getId());
                ps.setObject(2, reservation.getClientId());
                // Assurez-vous que statut_reservation est 'Reserve', 'Annule', ou 'Confirme'
                ps.setString(3, reservation.getStatutReservation());
                ps.executeUpdate();
            }

            try (PreparedStatement ps = connection.prepareStatement(sql2)) {
                ps.setObject(1, billetId);
                ps.setObject(2, reservation.getId());
                ps.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
    public Optional<Map<String, Object>> getDetailedReservationById(UUID id) {
        // La requête SQL a été modifiée pour inclure l'ID de la réservation.
        String sql = "SELECT r.id AS reservation_id, c.nom AS client_nom, b.id AS billet_id, b.date_depart AS date_depart, r.statut_reservation, "
                + "v_depart.nom AS ville_depart, v_destination.nom AS ville_destination, t.duree AS duree_trajet "
                + "FROM reservation r "
                + "JOIN client c ON r.clientid = c.id "
                + "JOIN billet_reservation br ON r.id = br.reservation_id "
                + "JOIN billet b ON br.billet_id = b.id "
                + "JOIN trajet t ON b.trajet_id = t.id "
                + "JOIN ville v_depart ON t.ville_depart_id = v_depart.id "
                + "JOIN ville v_destination ON t.ville_destination_id = v_destination.id "
                + "WHERE c.id = ?"; // Utilisation de r.id pour filtrer par ID de réservation

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id); // Paramètre pour l'ID de réservation
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> reservationData = new HashMap<>();

                    // Récupération des données et ajout à la carte
                    reservationData.put("reservation_id", rs.getObject("reservation_id", UUID.class)); // ID de la réservation
                    reservationData.put("client_nom", rs.getString("client_nom"));
                    reservationData.put("billet_id", rs.getObject("billet_id", UUID.class));
                    reservationData.put("date_depart", rs.getDate("date_depart").toLocalDate());
                    reservationData.put("statut_reservation", rs.getString("statut_reservation"));
                    reservationData.put("ville_depart", rs.getString("ville_depart"));
                    reservationData.put("ville_destination", rs.getString("ville_destination"));
                    reservationData.put("duree_trajet", rs.getDouble("duree_trajet"));

                    return Optional.of(reservationData);
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
    public void cancelledReservation(UUID id) {
        String sql = "Update reservation set statut_reservation = 'Annule'  WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
            System.out.println("Reservation Cancelled successfully.");

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
