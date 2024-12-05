package com.techietech.ps5emulatorprank.Models;

public class UpcomingGameData {
    private String gameImage;
    private String gameName;
    private String gamePlatform;
    private String gameReleaseDate;
    private String gameUrl;

    public UpcomingGameData(String gameImage, String gameName, String gamePlatform, String gameReleaseDate, String gameUrl) {
        this.gameImage = gameImage;
        this.gameName = gameName;
        this.gamePlatform = gamePlatform;
        this.gameReleaseDate = gameReleaseDate;
        this.gameUrl = gameUrl;
    }

    public String getGameImage() {
        return gameImage;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGamePlatform() {
        return gamePlatform;
    }

    public String getGameReleaseDate() {
        return gameReleaseDate;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public UpcomingGameData() {
    }
}
