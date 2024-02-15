package org.virosms.relaciones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;

public record Resume(
        String raceName,
        String circuitName,
        String circuitRef,
        LocalDate date,
        int round,
        String location,

        @JsonProperty("Driver Info")
        ResultDTO infoDriver

        
) {
}
