package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Levels {
    private String uuid;
    private int charmLevel;
    private boolean hideIfNotOwned;
    private String displayName;
    private String displayIcon;
    private String assetPath;
}
