package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.example.entities.Constructors;

import java.sql.Date;

@Data
public class Drivers {
    ObjectId id;
    private String code;
    private Constructors constructors;
    private Date dob;
    private int driverId;
    private String forename;
    private String surname;
    private String nationality;
    private String url;

    public Drivers(String code, Constructors constructor, Date dob, int driverId, String forename,
                   String nationality, String surname  , String url){
        this.code = code;
        this.driverId = driverId;
        this.forename = forename;
        this.surname = surname;
        this.nationality = nationality;
        this.dob = dob;
        this.constructors = constructor;
        this.url = url;

    }
}
