package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
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


}
