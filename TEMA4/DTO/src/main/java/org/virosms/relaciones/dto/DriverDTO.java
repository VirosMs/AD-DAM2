package org.virosms.relaciones.dto;

public record DriverDTO(
        Long id,
        String code,
        String fullname,
        String nationality,
        String constructor
) {}
