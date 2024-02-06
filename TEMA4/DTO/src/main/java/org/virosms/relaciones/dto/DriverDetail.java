package org.virosms.relaciones.dto;

import org.springframework.beans.factory.annotation.Value;

public interface DriverDetail
{
    Long getDriverId();
    String getCode();
    @Value("#{target.forename + ' ' + target.surname}")
    String getFullname();


    ConstructorDetail getConstructor();
}
