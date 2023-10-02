package org.example;

import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {


        System.out.println("--------------------------------------------------");

        Planar planar = new Planar(Paths.get("\\\\wsl.localhost\\kali-linux\\tmp\\niats"));
        long startTime = System.nanoTime();

        planar.planar();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(duration/1000000);
        System.out.println("--------------------------------------------------");

    }
}