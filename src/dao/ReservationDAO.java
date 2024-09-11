package dao;

import dao.interfaces.IReservationDAO;
import model.Reservation;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationDAO implements IReservationDAO {
    private final Connection connection ;

    public ReservationDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    @Override
    public boolean addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (id, client_id, billet_id, date_reservation) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, reservation.getId());
            preparedStatement.setObject(2, reservation.getClientId());
            preparedStatement.setObject(3, reservation.getBilletId());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(reservation.getDateReservation()));

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réservation : " + e.getMessage());
        }

        return false;
    }

    @Override
    public Optional<Reservation> findReservationById(UUID reservationId) {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        Reservation reservation = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, reservationId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                reservation = new Reservation(
                        (UUID) resultSet.getObject("id"),
                        (UUID) resultSet.getObject("client_id"),
                        (UUID) resultSet.getObject("billet_id"),
                        resultSet.getTimestamp("date_reservation").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de la réservation : " + e.getMessage());
        }

        return Optional.ofNullable(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        String sql = "SELECT * FROM reservations";
        List<Reservation> reservations = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        (UUID) resultSet.getObject("id"),
                        (UUID) resultSet.getObject("client_id"),
                        (UUID) resultSet.getObject("billet_id"),
                        resultSet.getTimestamp("date_reservation").toLocalDateTime()
                );
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }

        return reservations;
    }

    @Override
    public boolean updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET client_id = ?, billet_id = ?, date_reservation = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, reservation.getClientId());
            preparedStatement.setObject(2, reservation.getBilletId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(reservation.getDateReservation()));
            preparedStatement.setObject(4, reservation.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la réservation : " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteReservation(UUID reservationId) {
        String sql = "DELETE FROM reservations WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, reservationId);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la réservation : " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Reservation> findReservationsByClientId(UUID clientId) {
        String sql = "SELECT * FROM reservations WHERE client_id = ?";
        List<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, clientId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        (UUID) resultSet.getObject("id"),
                        (UUID) resultSet.getObject("client_id"),
                        (UUID) resultSet.getObject("billet_id"),
                        resultSet.getTimestamp("date_reservation").toLocalDateTime()
                );
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des réservations par ID de client : " + e.getMessage());
        }

        return reservations;
    }
}
