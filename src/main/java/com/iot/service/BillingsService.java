package com.iot.service;

import com.iot.DAO.implementation.BillingsDaoImpl;
import com.iot.model.BillingsEntity;

import java.sql.SQLException;
import java.util.List;

public class BillingsService {
    public List<BillingsEntity> findAll() throws SQLException {
        return new BillingsDaoImpl().findAll();
    }

    public BillingsEntity  findById(Integer id) throws SQLException {
        return new BillingsDaoImpl().findById(id);
    }

    public int create(BillingsEntity  entity) throws SQLException {
        return new BillingsDaoImpl().create(entity);
    }

    public int update(BillingsEntity  entity) throws SQLException {
        return new BillingsDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new BillingsDaoImpl().delete(id);
    }

}
