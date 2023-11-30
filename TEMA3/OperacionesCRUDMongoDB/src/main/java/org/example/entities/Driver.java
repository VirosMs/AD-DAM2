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
    private Constructor constructors;
    private Date dob;
    private int driverid;
    private String forename;
    private String surname;
    private String nationality;
    private String url;

    public Driver(String code, Constructor constructors, Date dob, int driverid, String forename,
                  String nationality, String surname  , String url){
        this.code = code;
        this.driverid = driverid;
        this.forename = forename;
        this.surname = surname;
        this.nationality = nationality;
        this.dob = dob;
        this.constructors = constructors;
        this.url = url;
    }
}
