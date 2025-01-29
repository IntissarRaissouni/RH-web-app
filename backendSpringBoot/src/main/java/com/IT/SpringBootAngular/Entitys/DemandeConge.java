package com.IT.SpringBootAngular.Entitys;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import  java.util.Date;
@Document(collection = "conge")
public class DemandeConge {
    @Id
    private String id = new ObjectId().toString();
    private String description;
    private Date startDate;
    private Date endDate;
    private  String state = "No action";
    public DemandeConge() {
    }

    public DemandeConge(String id, String description, Date startDate, Date endDate) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
