package com.iot.DAO.implementation;

import com.iot.DAO.SellersDAO;
import com.iot.model.SellersEntity;
import com.iot.persistant.ConnectionManager;
import com.iot.transformer.Transformer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellersDaoImpl implements SellersDAO {
    private static final String FIND_ALL = "SELECT * FROM sellers";
    private static final String DELETE = "DELETE FROM sellers WHERE seller_id=?";
    private static final String CREATE = "INSERT sellers (seller_id, email, name, surname, phone_number, birthday) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE sellers SET email=?, name=?, surname=?, phone_number=?, birthday=? WHERE seller_id=?";
    private static final String FIND_BY_ID = "SELECT * FROM sellers WHERE seller_id=?";

    @Override
    public List<SellersEntity> findAll() throws SQLException {
        List<SellersEntity> buyers = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    buyers.add((SellersEntity) new Transformer(SellersEntity.class).fromResultSetToEntity(resultSet));
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
    public SellersEntity findById(Integer id) throws SQLException {
        SellersEntity entity = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    entity = (SellersEntity) new Transformer(SellersEntity.class).fromResultSetToEntity(resultSet);
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
    public int create(SellersEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(CREATE)) {
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getName());
            ps.setString(4, entity.getSurname());
            ps.setString(5, entity.getPhoneNumber());
            ps.setString(6, entity.getBirthday());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(SellersEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getSurname());
            ps.setString(4, entity.getPhoneNumber());
            ps.setString(5, entity.getBirthday());
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


