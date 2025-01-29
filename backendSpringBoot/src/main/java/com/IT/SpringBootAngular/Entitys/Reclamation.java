package com.IT.SpringBootAngular.Entitys;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Reclamation")
public class Reclamation {
    @Id
    private String id = new ObjectId().toString();
    private String reclamation;

    public Reclamation() {
    }

    public Reclamation(String reclamation) {
        this.reclamation = reclamation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReclamation() {
        return reclamation;
    }

    public void setReclamation(String reclamation) {
        this.reclamation = reclamation;
    }




}
