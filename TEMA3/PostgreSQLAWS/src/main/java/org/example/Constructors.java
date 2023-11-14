package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Constructors {
    private int constructorid;
    private String constructorref;
    private String name;
    private String nationality;
    private String url;

    public Constructors(String constructorref, String name, String nationality, String url){
        this.constructorref = constructorref;
        this.name = name;
        this.nationality = nationality;
        this.url = url;
    }


}

