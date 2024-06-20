package com.csc396.repairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.csc396.repairshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;

    private View.OnClickListener button_add_vehicle_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent theIntent = new Intent(MainActivity.this, AddVehicleActivity.class);
            startActivity(theIntent);
        }
    };

    private View.OnClickListener button_add_repair_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent theIntent = new Intent(MainActivity.this, AddRepairActivity.class);
            startActivity(theIntent);
        }
    };

    private View.OnClickListener button_search_repairs_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent theIntent = new Intent(MainActivity.this, SearchRepairsActivity.class);
            startActivity(theIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonAddVehicle.setOnClickListener(button_add_vehicle_onClickListener);
        binding.buttonAddRepair.setOnClickListener(button_add_repair_onClickListener);
        binding.buttonSearchRepairs.setOnClickListener(button_search_repairs_onClickListener);
    }

}