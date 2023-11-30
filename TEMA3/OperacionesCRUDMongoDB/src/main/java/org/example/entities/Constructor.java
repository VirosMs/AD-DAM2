package org.example.entities;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Constructor {
    private String constructorref;
    private String name;
    private String nationality;
    private String url;

    public Constructor(String constructorref, String name, String nationality, String url){
        this.constructorref = constructorref;
        this.name = name;
        this.nationality = nationality;
        this.url = url;
    }


}

