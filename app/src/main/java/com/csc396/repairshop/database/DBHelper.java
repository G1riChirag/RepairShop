package com.csc396.repairshop.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // Single object reference
    private static DBHelper myInstance;

    private static final String DB_NAME = "vehicle.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_VEHICLE = "vehicle";
    public static final String COL_VEHICLE_ID = "vid";
    public static final String COL_VEHICLE_YEAR = "year";
    public static final String COL_VEHICLE_MAKE_MODEL = "makeModel";
    public static final String COL_VEHICLE_PURCHASE_PRICE = "purchasePrice";
    public static final String COL_VEHICLE_IS_NEW = "isNew";

    public static final String TABLE_REPAIR = "repair";
    public static final String COL_REPAIR_ID = "rid";
    public static final String COL_REPAIR_VEHICLE_ID = "v_id";
    public static final String COL_REPAIR_DATE = "date";
    public static final String COL_REPAIR_COST = "cost";
    public static final String COL_REPAIR_DESCRIPTION = "description";


    private DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_VEHICLE + " (" +
                COL_VEHICLE_ID + " INTEGER," +
                COL_VEHICLE_YEAR + " INTEGER NOT NULL," +
                COL_VEHICLE_MAKE_MODEL + " TEXT NOT NULL," +
                COL_VEHICLE_PURCHASE_PRICE + " REAL NOT NULL," +
                COL_VEHICLE_IS_NEW + " INTEGER NOT NULL," +
                "PRIMARY KEY(" + COL_VEHICLE_ID + " AUTOINCREMENT)" +
                ")";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE " + TABLE_REPAIR +" (" +
                COL_REPAIR_ID + " INTEGER," +
                COL_REPAIR_VEHICLE_ID + " INTEGER NOT NULL," +
                COL_REPAIR_DATE + " TEXT NOT NULL," +
                COL_REPAIR_COST + " REAL NOT NULL," +
                COL_REPAIR_DESCRIPTION + " TEXT NOT NULL," +
                "PRIMARY KEY(" + COL_REPAIR_ID + " AUTOINCREMENT)" +
                ")";

        db.execSQL(sql2);
    }

    public static DBHelper getInstance(Context context){
        if(myInstance == null){
            myInstance = new DBHelper(context);
        }
        return myInstance;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertVehicle(Vehicle v){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_VEHICLE_YEAR, v.getYear());
        cv.put(COL_VEHICLE_MAKE_MODEL, v.getMakeModel());
        cv.put(COL_VEHICLE_PURCHASE_PRICE, v.getPrice());
        cv.put(COL_VEHICLE_IS_NEW, v.getIsNew());

        long result = db.insert(TABLE_VEHICLE, null, cv);
        db.close();

        return result;
    }

    public long insertRepair(Repair r){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_REPAIR_VEHICLE_ID, r.getV_id());
        cv.put(COL_REPAIR_DATE, r.getDate());
        cv.put(COL_REPAIR_COST, r.getCost());
        cv.put(COL_REPAIR_DESCRIPTION, r.getDescription());

        long result = db.insert(TABLE_REPAIR, null, cv);
        db.close();

        return result;
    };

    public List<Vehicle> getAllVehicles(){
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_VEHICLE;
        Cursor cursor = db.rawQuery(sql, null);

        int idx_vid = cursor.getColumnIndex(COL_VEHICLE_ID);
        int idx_year = cursor.getColumnIndex(COL_VEHICLE_YEAR);
        int idx_makeModel = cursor.getColumnIndex(COL_VEHICLE_MAKE_MODEL);
        int idx_purchasePrice = cursor.getColumnIndex(COL_VEHICLE_PURCHASE_PRICE);
        int idx_isNew = cursor.getColumnIndex(COL_VEHICLE_IS_NEW);

        List<Vehicle> vehicles = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                int vid = cursor.getInt(idx_vid);
                int year = cursor.getInt(idx_year);
                String makeModel = cursor.getString(idx_makeModel);
                double purchasePrice = cursor.getDouble(idx_purchasePrice);
                int isNew = cursor.getInt(idx_isNew);

                Vehicle v = new Vehicle(vid, year, makeModel, purchasePrice, isNew);
                vehicles.add(v);

            }while(cursor.moveToNext());
        }
        db.close();

        return vehicles;
    }

    public List<Repair> getRepair(String searchPhrase){
        SQLiteDatabase db = getReadableDatabase();

        String sql = String.format("SELECT * FROM %s WHERE %s LIKE '%%%s%%'",
                TABLE_REPAIR,
                COL_REPAIR_DESCRIPTION,
                searchPhrase);

        if(searchPhrase == ""){
            sql = String.format("SELECT * FROM %s", TABLE_REPAIR);
        }

        Cursor cursor = db.rawQuery(sql, null);

        int idx_rid = cursor.getColumnIndex(COL_REPAIR_ID);
        int idx_v_id = cursor.getColumnIndex(COL_REPAIR_VEHICLE_ID);
        int idx_date = cursor.getColumnIndex(COL_REPAIR_DATE);
        int idx_cost = cursor.getColumnIndex(COL_REPAIR_COST);
        int idx_description = cursor.getColumnIndex(COL_REPAIR_DESCRIPTION);

        List<Repair> repairs = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                int rid = cursor.getInt(idx_rid);
                int v_id = cursor.getInt(idx_v_id);
                String date = cursor.getString(idx_date);
                Double cost = cursor.getDouble(idx_cost);
                String description = cursor.getString(idx_description);

                Repair r = new Repair(rid, v_id, date, cost, description);
                repairs.add(r);

            }while(cursor.moveToNext());
        }
        db.close();

        return repairs;
    }

    public List<RepairWithVehicle> getRepairsWithVehicle(String searchPhrase){
        SQLiteDatabase db = getReadableDatabase();

        String sql = String.format("SELECT * FROM %s INNER JOIN %s ON %s = %s WHERE %s LIKE '%%%s%%'",
                TABLE_VEHICLE,
                TABLE_REPAIR,
                COL_VEHICLE_ID,
                COL_REPAIR_VEHICLE_ID,
                COL_REPAIR_DESCRIPTION,
                searchPhrase);
        Cursor cursor = db.rawQuery(sql, null);

        int idx_vid = cursor.getColumnIndex(COL_VEHICLE_ID);
        int idx_year = cursor.getColumnIndex(COL_VEHICLE_YEAR);
        int idx_makeModel = cursor.getColumnIndex(COL_VEHICLE_MAKE_MODEL);
        int idx_purchasePrice = cursor.getColumnIndex(COL_VEHICLE_PURCHASE_PRICE);
        int idx_isNew = cursor.getColumnIndex(COL_VEHICLE_IS_NEW);

        int idx_rid = cursor.getColumnIndex(COL_REPAIR_ID);
        int idx_v_id = cursor.getColumnIndex(COL_REPAIR_VEHICLE_ID);
        int idx_date = cursor.getColumnIndex(COL_REPAIR_DATE);
        int idx_cost = cursor.getColumnIndex(COL_REPAIR_COST);
        int idx_description = cursor.getColumnIndex(COL_REPAIR_DESCRIPTION);

        List<RepairWithVehicle> repairWithVehicles = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                int vid = cursor.getInt(idx_vid);
                int year = cursor.getInt(idx_year);
                String makeModel = cursor.getString(idx_makeModel);
                double purchasePrice = cursor.getDouble(idx_purchasePrice);
                int isNew = cursor.getInt(idx_isNew);

                int rid = cursor.getInt(idx_rid);
                int v_id = cursor.getInt(idx_v_id);
                String date = cursor.getString(idx_date);
                Double cost = cursor.getDouble(idx_cost);
                String description = cursor.getString(idx_description);

                Vehicle v = new Vehicle(vid, year, makeModel, purchasePrice, isNew);
                Repair r = new Repair(rid, v_id, date, cost, description);
                RepairWithVehicle rWithV = new RepairWithVehicle(v, r);
                repairWithVehicles.add(rWithV);

            }while(cursor.moveToNext());
        }
        db.close();

        return repairWithVehicles;
    }

    public int deleteRepair(int position){
        SQLiteDatabase db = getWritableDatabase();
        String where = String.format("%s LIKE '%s'", COL_REPAIR_ID, position);
        int num_rows = db.delete(TABLE_REPAIR, where, null);

        db.close();
        return num_rows;
    }


}
