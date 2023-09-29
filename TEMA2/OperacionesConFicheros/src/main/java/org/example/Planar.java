package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;


public class Planar {

    private final Path dir;

    public Planar(Path dir){
        this.dir = dir;
    }

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
            System.out.println("Error al listar el directorio");
        }
    }

}


