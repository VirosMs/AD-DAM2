package org.example.pilotos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Piloto {
    private int driverId;
    private String code;
    private String forename;
    private String surname;
    private LocalDate dob;
    private String nationality;
    private String url;

    public Piloto(String code, String forename, String surname, String dob, String nationality, String url){
        this.code = code;
        this.forename = forename;
        this.surname = surname;
        this.dob = LocalDate.parse(dob);
        this.nationality = nationality;
        this.url = url;
    }

    public Piloto(int driverId, String code, String forename, String surname, String dob, String nationality, String url){
        this.driverId = driverId;
        this.code = code;
        this.forename = forename;
        this.surname = surname;
        this.dob = LocalDate.parse(dob);
        this.nationality = nationality;
        this.url = url;
    }


}
