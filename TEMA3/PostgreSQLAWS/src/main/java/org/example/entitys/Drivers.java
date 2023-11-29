package org.example.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entitys.Constructors;

import java.time.LocalDate;

@Data
public class Drivers {
    private int driverId;
    private String code;
    private String forename;
    private String surname;
    private LocalDate dob;
    private String nationality;
    private Constructors constructors;
    private String url;

    public Drivers(String code, String forename, String surname, String dob, String nationality, Constructors constructor, String url){
        this.code = code;
        this.forename = forename;
        this.surname = surname;
        this.nationality = nationality;
        this.dob = LocalDate.parse(dob);
        this.constructors = constructor;
        this.url = url;

    }
}
