package com.csc396.repairshop.database;

public class Vehicle {

    private int vid;
    private int year;
    private String makeModel;
    private double price;
    private int isNew;

    public Vehicle(int vid, int year, String makeModel, double price, int isNew) {
        this.vid = vid;
        this.year = year;
        this.makeModel = makeModel;
        this.price = price;
        this.isNew = isNew;
    }

    public Vehicle(int year, String makeModel, double price, int isNew) {
        this.year = year;
        this.makeModel = makeModel;
        this.price = price;
        this.isNew = isNew;
    }

    public int getVid() {
        return vid;
    }

    public int getYear() {
        return year;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public double getPrice() {
        return price;
    }

    public int getIsNew() {
        return isNew;
    }

    @Override
    public String toString() {
        return year + " " + makeModel ;
    }
}
