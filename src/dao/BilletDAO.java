package dao;

import java.time.LocalDate;


import dao.interfaces.IBilletDAO;
import model.Billet;
import model.TypeTransport;
import model.StatutBillet;
import util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BilletDAO implements IBilletDAO {
    private final Connection connection;

    public BilletDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void addBillet(Billet billet) {
        String query = "INSERT INTO billet (id, contrat_id, trajet_id, type_transport, prix_achat, prix_vente, date_vente, statut_billet, date_depart, horaire) " +
                "VALUES (?, ?, ?, ?::type_transport, ?, ?, ?, ?::statut_billet, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, billet.getId());
            statement.setObject(2, billet.getContratId());
            statement.setInt(3, billet.getTrajetId());
            statement.setString(4, billet.getTypeTransport().name().toLowerCase());
            statement.setBigDecimal(5, billet.getPrixAchat());
            statement.setBigDecimal(6, billet.getPrixVente());
            statement.setTimestamp(7, billet.getDateVente());
            statement.setString(8, billet.getStatutBillet().name().toLowerCase());
            statement.setDate(9, billet.getDateDepart());
            statement.setTime(10, billet.getHoraire());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Billet> getAllBillets() {
        List<Billet> billets = new ArrayList<>();
        String query = "SELECT * FROM billet";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                UUID id = (UUID) resultSet.getObject("id");
                UUID contratId = (UUID) resultSet.getObject("contrat_id");
                int trajetId = resultSet.getInt("trajet_id");
                TypeTransport typeTransport = TypeTransport.valueOf(resultSet.getString("type_transport").toUpperCase());
                BigDecimal prixAchat = resultSet.getBigDecimal("prix_achat");
                BigDecimal prixVente = resultSet.getBigDecimal("prix_vente");
                Timestamp dateVente = resultSet.getTimestamp("date_vente");
                StatutBillet statutBillet = StatutBillet.valueOf(resultSet.getString("statut_billet").toUpperCase());
                java.sql.Date dateDepart = resultSet.getDate("date_depart");
                java.sql.Time horaire = resultSet.getTime("horaire");
                BigDecimal duree = resultSet.getBigDecimal("duree");
                String nomCompagnie = resultSet.getString("nom_compagnie");
                String villeDepart = resultSet.getString("ville_depart");
                String villeDestination = resultSet.getString("ville_destination");

                Billet billet = new Billet(id, typeTransport, prixAchat, prixVente, dateVente, statutBillet, contratId, trajetId, dateDepart, horaire, duree, nomCompagnie, villeDepart, villeDestination);
                billets.add(billet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billets;
    }

    @Override
    public Billet getBilletById(UUID id) {
        Billet billet = null;
        String query = "SELECT * FROM billet WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                billet = new Billet(
                        id,
                        TypeTransport.valueOf(resultSet.getString("type_transport").toUpperCase()),
                        resultSet.getBigDecimal("prix_achat"),
                        resultSet.getBigDecimal("prix_vente"),
                        resultSet.getTimestamp("date_vente"),
                        StatutBillet.valueOf(resultSet.getString("statut_billet").toUpperCase()),
                        (UUID) resultSet.getObject("contrat_id"),
                        resultSet.getInt("trajet_id"),
                        resultSet.getDate("date_depart"),
                        resultSet.getTime("horaire"),
                        resultSet.getBigDecimal("duree"), // Assurez-vous que ce champ existe dans la table
                        resultSet.getString("nom_compagnie"), // Assurez-vous que ce champ existe dans la table
                        resultSet.getString("ville_depart"), // Assurez-vous que ce champ existe dans la table
                        resultSet.getString("ville_destination") // Assurez-vous que ce champ existe dans la table
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billet;
    }

    @Override
    public void updateBillet(Billet billet) {
        String query = "UPDATE billet SET contrat_id = ?, trajet_id = ?, type_transport = CAST(? AS type_transport), prix_achat = ?, prix_vente = ?, date_vente = ?, statut_billet = CAST(? AS statut_billet), date_depart = ?, horaire = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, billet.getContratId());
            statement.setInt(2, billet.getTrajetId());
            statement.setString(3, billet.getTypeTransport().name().toLowerCase());
            statement.setBigDecimal(4, billet.getPrixAchat());
            statement.setBigDecimal(5, billet.getPrixVente());
            statement.setTimestamp(6, billet.getDateVente());
            statement.setString(7, billet.getStatutBillet().name().toLowerCase());
            statement.setDate(8, billet.getDateDepart());
            statement.setTime(9, billet.getHoraire());
            statement.setObject(10, billet.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBillet(UUID id) {
        String query = "DELETE FROM billet WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



   /* @Override
    public List<Billet> searchBillets(String villeDepart, String villeDestination, String dateDepart) {
        List<Billet> billets = new ArrayList<>();
        String sql = "SELECT b.* FROM billet b " +
                "JOIN trajet t ON b.trajet_id = t.id " +
                "JOIN ville v_depart ON t.ville_depart_id = v_depart.id " +
                "JOIN ville v_destination ON t.ville_destination_id = v_destination.id " +
                "WHERE v_depart.nom = ? AND v_destination.nom = ? AND b.date_depart = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, villeDepart);
            pstmt.setString(2, villeDestination);
            pstmt.setDate(3, Date.valueOf(LocalDate.parse(dateDepart)));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Récupération des informations du billet
                Billet billet = new Billet(
                        UUID.fromString(rs.getString("id")),
                        TypeTransport.valueOf(rs.getString("type_transport").toUpperCase()),  // Convertir en majuscules
                        rs.getBigDecimal("prix_achat"),
                        rs.getBigDecimal("prix_vente"),
                        rs.getTimestamp("date_vente"),
                        StatutBillet.valueOf(rs.getString("statut_billet").toUpperCase()),
                        UUID.fromString(rs.getString("contrat_id")),
                        rs.getInt("trajet_id"),
                        rs.getDate("date_depart"),
                        rs.getTime("horaire")
                );
                billets.add(billet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billets;
    }

    */
//   public List<Billet> searchBillets(String villeDepart, String villeDestination, LocalDate dateDepart) {
//       List<Billet> billets = new ArrayList<>();
//
//       String sql = "SELECT b.id AS billet_id, b.prix_vente, b.horaire, t.duree, p.nom_compagnie, " +
//               "v_dep.nom AS ville_depart, v_dest.nom AS ville_destination, b.trajet_id, b.date_depart " +
//               "FROM billet b " +
//               "JOIN trajet t ON b.trajet_id = t.id " +
//               "JOIN ville v_dep ON t.ville_depart_id = v_dep.id " +
//               "JOIN ville v_dest ON t.ville_destination_id = v_dest.id " +
//               "JOIN contrat c ON b.contrat_id = c.id " +
//               "JOIN partenaire p ON c.partenaire_id = p.id " +
//               "WHERE v_dep.nom = ? AND v_dest.nom = ? AND b.date_depart = ?";
//
//       try (PreparedStatement ps = connection.prepareStatement(sql)) {
//           ps.setString(1, villeDepart);
//           ps.setString(2, villeDestination);
//           ps.setDate(3, Date.valueOf(dateDepart));
//
//           try (ResultSet rs = ps.executeQuery()) {
//               // Print the column names for debugging
//               ResultSetMetaData rsmd = rs.getMetaData();
//               for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                   System.out.println("Column " + i + ": " + rsmd.getColumnName(i));
//               }
//
//               while (rs.next()) {
//                   Billet billet = new Billet(
//                           UUID.fromString(rs.getString("billet_id")),
//                           null, // Définir la logique pour obtenir le TypeTransport si nécessaire
//                           null, // Définir la logique pour obtenir le prix d'achat si nécessaire
//                           rs.getBigDecimal("prix_vente"),
//                           null, // Définir la logique pour obtenir la date de vente si nécessaire
//                           null, // Définir la logique pour obtenir le statut du billet si nécessaire
//                           null, // Définir la logique pour obtenir le contrat ID si nécessaire
//                           rs.getInt("trajet_id"),
//                           rs.getDate("date_depart"), // Assurez-vous que cette colonne existe
//                           rs.getTime("horaire"),
//                           rs.getBigDecimal("duree"),
//                           rs.getString("nom_compagnie"),
//                           rs.getString("ville_depart"),
//                           rs.getString("ville_destination")
//                   );
//
//                   billets.add(billet);
//               }
//           }
//       } catch (SQLException e) {
//           e.printStackTrace();
//       }
//
//       return billets;
//   }


    public List<Billet> searchBillets(String villeDepart, String villeDestination, LocalDate dateDepart) {
        List<Billet> billets = new ArrayList<>();

        String sql = "SELECT b.id AS billet_id, b.prix_vente, b.horaire, t.duree, p.nom_compagnie, " +
                "v_dep.nom AS ville_depart, v_dest.nom AS ville_destination, b.trajet_id, b.date_depart, " +
                "b.type_transport, b.prix_achat, b.date_vente, b.statut_billet, b.contrat_id " + // Ensure correct column name
                "FROM billet b " +
                "JOIN trajet t ON b.trajet_id = t.id " +
                "JOIN ville v_dep ON t.ville_depart_id = v_dep.id " +
                "JOIN ville v_dest ON t.ville_destination_id = v_dest.id " +
                "JOIN contrat c ON b.contrat_id = c.id " +
                "JOIN partenaire p ON c.partenaire_id = p.id " +
                "WHERE v_dep.nom = ? AND v_dest.nom = ? AND b.date_depart = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, villeDepart);
            ps.setString(2, villeDestination);
            ps.setDate(3, java.sql.Date.valueOf(dateDepart)); // Convert LocalDate to java.sql.Date

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Convert Timestamp to LocalDateTime and then LocalDate
                    Timestamp dateVenteTimestamp = rs.getTimestamp("date_vente");
                    LocalDate dateVente = dateVenteTimestamp != null ? dateVenteTimestamp.toLocalDateTime().toLocalDate() : null;

                    Billet billet = new Billet(
                            UUID.fromString(rs.getString("billet_id")),
                            TypeTransport.valueOf(rs.getString("type_transport").toUpperCase()), // Ensure this conversion is correct
                            rs.getBigDecimal("prix_achat"),
                            rs.getBigDecimal("prix_vente"),
                            dateVenteTimestamp, // Pass Timestamp here
                            StatutBillet.valueOf(rs.getString("statut_billet").toUpperCase()), // Ensure this conversion is correct
                            rs.getObject("contrat_id", UUID.class), // Ensure "contrat_id" is in the ResultSet
                            rs.getInt("trajet_id"),
                            rs.getDate("date_depart"),
                            rs.getTime("horaire"),
                            rs.getBigDecimal("duree"),
                            rs.getString("nom_compagnie"),
                            rs.getString("ville_depart"),
                            rs.getString("ville_destination")
                    );

                    billets.add(billet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billets;
    }


//    public List<Billet> searchBillets(String villeDepart, String villeDestination, LocalDate dateDepart) {
//        List<Billet> billets = new ArrayList<>();
//
//        String sql = "WITH trajets_directs AS (" +
//                "    SELECT b.id AS billet_id, b.prix_vente, b.horaire, t.duree, p.nom_compagnie, " +
//                "           v_dep.nom AS ville_depart, v_dest.nom AS ville_destination, b.trajet_id, b.date_depart, " +
//                "           b.type_transport, b.prix_achat, b.date_vente, b.statut_billet " +
//                "    FROM billet b " +
//                "    JOIN trajet t ON b.trajet_id = t.id " +
//                "    JOIN ville v_dep ON t.ville_depart_id = v_dep.id " +
//                "    JOIN ville v_dest ON t.ville_destination_id = v_dest.id " +
//                "    JOIN contrat c ON b.contrat_id = c.id " +
//                "    JOIN partenaire p ON c.partenaire_id = p.id " +
//                "    WHERE v_dep.nom = ? AND v_dest.nom = ? AND b.date_depart = ?" +
//                "), " +
//                "trajets_composes AS (" +
//                "    SELECT b1.id AS billet_id_1, b2.id AS billet_id_2, b1.prix_vente AS prix_vente1, b2.prix_vente AS prix_vente2, " +
//                "           t1.duree AS duree1, t2.duree AS duree2, p1.nom_compagnie AS nom_compagnie1, p2.nom_compagnie AS nom_compagnie2, " +
//                "           v_dep1.nom AS ville_depart1, v_dest1.nom AS ville_destination1, v_dep2.nom AS ville_depart2, v_dest2.nom AS ville_destination2, " +
//                "           b1.date_depart AS date_depart1, b2.date_depart AS date_depart2, b1.horaire AS horaire1, b2.horaire AS horaire2 " +
//                "    FROM trajet t1 " +
//                "    JOIN trajet t2 ON t1.ville_destination_id = t2.ville_depart_id " +
//                "    JOIN billet b1 ON t1.id = b1.trajet_id " +
//                "    JOIN billet b2 ON t2.id = b2.trajet_id " +
//                "    JOIN ville v_dep1 ON t1.ville_depart_id = v_dep1.id " +
//                "    JOIN ville v_dest1 ON t1.ville_destination_id = v_dest1.id " +
//                "    JOIN ville v_dep2 ON t2.ville_depart_id = v_dep2.id " +
//                "    JOIN ville v_dest2 ON t2.ville_destination_id = v_dest2.id " +
//                "    JOIN partenaire p1 ON b1.contrat_id = p1.id " +
//                "    JOIN partenaire p2 ON b2.contrat_id = p2.id " +
//                "    WHERE v_dep1.nom = ? AND v_dest2.nom = ? AND b1.date_depart <= b2.date_depart" +
//                ") " +
//                "SELECT 'Direct' AS type_trajet, * FROM trajets_directs " +
//                "UNION ALL " +
//                "SELECT 'Combiné' AS type_trajet, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL " +
//                "FROM trajets_composes";
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, villeDepart);
//            ps.setString(2, villeDestination);
//            ps.setDate(3, java.sql.Date.valueOf(dateDepart));
//            ps.setString(4, villeDepart);  // Pour les trajets combinés
//            ps.setString(5, villeDestination);  // Pour les trajets combinés
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    String typeTrajet = rs.getString("type_trajet");
//
//                    if ("Direct".equals(typeTrajet)) {
//                        Billet billet = new Billet(
//                                UUID.fromString(rs.getString("billet_id")),
//                                TypeTransport.valueOf(rs.getString("type_transport").toUpperCase()),
//                                rs.getBigDecimal("prix_achat"),
//                                rs.getBigDecimal("prix_vente"),
//                                rs.getTimestamp("date_vente"),
//                                StatutBillet.valueOf(rs.getString("statut_billet").toUpperCase()),
//                                rs.getObject("contrat_id", UUID.class),
//                                rs.getInt("trajet_id"),
//                                rs.getDate("date_depart"),
//                                rs.getTime("horaire"),
//                                rs.getBigDecimal("duree"),
//                                rs.getString("nom_compagnie"),
//                                rs.getString("ville_depart"),
//                                rs.getString("ville_destination")
//                        );
//                        billets.add(billet);
//                    }
//                    // Trajets combinés : vous devez adapter ce code pour gérer les trajets combinés
//                    else if ("Combiné".equals(typeTrajet)) {
//                        // Traitez les trajets combinés ici
//                        // Par exemple, créer une classe ou un objet spécifique pour gérer les trajets combinés
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return billets;
//    }







//    public List<TrajetCombine> searchCombinatedTrajets(String villeDepart, String villeDestination, LocalDate dateDepart) {
//        List<TrajetCombine> trajetsComposes = new ArrayList<>();
//
//        String sql = "WITH trajets_composes AS (" +
//                "    SELECT b1.id AS billet_id_1, b2.id AS billet_id_2, b1.prix_vente AS prix_vente1, b2.prix_vente AS prix_vente2, " +
//                "           t1.duree AS duree1, t2.duree AS duree2, p1.nom_compagnie AS nom_compagnie1, p2.nom_compagnie AS nom_compagnie2, " +
//                "           v_dep1.nom AS ville_depart1, v_dest1.nom AS ville_destination1, v_dep2.nom AS ville_depart2, v_dest2.nom AS ville_destination2, " +
//                "           b1.date_depart AS date_depart1, b2.date_depart AS date_depart2, b1.horaire AS horaire1, b2.horaire AS horaire2 " +
//                "    FROM trajet t1 " +
//                "    JOIN trajet t2 ON t1.ville_destination_id = t2.ville_depart_id " +
//                "    JOIN billet b1 ON t1.id = b1.trajet_id " +
//                "    JOIN billet b2 ON t2.id = b2.trajet_id " +
//                "    JOIN ville v_dep1 ON t1.ville_depart_id = v_dep1.id " +
//                "    JOIN ville v_dest1 ON t1.ville_destination_id = v_dest1.id " +
//                "    JOIN ville v_dep2 ON t2.ville_depart_id = v_dep2.id " +
//                "    JOIN ville v_dest2 ON t2.ville_destination_id = v_dest2.id " +
//                "    JOIN partenaire p1 ON b1.contrat_id = p1.id " +
//                "    JOIN partenaire p2 ON b2.contrat_id = p2.id " +
//                "    WHERE v_dep1.nom = ? AND v_dest2.nom = ? AND b1.date_depart <= b2.date_depart" +
//                ") " +
//                "SELECT *, " +
//                "       (b1.prix_vente + b2.prix_vente) AS prix_total, " +
//                "       (t1.duree + t2.duree) AS duree_totale " +
//                "FROM trajets_composes";
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, villeDepart);
//            ps.setString(2, villeDestination);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    Billet billet1 = new Billet(
//                            UUID.fromString(rs.getString("billet_id_1")),
//                            TypeTransport.valueOf(rs.getString("type_transport").toUpperCase()),
//                            rs.getBigDecimal("prix_achat1"),
//                            rs.getBigDecimal("prix_vente1"),
//                            rs.getTimestamp("date_vente1"),
//                            StatutBillet.valueOf(rs.getString("statut_billet1").toUpperCase()),
//                            rs.getObject("contrat_id1", UUID.class),
//                            rs.getInt("trajet_id1"),
//                            rs.getDate("date_depart1"),
//                            rs.getTime("horaire1"),
//                            rs.getBigDecimal("duree1"),
//                            rs.getString("nom_compagnie1"),
//                            rs.getString("ville_depart1"),
//                            rs.getString("ville_destination1")
//                    );
//
//                    Billet billet2 = new Billet(
//                            UUID.fromString(rs.getString("billet_id_2")),
//                            TypeTransport.valueOf(rs.getString("type_transport").toUpperCase()),
//                            rs.getBigDecimal("prix_achat2"),
//                            rs.getBigDecimal("prix_vente2"),
//                            rs.getTimestamp("date_vente2"),
//                            StatutBillet.valueOf(rs.getString("statut_billet2").toUpperCase()),
//                            rs.getObject("contrat_id2", UUID.class),
//                            rs.getInt("trajet_id2"),
//                            rs.getDate("date_depart2"),
//                            rs.getTime("horaire2"),
//                            rs.getBigDecimal("duree2"),
//                            rs.getString("nom_compagnie2"),
//                            rs.getString("ville_depart2"),
//                            rs.getString("ville_destination2")
//                    );
//
//                    BigDecimal prixTotal = rs.getBigDecimal("prix_total");
//                    BigDecimal dureeTotale = rs.getBigDecimal("duree_totale");
//
//                    TrajetCombine trajetCombine = new TrajetCombine(
//                            billet1, billet2, prixTotal, dureeTotale,
//                            rs.getString("ville_depart1"), rs.getString("ville_destination2"),
//                            rs.getString("nom_compagnie1"), rs.getString("nom_compagnie2")
//                    );
//
//                    trajetsComposes.add(trajetCombine);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return trajetsComposes;
//    }

}

