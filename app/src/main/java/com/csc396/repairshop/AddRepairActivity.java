package com.csc396.repairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.csc396.repairshop.database.DBHelper;
import com.csc396.repairshop.database.Repair;
import com.csc396.repairshop.database.Vehicle;
import com.csc396.repairshop.databinding.ActivityAddRepairBinding;

import java.util.Calendar;
import java.util.List;

public class AddRepairActivity extends AppCompatActivity {
    private ActivityAddRepairBinding binding;


    private View.OnClickListener button_add_repair_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int v_id = binding.spinnerVehicles.getSelectedItemPosition()+1;
            String date = binding.edittextRepairDate.getText().toString();
            double cost = Double.parseDouble(binding.edittextRepairCost.getText().toString());
            String description = binding.edittextRepairDescription.getText().toString();

            Repair newRepair = new Repair(v_id, date, cost, description);

            DBHelper helper = DBHelper.getInstance(AddRepairActivity.this);
            long result = helper.insertRepair(newRepair);
            if(result >= 0){
                Toast.makeText(AddRepairActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    };

    private View.OnClickListener editText_repair_date_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar myCalendar = Calendar.getInstance();
            DatePickerDialog myPicker = new DatePickerDialog(AddRepairActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = (year) + "-" + (month + 1) + "-" + (dayOfMonth);
                    binding.edittextRepairDate.setText(date);
                }
            }, myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            myPicker.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRepairBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = DBHelper.getInstance(this);
        List<Vehicle> vehicles = helper.getAllVehicles();
        ArrayAdapter<Vehicle> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vehicles);
        binding.spinnerVehicles.setAdapter(myAdapter);

        binding.edittextRepairDate.setOnClickListener(editText_repair_date_onClickListener);
        binding.buttonAddRepair.setOnClickListener(button_add_repair_onClickListener);

    }

}