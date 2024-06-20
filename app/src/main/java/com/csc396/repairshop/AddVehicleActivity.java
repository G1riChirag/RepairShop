package com.csc396.repairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.csc396.repairshop.database.DBHelper;
import com.csc396.repairshop.database.Vehicle;
import com.csc396.repairshop.databinding.ActivityAddVehicleBinding;

public class AddVehicleActivity extends AppCompatActivity {
    private ActivityAddVehicleBinding binding;

    private View.OnClickListener button_add_vehicle_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int year = Integer.valueOf(binding.edittextYear.getText().toString());
            String makeModel = binding.edittextMakeModel.getText().toString();
            double price = Double.parseDouble(binding.edittextPrice.getText().toString());
            int isNew = 0;
            if(binding.checkboxIsNew.isChecked()) {
                isNew = 1;
            }

            Vehicle newVehicle = new Vehicle(year, makeModel, price, isNew);

            DBHelper helper = DBHelper.getInstance(AddVehicleActivity.this);
            long result = helper.insertVehicle(newVehicle);
            if(result >= 0){
                Toast.makeText(AddVehicleActivity.this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddVehicleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonAddVehicle.setOnClickListener(button_add_vehicle_onClickListener);
    }
}