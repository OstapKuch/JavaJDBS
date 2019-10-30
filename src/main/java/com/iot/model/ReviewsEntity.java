package com.iot.model;

import com.iot.model.Annotation.Column;
import com.iot.model.Annotation.PrimaryKey;
import com.iot.model.Annotation.Table;

@Table(name = "reviews")
public class ReviewsEntity {
    @PrimaryKey
    @Column(name = "id")
    private Integer id;
    @Column(name = "apartments_id")
    private Integer apartmentsId;
    @Column(name = "buyer_id")
    private Integer buyerId;
    @Column(name = "message", length = 300)
    private String message;
    @Column(name = "average_rating")
    private Float averageRating;
    @Column(name = "date", length = 45)
    private String date;

    public ReviewsEntity() {
    }

    public ReviewsEntity(Integer id, Integer apartmentsId, Integer buyerId, String message, Float averageRating, String date) {
        this.id = id;
        this.apartmentsId = apartmentsId;
        this.buyerId = buyerId;
        this.message = message;
        this.averageRating = averageRating;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApartmentsId() {
        return apartmentsId;
    }

    public void setApartmentsId(Integer apartmentsId) {
        this.apartmentsId = apartmentsId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReviewsEntity{"
                + "id=" + id
                + ", apartmentsId=" + apartmentsId
                + ", buyerId=" + buyerId
                + ", message='" + message + '\''
                + ", averageRating=" + averageRating
                + ", date='" + date + '\''
                + '}';
    }
}
