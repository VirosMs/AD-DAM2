package org.example.atletas;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.util.List;
@Data
@JacksonXmlRootElement(localName = "atletasFemeninas")
public class AtletaFemeninaCollections {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "atletaFemenina")
    private List<AtletaFemenina> atletas;



}
