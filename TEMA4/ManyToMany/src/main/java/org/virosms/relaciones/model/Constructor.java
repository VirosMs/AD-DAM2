package org.virosms.relaciones.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "constructors")
@NoArgsConstructor
public class Constructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constructorid")
    private long constructorId;

    @Column(name = "constructorref", unique = true, nullable = false)
    private String constructorRef;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String nationality;

    private String url;

    @OneToMany(mappedBy = "constructor",
            cascade = CascadeType.ALL)
    private List<Driver> driverList;
}
