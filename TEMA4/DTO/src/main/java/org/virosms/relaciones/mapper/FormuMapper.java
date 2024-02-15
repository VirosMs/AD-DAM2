package org.virosms.relaciones.mapper;

import org.mapstruct.*;
import org.virosms.relaciones.dto.ConstructorDTO;
import org.virosms.relaciones.dto.DriverDTO;
import org.virosms.relaciones.dto.ResultDTO;
import org.virosms.relaciones.dto.Resume;
import org.virosms.relaciones.model.*;


@Mapper(componentModel = "spring")
public interface FormuMapper {

    @Mappings({
            @Mapping(target = "id", source = "driver.driverId"),
            @Mapping(target = "code", source = "driver.code"),
            @Mapping(target = "fullname", expression = "java(createFullName(driver.getForename(), driver.getSurname()))"),
            @Mapping(target = "nationality", source = "driver.nationality"),
            @Mapping(target = "constructor", source = "constructor", qualifiedByName = "mapConstructor")
    })
    DriverDTO driverToDriverDTO(Driver driver, Constructor constructor);


    @Mappings({
            @Mapping(target = "raceName", source = "race.name"),
            @Mapping(target = "circuitName", source = "circuit.name"),
            @Mapping(target = "circuitRef", source = "circuit.circuitRef"),
            @Mapping(target = "date" , source = "race.date"),
            @Mapping(target = "round" , source = "race.round"),
            @Mapping(target = "location" , source = "circuit.location"),
            @Mapping(target = "infoDriver", source = "resultDTO")
    })
    Resume toResume(Race race, Circuit circuit, ResultDTO resultDTO);

    @Mappings({
            @Mapping(target = "id", source = "resultId"),
            @Mapping(target="driverName", expression = "java(createFullName(result.getDriver().getForename(), result.getDriver().getSurname()))"),
            @Mapping(target = "constructor", source = "result.driver.constructor.name"),
            @Mapping(target = "grid", source = "grid"),
            @Mapping(target = "position", source = "position"),
            @Mapping(target = "points", source = "points")
    })
    ResultDTO toResultDTO(Result result);

    @Named("mapConstructor")
    static ConstructorDTO mapConstructor(Constructor constructor) {
        if (constructor == null) {
            return null;
        }

        ConstructorDTO constructorDTO = new ConstructorDTO();
        constructorDTO.setName(constructor.getName());
        // set other fields if needed

        return constructorDTO;
    }

    default String createFullName(String forename, String surname){
        return forename + " " + surname;
    }
}