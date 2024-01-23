package org.virosms.relaciones.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "circuits")
public class Circuit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "circuitid")
    private Long circuitId;

    @Column(name = "circuitref", unique = true, nullable = false)
    private String circuitRef;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String country;

    private double lat;

    private double lng;

    private int alt;

    private String url;

    @OneToOne(mappedBy = "circuit", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Race race;
}
