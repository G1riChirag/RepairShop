package com.csc396.repairshop.database;

public class RepairWithVehicle {
    private Vehicle vehicle;
    private Repair repair;

    public RepairWithVehicle(Vehicle vehicle, Repair repair) {
        this.vehicle = vehicle;
        this.repair = repair;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Repair getRepair() {
        return repair;
    }
}
