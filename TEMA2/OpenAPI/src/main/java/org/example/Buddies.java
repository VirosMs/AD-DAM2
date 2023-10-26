package org.example;

import lombok.*;

import java.util.List;

/**
 * This class is used to create a new object of type Buddies.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Buddies {
    private String uuid;
    private String displayName;
    private boolean isHiddenIfNotOwned;
    private String themeUuid;
    private String displayIcon;
    private String assetPath;
    private List<Levels> levels;



    @Override
    public String toString() {
        return "Buddies{" +
                "uuid='" + uuid + '\'' +
                ", displayName='" + displayName + '\'' +
                ", isHiddenIfNotOwned=" + isHiddenIfNotOwned +
                ", themeUuid='" + themeUuid + '\'' +
                ", displayIcon='" + displayIcon + '\'' +
                ", assetPath='" + assetPath + '\'' +
                ", levels=" + levels +
                '}';
    }

    /**
     * This method is used to get the value of isHiddenIfNotOwned.
     * The method parse the String to Boolean.
     */
    public void setIsHiddenIfNotOwned(String isHiddenIfNotOwned) {
        this.isHiddenIfNotOwned = Boolean.parseBoolean(isHiddenIfNotOwned);
    }

}
