package com.iot.model;

import com.iot.model.Annotation.Column;
import com.iot.model.Annotation.PrimaryKey;
import com.iot.model.Annotation.Table;

@Table(name = "sellers")
public class SellersEntity {
    @PrimaryKey
    @Column(name = "seller_id")
    private Integer id;
    @Column(name = "email", length = 45)
    private String email;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "surname", length = 45)
    private String surname;
    @Column(name = "phone_number", length = 45)
    private String phoneNumber;
    @Column(name = "birthday", length = 45)
    private String birthday;

    public SellersEntity() {
    }

    public SellersEntity(Integer id, String email, String name, String surname, String phoneNumber, String birthday) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "SellersEntity{"
                + "id=" + id
                + ", email='" + email + '\''
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", birthday='" + birthday + '\''
                + '}';
    }
}
