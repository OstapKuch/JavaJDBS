package com.iot.model;

import com.iot.model.Annotation.Column;
import com.iot.model.Annotation.PrimaryKey;
import com.iot.model.Annotation.Table;

@Table(name = "airbnb_wallet")
public class AirbnbWalletEntity {
    @PrimaryKey
    @Column(name = "wallet_id")
    private Integer id;
    @Column(name = "money")
    private Integer money;

    public AirbnbWalletEntity() {
    }

    public AirbnbWalletEntity(Integer id, Integer money) {
        this.id = id;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }


    @Override
    public String toString() {
        return "airbnb_wallet "
                + "id=" + id
                + ", money=" + money;
    }
}
