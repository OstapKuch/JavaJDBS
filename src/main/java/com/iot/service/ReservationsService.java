package com.iot.service;

import com.iot.DAO.implementation.ReservationsDaoImpl;
import com.iot.model.ReservationsEntity;

import java.sql.SQLException;
import java.util.List;

public class ReservationsService {
    public List<ReservationsEntity> findAll() throws SQLException {
        return new ReservationsDaoImpl().findAll();
    }

    public ReservationsEntity  findById(Integer id) throws SQLException {
        return new ReservationsDaoImpl().findById(id);
    }

    public int create(ReservationsEntity  entity) throws SQLException {
        return new ReservationsDaoImpl().create(entity);
    }

    public int update(ReservationsEntity  entity) throws SQLException {
        return new ReservationsDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new ReservationsDaoImpl().delete(id);
    }

}
