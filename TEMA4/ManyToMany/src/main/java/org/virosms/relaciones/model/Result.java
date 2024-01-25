package org.virosms.relaciones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "results")
@NoArgsConstructor
public class Result {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resultid")
    private Long resultId;

    @ManyToOne
    @JoinColumn(name = "raceid")
    @JsonIgnoreProperties("resultList")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "driverid")
    @JsonIgnoreProperties("resultList")
    private Driver driver;

    @Column(nullable = false)
    private int grid;


    private Integer position;

    @Column(nullable = false)
    private int points;
}
