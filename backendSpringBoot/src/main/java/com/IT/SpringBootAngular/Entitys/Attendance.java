package com.IT.SpringBootAngular.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "attendances")
public class Attendance {

    @Id
    private String id = new ObjectId().toString();

    private LocalDateTime time;




    public Attendance(){

    }
    public Attendance(String id, LocalDateTime time) {

        this.id = id;
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
