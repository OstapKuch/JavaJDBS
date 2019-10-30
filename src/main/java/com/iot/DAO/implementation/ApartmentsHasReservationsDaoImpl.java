package com.iot.DAO.implementation;

import com.iot.DAO.ApartmentsHasReservationsDAO;
import com.iot.model.ApartmentsHasReservationsEntity;
import com.iot.persistant.ConnectionManager;
import com.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentsHasReservationsDaoImpl implements ApartmentsHasReservationsDAO {
    private static final String FIND_ALL = "SELECT * FROM appartments_has_appartments_reservations";
    private static final String DELETE = "DELETE FROM appartments_has_appartments_reservations WHERE appartments_reservations_id=?";
    private static final String CREATE = "INSERT appartments_has_appartments_reservations (appartments_id, buyers_id, billings_id, airbnb_wallet_id, appartments_reservations_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE appartments_has_appartments_reservations SET appartments_id=?, buyers_id=?, billings_id=?, airbnb_wallet_id=? WHERE appartments_reservations_id=?";
    private static final String FIND_BY_ID = "SELECT * FROM appartments_has_appartments_reservations WHERE appartments_reservations_id=?";

    @Override
    public List<ApartmentsHasReservationsEntity> findAll() throws SQLException {
        List<ApartmentsHasReservationsEntity> apartments = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    apartments.add((ApartmentsHasReservationsEntity) new Transformer(ApartmentsHasReservationsEntity.class).fromResultSetToEntity(resultSet));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return apartments;
    }

    @Override
    public ApartmentsHasReservationsEntity findById(Integer id) throws SQLException {
        ApartmentsHasReservationsEntity entity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    entity = (ApartmentsHasReservationsEntity) new Transformer(ApartmentsHasReservationsEntity.class).fromResultSetToEntity(resultSet);
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }

    @Override
    public int create(ApartmentsHasReservationsEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getApartmentsId());
            ps.setInt(2, entity.getBuyersId());
            ps.setInt(3, entity.getBillingsId());
            ps.setInt(4, entity.getAirbnbWalletId());
            ps.setInt(5, entity.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(ApartmentsHasReservationsEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setInt(1, entity.getApartmentsId());
            ps.setInt(2, entity.getBuyersId());
            ps.setInt(3, entity.getBillingsId());
            ps.setInt(4, entity.getAirbnbWalletId());
            ps.setInt(5, entity.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 4;
        }


    }

    @Override
    public int delete(Integer id) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }
}


