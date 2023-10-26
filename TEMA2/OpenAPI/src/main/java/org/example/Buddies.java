package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import java.util.List;

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

    public void setIsHiddenIfNotOwned(String isHiddenIfNotOwned) {
        this.isHiddenIfNotOwned = Boolean.parseBoolean(isHiddenIfNotOwned);
    }

}
