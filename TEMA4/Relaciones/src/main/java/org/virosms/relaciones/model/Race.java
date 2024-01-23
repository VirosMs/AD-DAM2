package org.virosms.relaciones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "races")
@NoArgsConstructor
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raceid")
    private Long raceId;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int round;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    private String url;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "circuitid")
    @JsonIgnoreProperties("race")

    private Circuit circuit;
}
