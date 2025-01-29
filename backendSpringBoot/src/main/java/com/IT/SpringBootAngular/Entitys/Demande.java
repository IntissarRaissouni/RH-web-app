package com.IT.SpringBootAngular.Entitys;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

//emission
@Document(collection = "Demande")
public class Demande {
    @Id
    private String id = new ObjectId().toString();
    private String reason;
    private String state = "No action";
    private Date request_date;
    private Date resign_date;
    public Demande() {
    }

    public Demande(String id, String reason, String state, Date request_date, Date resign_date) {
        this.id = id;
        this.reason = reason;
        this.state = state;
        this.request_date = request_date;
        this.resign_date = resign_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getRequest_date() {
        return request_date;
    }

    public void setRequest_date(Date request_date) {
        this.request_date = request_date;
    }

    public Date getResign_date() {
        return resign_date;
    }

    public void setResign_date(Date resign_date) {
        this.resign_date = resign_date;
    }
}
