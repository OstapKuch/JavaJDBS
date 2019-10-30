package com.iot.model;

import com.iot.model.Annotation.Column;
import com.iot.model.Annotation.PrimaryKey;
import com.iot.model.Annotation.Table;

@Table(name = "appartments_reservations")
public class ReservationsEntity {
    @PrimaryKey
    @Column(name = "reservation_id")
    private Integer id;
    @Column(name = "settlement_date", length = 45)
    private String settlementDate;
    @Column(name = "leave_date", length = 45)
    private String leaveDate;
    @Column(name = "paid")
    private Integer paid;

    public ReservationsEntity() {
    }

    public ReservationsEntity(Integer id, String settlementDate, String leaveDate, Integer paid) {
        this.id = id;
        this.settlementDate = settlementDate;
        this.leaveDate = leaveDate;
        this.paid = paid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "ReservationsEntity{"
                + "id=" + id
                + ", settlementDate='" + settlementDate + '\''
                + ", leaveDate=" + leaveDate
                + ", paid=" + paid
                + '}';
    }
}
