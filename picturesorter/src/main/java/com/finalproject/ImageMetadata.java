package com.finalproject;

public class ImageMetadata {
    String year;
    String month;
    String day;
    String filePath;

    public ImageMetadata(int year, int month, int day, String filePath){
        this.year = String.valueOf(year);
        this.month = String.valueOf(month);
        this.day = String.valueOf(day);
        this.filePath = filePath;
    }
}
