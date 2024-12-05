package com.techietech.ps5emulatorprank.Models;

public class GameDataClass {
    private String gameName;
    private String gameUrl;
    private String gameImage;
    private String gamePlatform;
    private String gameReleaseDate;

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

    public String getGameReleaseDate() {
        return gameReleaseDate;
    }


    public GameDataClass(String gameName, String gameUrl, String gameImage, String gameReleaseDate, String gamePlatform) {
        this.gameName = gameName;
        this.gameUrl = gameUrl;
        this.gameImage = gameImage;
        this.gamePlatform = gamePlatform;
        this.gameReleaseDate = gameReleaseDate;
    }

    public GameDataClass() {

    }

}
