package com.iot.service;

import com.iot.DAO.implementation.ApartmentsHasReservationsDaoImpl;
import com.iot.model.ApartmentsHasReservationsEntity;

import java.sql.SQLException;
import java.util.List;

public class ApartmentsHasReservationsService {
    public List<ApartmentsHasReservationsEntity> findAll() throws SQLException {
        return new ApartmentsHasReservationsDaoImpl().findAll();
    }

    public ApartmentsHasReservationsEntity  findById(Integer id) throws SQLException {
        return new ApartmentsHasReservationsDaoImpl().findById(id);
    }

    public int create(ApartmentsHasReservationsEntity  entity) throws SQLException {
        return new ApartmentsHasReservationsDaoImpl().create(entity);
    }

    public int update(ApartmentsHasReservationsEntity  entity) throws SQLException {
        return new ApartmentsHasReservationsDaoImpl().update(entity);
    }

    public int delete(Integer id) throws SQLException {
        return new ApartmentsHasReservationsDaoImpl().delete(id);
    }

}
