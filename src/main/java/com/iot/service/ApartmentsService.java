package com.iot.service;

import com.iot.DAO.implementation.ApartmentsDaoImpl;
import com.iot.model.ApartmentsEntity;

import java.sql.SQLException;
import java.util.List;

public class ApartmentsService {
    public List<ApartmentsEntity> findAll() throws SQLException {
        return new ApartmentsDaoImpl().findAll();
    }

    public ApartmentsEntity  findById(Integer id) throws SQLException {
        return new ApartmentsDaoImpl().findById(id);
    }

    public int create(ApartmentsEntity  entity) throws SQLException {
        return new ApartmentsDaoImpl().create(entity);
    }

    public int update(ApartmentsEntity  entity) throws SQLException {
        return new ApartmentsDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new ApartmentsDaoImpl().delete(id);
    }

}
