package com.csc396.repairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.csc396.repairshop.database.DBHelper;
import com.csc396.repairshop.database.Repair;
import com.csc396.repairshop.database.RepairWithVehicle;
import com.csc396.repairshop.database.Vehicle;
import com.csc396.repairshop.databinding.ActivitySearchRepairsBinding;

import java.util.List;

public class SearchRepairsActivity extends AppCompatActivity {
    private ActivitySearchRepairsBinding binding;

    private View.OnClickListener button_find_repairs_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String search_phrase = binding.edittextSearchPhrase.getText().toString();
            DBHelper helper = DBHelper.getInstance(SearchRepairsActivity.this);
            List<RepairWithVehicle> repairs = helper.getRepairsWithVehicle(search_phrase);
            CustomAdapter myAdapter = new CustomAdapter(SearchRepairsActivity.this, repairs);
            binding.listviewResults.setAdapter(myAdapter);
        }
    };

    private AdapterView.OnItemLongClickListener listview_onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            RepairWithVehicle repair_with_vehicle = (RepairWithVehicle) parent.getItemAtPosition(position);
            DBHelper helper = DBHelper.getInstance(SearchRepairsActivity.this);
            int  numRowsAffected = helper.deleteRepair(repair_with_vehicle.getRepair().getRid());
            binding.listviewResults.invalidateViews();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchRepairsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonFindRepairs.setOnClickListener(button_find_repairs_onClickListener);
        binding.listviewResults.setOnItemLongClickListener(listview_onItemLongClickListener);
    }

}