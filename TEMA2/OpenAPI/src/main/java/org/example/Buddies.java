package org.example;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Buddies {
    private String uuid;
    private String displayName;
    private boolean isHiddenIfNotOwned;
    private String themeUuid;
    private String displayIcon;
    private String assetPath;
    private List<Levels> levels;
}
