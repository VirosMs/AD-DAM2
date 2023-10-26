package org.example;


/**
 * This class is used to create a new object of type Levels.
 *
 * @param uuid
 * @param charmLevel
 * @param hideIfNotOwned
 * @param displayName
 * @param displayIcon
 * @param assetPath
 */
public record Levels (String uuid, int charmLevel, boolean hideIfNotOwned,
                        String displayName, String displayIcon, String assetPath){}
