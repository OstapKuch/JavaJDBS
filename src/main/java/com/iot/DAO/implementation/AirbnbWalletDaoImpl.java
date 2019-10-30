package com.iot.DAO.implementation;

import com.iot.DAO.AirbnbWalletDAO;
import com.iot.model.AirbnbWalletEntity;
import com.iot.persistant.ConnectionManager;
import com.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirbnbWalletDaoImpl implements AirbnbWalletDAO {
    private static final String FIND_ALL = "SELECT * FROM airbnb_wallet";
    private static final String DELETE = "DELETE FROM airbnb_wallet WHERE wallet_id=?";
    private static final String CREATE = "INSERT airbnb_wallet (wallet_id, money) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE airbnb_wallet SET money=? WHERE wallet_id=?";
    private static final String FIND_BY_ID = "SELECT * FROM airbnb_wallet WHERE wallet_id=?";

    @Override
    public List<AirbnbWalletEntity> findAll() throws SQLException {
        List<AirbnbWalletEntity> employees = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    employees.add((AirbnbWalletEntity) new Transformer(AirbnbWalletEntity.class).fromResultSetToEntity(resultSet));
                }
            }
        }
        return employees;
    }

    @Override
    public AirbnbWalletEntity findById(Integer id) throws SQLException {
        AirbnbWalletEntity entity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    entity = (AirbnbWalletEntity) new Transformer(AirbnbWalletEntity.class).fromResultSetToEntity(resultSet);
                    break;
                }
            }
        }
        return entity;
    }

    @Override
    public int create(AirbnbWalletEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(CREATE)) {
            ps.setInt(2, entity.getMoney());
            ps.setInt(1, entity.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(AirbnbWalletEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setInt(1, entity.getMoney());
            ps.setInt(2, entity.getId());

            return ps.executeUpdate();
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


