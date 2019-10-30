package com.iot.service;

import com.iot.DAO.implementation.SellersDaoImpl;
import com.iot.model.SellersEntity;

import java.sql.SQLException;
import java.util.List;

public class SellersService {
    public List<SellersEntity> findAll() throws SQLException {
        return new SellersDaoImpl().findAll();
    }

    public SellersEntity  findById(Integer id) throws SQLException {
        return new SellersDaoImpl().findById(id);
    }

    public int create(SellersEntity  entity) throws SQLException {
        return new SellersDaoImpl().create(entity);
    }

    public int update(SellersEntity  entity) throws SQLException {
        return new SellersDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new SellersDaoImpl().delete(id);
    }

}
