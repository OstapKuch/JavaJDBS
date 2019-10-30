package com.iot.service;

import com.iot.DAO.implementation.AirbnbWalletDaoImpl;
import com.iot.model.AirbnbWalletEntity;

import java.sql.SQLException;
import java.util.List;

public class AirbnbWalletService {
    public List<AirbnbWalletEntity> findAll() throws SQLException {
        return new AirbnbWalletDaoImpl().findAll();
    }

    public AirbnbWalletEntity  findById(Integer id) throws SQLException {
        return new AirbnbWalletDaoImpl().findById(id);
    }

    public int create(AirbnbWalletEntity  entity) throws SQLException {
        return new AirbnbWalletDaoImpl().create(entity);
    }

    public int update(AirbnbWalletEntity  entity) throws SQLException {
        return new AirbnbWalletDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new AirbnbWalletDaoImpl().delete(id);
    }

}
