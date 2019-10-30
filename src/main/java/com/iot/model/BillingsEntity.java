package com.iot.model;

import com.iot.model.Annotation.Column;
import com.iot.model.Annotation.PrimaryKey;
import com.iot.model.Annotation.Table;

import java.sql.Date;


@Table(name = "billings")
public class BillingsEntity {
    @PrimaryKey
    @Column(name = "billing_d")
    private Integer id;
    @Column(name = "date_payed")
    private Date settlementDate;
    @Column(name = "buyers_id")
    private Integer buyersId;
    @Column(name = "sellers_id")
    private Integer sellersId;
    @Column(name = "price")
    private Integer price;

    public BillingsEntity() {
    }

    public BillingsEntity(Integer id, Date settlementDate, Integer buyersId, Integer sellersId, Integer price) {
        this.id = id;
        this.settlementDate = settlementDate;
        this.buyersId = buyersId;
        this.sellersId = sellersId;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Integer getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(Integer buyersId) {
        this.buyersId = buyersId;
    }

    public Integer getSellersId() {
        return sellersId;
    }

    public void setSellersId(Integer sellersId) {
        this.sellersId = sellersId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BillingsEntity{"
                + "id=" + id
                + ", settlementDate=" + settlementDate
                + ", buyersId=" + buyersId
                + ", sellersId=" + sellersId
                + ", price=" + price
                + '}';
    }
}
