package org.virosms.relaciones.mapper;

import org.mapstruct.*;
import org.virosms.relaciones.dto.DriverDTO;
import org.virosms.relaciones.model.Driver;



@Mapper(componentModel = "spring")
public interface FormuMapper {

    @Mappings({
            @Mapping(target = "id", source = "driverId"),
            @Mapping(target = "code", source = "code"),
            @Mapping(target = "fullname", expression = "java(createFullName(driver.getForename(), driver.getSurname()))"),
            @Mapping(target = "nationality", source = "nationality"),
            @Mapping(target = "constructor", source = "constructor.name")
    })
    DriverDTO driverToDriverDTO(Driver driver);




    default String createFullName(String forename, String surname){
        return forename + " " + surname;
    }
}
