package com.iot.service;

import com.iot.DAO.implementation.BuyersDaoImpl;
import com.iot.model.BuyersEntity;

import java.sql.SQLException;
import java.util.List;

public class BuyersService {
    public List<BuyersEntity> findAll() throws SQLException {
        return new BuyersDaoImpl().findAll();
    }

    public BuyersEntity  findById(Integer id) throws SQLException {
        return new BuyersDaoImpl().findById(id);
    }

    public int create(BuyersEntity  entity) throws SQLException {
        return new BuyersDaoImpl().create(entity);
    }

    public int update(BuyersEntity  entity) throws SQLException {
        return new BuyersDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new BuyersDaoImpl().delete(id);
    }

}
