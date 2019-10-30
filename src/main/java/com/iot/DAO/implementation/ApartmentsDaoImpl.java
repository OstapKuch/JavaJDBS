package com.iot.DAO.implementation;

import com.iot.DAO.ApartmentsDAO;
import com.iot.model.ApartmentsEntity;
import com.iot.persistant.ConnectionManager;
import com.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentsDaoImpl implements ApartmentsDAO {
    private static final String FIND_ALL = "SELECT * FROM appartments";
    private static final String DELETE = "DELETE FROM appartments WHERE appertment_id=?";
    private static final String CREATE = "INSERT appartments (seller_id, rooms_number, beds_number, hour_price, adress, appertment_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE appartments SET seller_id=?, rooms_number=?, beds_number=?, hour_price=?, adress=? WHERE appertment_id=?";
    private static final String FIND_BY_ID = "SELECT * FROM appartments WHERE appertment_id=?";

    @Override
    public List<ApartmentsEntity> findAll() throws SQLException {
        List<ApartmentsEntity> apartments = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    apartments.add((ApartmentsEntity) new Transformer(ApartmentsEntity.class).fromResultSetToEntity(resultSet));
                }
            }
        }
        return apartments;
    }

    @Override
    public ApartmentsEntity findById(Integer id) throws SQLException {
        ApartmentsEntity entity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    entity = (ApartmentsEntity) new Transformer(ApartmentsEntity.class).fromResultSetToEntity(resultSet);
                    break;
                }
            }
        }
        return entity;
    }

    @Override
    public int create(ApartmentsEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getSellerId());
            ps.setInt(2, entity.getRoomsNumber());
            ps.setInt(3, entity.getBedsNumber());
            ps.setInt(4, entity.getHourPrice());
            ps.setString(5, entity.getAddress());
            ps.setInt(6, entity.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(ApartmentsEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setInt(1, entity.getSellerId());
            ps.setInt(2, entity.getRoomsNumber());
            ps.setInt(3, entity.getBedsNumber());
            ps.setInt(4, entity.getHourPrice());
            ps.setString(5, entity.getAddress());
            ps.setInt(6, entity.getId());
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


