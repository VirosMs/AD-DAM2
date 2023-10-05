import enums.Modelo;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static enums.Modelo.*;

public class Funko{
    private UUID cod;
    private String nombre;
    private String modelo;

    private double precio;

    private LocalDate fechaLazmiento;

    public Funko(List<String> lista) {
        this.cod = UUID.fromString(lista.get(1));
        if(modelo.equalsIgnoreCase(MARVEL.toString())
                || modelo.equalsIgnoreCase(DISNEY.toString())
                || modelo.equalsIgnoreCase(ANIME.toString())
                || modelo.equalsIgnoreCase(OTROS.toString()))
        {
            this.modelo = modelo;
        }
        this.nombre = nombre;
        //this.precio = Float.parseFloat(precio);
        //this.fechaLazmiento = LocalDate.parse(fechaLazamento);

    }
}
