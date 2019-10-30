package com.iot.DAO.implementation;

import com.iot.DAO.BuyersDAO;
import com.iot.model.BuyersEntity;
import com.iot.persistant.ConnectionManager;
import com.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyersDaoImpl implements BuyersDAO {
    private static final String FIND_ALL = "SELECT * FROM buyers";
    private static final String DELETE = "DELETE FROM buyers WHERE buyer_id=?";
    private static final String CREATE = "INSERT buyers (buyer_id, email, name, surname, phone_number, birthday) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE buyers email=?, name=?, surname=?, phone_number=?, birthday=? WHERE buyer_id=?";
    private static final String FIND_BY_ID = "SELECT * FROM buyers WHERE buyer_id=?";

    @Override
    public List<BuyersEntity> findAll() throws SQLException {
        List<BuyersEntity> buyers = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    buyers.add((BuyersEntity) new Transformer(BuyersEntity.class).fromResultSetToEntity(resultSet));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return buyers;
    }

    @Override
    public BuyersEntity findById(Integer id) throws SQLException {
        BuyersEntity entity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    entity = (BuyersEntity) new Transformer(BuyersEntity.class).fromResultSetToEntity(resultSet);
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
    public int create(BuyersEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getSurname());
            ps.setString(5, entity.getPhoneNumber());
            ps.setDate(6, (Date) entity.getBirthday());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(BuyersEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getSurname());
            ps.setString(4, entity.getPhoneNumber());
            ps.setDate(5, (Date) entity.getBirthday());
            ps.setInt(6, entity.getId());
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


