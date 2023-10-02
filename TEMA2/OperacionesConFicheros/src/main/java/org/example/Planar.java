package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Clase que implementa el codigo Planar
 *
 * @version 1.0
 * @author Charles
 * @since 2023-10-02
 */
public class Planar {

    private final Path dir;

    public Planar(Path dir){
        this.dir = dir;
    }

    /**
     * Mueve todos los archivos de un directorio a su directorio padre y elimina el directorio vacio
     *
     * @throws RuntimeException si no se puede mover el archivo
     * @throws RuntimeException si no se puede eliminar el directorio
     *
     *
     * @see Files#isSameFile(Path, Path)
     * @see Files#delete(Path)
     *
     *
     *
     */
    public void planar(){
        try (Stream<Path> files = Files.walk(this.dir).sorted(Comparator.reverseOrder())) {
            files.forEach(file -> {
                try {
                    if (!Files.isSameFile(file, this.dir))
                        if (Files.isDirectory(file))
                            Files.delete(file);
                        else
                            Files.move(file.toAbsolutePath(), this.dir.resolve(file.getFileName()));
                } catch (IOException e) {
                    System.out.println("Error al mover el archivo");
                }
            });
        }catch (IOException e) {
            throw new RuntimeException("Error al leer el directorio");
        }
    }


}


