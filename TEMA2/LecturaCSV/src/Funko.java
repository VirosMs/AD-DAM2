import enums.Modelo;

import java.util.Date;
import java.util.UUID;

import static enums.Modelo.*;

public class Funko{
    private UUID cod;
    private String nombre;
    private String modelo;



    private Date fechaLazmiento;

    public Funko(String cod, String modelo, String nombre, String precio, String fechaLazamento) {
        this.cod = UUID.fromString(cod);
        if(modelo.equalsIgnoreCase(MARVEL.toString())
                || modelo.equalsIgnoreCase(DISNEY.toString())
                || modelo.equalsIgnoreCase(ANIME.toString())
                || modelo.equalsIgnoreCase(OTROS.toString()))
        {
            this.modelo = modelo;
        }


    }
}
