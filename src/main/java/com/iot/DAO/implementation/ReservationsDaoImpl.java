package com.iot.DAO.implementation;

import com.iot.DAO.ReservationsDAO;
import com.iot.model.ReservationsEntity;
import com.iot.persistant.ConnectionManager;
import com.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationsDaoImpl implements ReservationsDAO {
    private static final String FIND_ALL = "SELECT * FROM appartments_reservations";
    private static final String DELETE = "DELETE FROM appartments_reservations WHERE reservation_id=?";
    private static final String CREATE = "INSERT appartments_reservations (reservation_id, settlement_date, leave_date, paid) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE appartments_reservations SET settlement_date=?, leave_date=?, paid=? WHERE reservation_id=?";
    private static final String FIND_BY_ID = "SELECT * FROM appartments_reservations WHERE reservation_id=?";

    @Override
    public List<ReservationsEntity> findAll() throws SQLException {
        List<ReservationsEntity> reservations = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    reservations.add((ReservationsEntity) new Transformer(ReservationsEntity.class).fromResultSetToEntity(resultSet));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return reservations;
    }

    @Override
    public ReservationsEntity findById(Integer id) throws SQLException {
        ReservationsEntity entity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    entity = (ReservationsEntity) new Transformer(ReservationsEntity.class).fromResultSetToEntity(resultSet);
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
    public int create(ReservationsEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getSettlementDate());
            ps.setString(3, entity.getLeaveDate());
            ps.setInt(4, entity.getPaid());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(ReservationsEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, entity.getSettlementDate());
            ps.setString(2, entity.getLeaveDate());
            ps.setInt(3, entity.getPaid());
            ps.setInt(4, entity.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
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


