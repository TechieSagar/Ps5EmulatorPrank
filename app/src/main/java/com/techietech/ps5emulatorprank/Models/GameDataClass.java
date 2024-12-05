package com.techietech.ps5emulatorprank;

public class GameDataClass {
    private String gameName;
    private String gameUrl;
    private String gameImage;
    private String gamePlatform;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;

    public String getGameName() {
        return gameName;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public String getGameImage() {
        return gameImage;
    }

    public String getGamePlatform() {
        return gamePlatform;
    }

    public GameDataClass(String gameName, String gameUrl, String gameImage, String gamePlatform) {
        this.gameName = gameName;
        this.gameUrl = gameUrl;
        this.gameImage = gameImage;
        this.gamePlatform = gamePlatform;
    }

    public GameDataClass() {

    }

}
