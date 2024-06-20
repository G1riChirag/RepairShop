package com.csc396.repairshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csc396.repairshop.database.DBHelper;
import com.csc396.repairshop.database.Repair;
import com.csc396.repairshop.database.RepairWithVehicle;
import com.csc396.repairshop.database.Vehicle;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<RepairWithVehicle> repairsWithVehicles;

    public CustomAdapter(Context context, List<RepairWithVehicle> repairsWithVehicles) {
        this.context = context;
        this.repairsWithVehicles = repairsWithVehicles;
    }

    @Override
    public int getCount() {
        return repairsWithVehicles.size();
    }


    @Override
    public Object getItem(int position) {
        return repairsWithVehicles.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_results_row, parent, false);
        }
        RepairWithVehicle thisRepair = repairsWithVehicles.get(position);

        TextView repair_year_make_model = convertView.findViewById(R.id.text_year_make_model);
        TextView repair_date = convertView.findViewById(R.id.text_repair_date);
        TextView repair_cost = convertView.findViewById(R.id.text_repair_cost);
        TextView repair_description = convertView.findViewById(R.id.text_repair_description);

        String year_make_model = thisRepair.getVehicle().getYear() + " " + thisRepair.getVehicle().getMakeModel();
        repair_year_make_model.setText(year_make_model);
        repair_date.setText(thisRepair.getRepair().getDate());
        repair_cost.setText(Double.toString(thisRepair.getRepair().getCost()));
        repair_description.setText(thisRepair.getRepair().getDescription());

        return convertView;
    }
}
