package com.IT.SpringBootAngular.Entitys;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

//emission
@Document(collection = "Demande")
@Getter
@Setter
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
}
