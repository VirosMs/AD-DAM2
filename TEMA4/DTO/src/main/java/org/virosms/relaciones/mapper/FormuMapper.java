package org.virosms.relaciones.mapper;

import org.mapstruct.*;
import org.virosms.relaciones.dto.ConstructorDTO;
import org.virosms.relaciones.dto.DriverDTO;
import org.virosms.relaciones.model.Constructor;
import org.virosms.relaciones.model.Driver;



@Mapper(componentModel = "spring")
public interface FormuMapper {

    @Mappings({
            @Mapping(target = "id", source = "dri.driverId"),
            @Mapping(target = "code", source = "dri.code"),
            @Mapping(target = "fullname", expression = "java(createFullName(dri.getForename(), dri.getSurname()))"),
            @Mapping(target = "nationality", source = "dri.nationality"),
            @Mapping(target = "constructor", source = "cons", qualifiedByName = "constructorToConstructorDTO")
    })
    DriverDTO fromDriverAndConstructorToDriverDTO(Driver dri, Constructor cons);

    @Named("constructorToConstructorDTO")
    static ConstructorDTO constructorToConstructorDTO(Constructor constructor){
        return new ConstructorDTO(
                constructor.getConstructorId(),
                constructor.getConstructorRef(),
                constructor.getName()
        );
    }


    default String createFullName(String forename, String surname){
        return forename + " " + surname;
    }
}
