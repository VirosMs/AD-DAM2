import enums.Modelo;

import java.time.LocalDate;
import java.util.*;

import static enums.Modelo.*;

public class Funko {
    private final UUID cod;
    private final String nombre;
    private String modelo;

    private final double precio;

    private final LocalDate fechaLazmiento;

    public Funko(List<String> lista) {

        this.cod = compCod(lista.get(0));

        this.nombre = lista.get(1);

        this.modelo = compMod(lista.get(2));

        this.precio = convertDuble(lista.get(3));

        this.fechaLazmiento = LocalDate.parse(lista.get(4));

    }


    /**
     * This method is used to transform a String to a float
     *
     * @param pre String
     * @return precio rounded to 2 decimals or 0.0 if pre is not a precio
     */
    private double convertDuble(String pre) {
        double pre1 = 0.0d;
        try {
            pre1 = Double.parseDouble(pre);
        } catch (NumberFormatException e) {
            e.getMessage();
        }

        return Math.round(pre1 * 100.0d) / 100.0d;
    }

    /**
     * @param mod String to compare with Modelo
     * @return Modelo or OTROS if mod is not a Modelo
     */
    private String compMod(String mod) {
        String mod1;
        if (Arrays.stream(Modelo.values()).anyMatch(m -> m.name().equalsIgnoreCase(mod))) {
            mod1 = mod;
        } else {
            mod1 = OTROS.name();
        }
        return mod1;
    }


    /**
     * @param code String to compare with UUID
     * @return UUID or null if code is not a UUID
     */
    private UUID compCod(String code) {
        UUID cod1 = null;
        try {

            cod1 = UUID.fromString(code);


        } catch (IllegalArgumentException e) {
            e.getMessage();
        }


        return cod1;
    }


    @Override
    public String toString() {
        return "Funko{" +
                "cod=" + cod +
                ", nombre='" + nombre + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", fechaLazmiento=" + fechaLazmiento +
                '}';
    }


    public double getPrecio() {
        return precio;
    }
}
