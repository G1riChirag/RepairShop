package com.csc396.repairshop.database;

public class Repair {
    private int rid;
    private int v_id;
    private String date;
    private double cost;
    private String description;

    public Repair(int rid, int v_id, String date, double cost, String description) {
        this.rid = rid;
        this.v_id = v_id;
        this.date = date;
        this.cost = cost;
        this.description = description;
    }

    public Repair(int v_id, String date, double cost, String description) {
        this.v_id = v_id;
        this.date = date;
        this.cost = cost;
        this.description = description;
    }

    public int getRid() {
        return rid;
    }

    public int getV_id() {
        return v_id;
    }

    public String getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}
