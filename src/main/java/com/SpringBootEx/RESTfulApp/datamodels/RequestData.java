package com.SpringBootEx.RESTfulApp.datamodels;

public class RequestData {
    private String response;
    private int favoriteNumber;

    public RequestData(String response, int favoriteNumber) {
        this.response = response;
        this.favoriteNumber = favoriteNumber;
    }

    public String getResponse() {
        return response;
    }

    public int getFavoriteNumber() {
        return favoriteNumber;
    }
}
