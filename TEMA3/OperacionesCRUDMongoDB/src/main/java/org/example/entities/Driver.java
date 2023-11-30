package org.example.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@NoArgsConstructor
public class Driver {
    ObjectId id;
    private String code;
    private Constructor constructor;
    private Date dob;
    private int driverId;
    private String forename;
    private String surname;
    private String nationality;
    private String url;

    public Driver(String code, Constructor constructor, Date dob, int driverId, String forename,
                  String nationality, String surname  , String url){
        this.code = code;
        this.driverId = driverId;
        this.forename = forename;
        this.surname = surname;
        this.nationality = nationality;
        this.dob = dob;
        this.constructor = constructor;
        this.url = url;
    }
}
