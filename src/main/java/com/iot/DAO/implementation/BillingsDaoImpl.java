package com.iot.DAO.implementation;

import com.iot.DAO.BillingsDAO;
import com.iot.model.BillingsEntity;
import com.iot.persistant.ConnectionManager;
import com.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingsDaoImpl implements BillingsDAO {
    private static final String FIND_ALL = "SELECT * FROM billings";
    private static final String DELETE = "DELETE FROM billings WHERE billing_d=?";
    private static final String CREATE = "INSERT billings (billing_d, date_payed, buyers_id, sellers_id, price) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE billings SET date_payed=?, buyers_id=?, sellers_id=?, price=? WHERE billing_d=?";
    private static final String FIND_BY_ID = "SELECT * FROM billings WHERE billing_d=?";

    @Override
    public List<BillingsEntity> findAll() throws SQLException {
        List<BillingsEntity> apartments = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    apartments.add((BillingsEntity) new Transformer(BillingsEntity.class).fromResultSetToEntity(resultSet));
                }
            }
        }
        return apartments;
    }

    @Override
    public BillingsEntity findById(Integer id) throws SQLException {
        BillingsEntity entity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    entity = (BillingsEntity) new Transformer(BillingsEntity.class).fromResultSetToEntity(resultSet);
                    break;
                }
            }
        }
        return entity;
    }

    @Override
    public int create(BillingsEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getId());
            ps.setDate(2, (Date) entity.getSettlementDate());
            ps.setInt(3, entity.getBuyersId());
            ps.setInt(4, entity.getSellersId());
            ps.setInt(5, entity.getPrice());

            return ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int update(BillingsEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setDate(1, (Date) entity.getSettlementDate());
            ps.setInt(2, entity.getBuyersId());
            ps.setInt(3, entity.getSellersId());
            ps.setInt(4, entity.getPrice());
            ps.setInt(5, entity.getId());

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


