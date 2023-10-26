package org.example;




public record Levels (String uuid, int charmLevel, boolean hideIfNotOwned,
                        String displayName, String displayIcon, String assetPath){}
