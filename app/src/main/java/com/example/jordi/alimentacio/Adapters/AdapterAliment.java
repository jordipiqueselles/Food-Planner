package com.example.jordi.alimentacio.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.jordi.alimentacio.Dades.Aliment;
import com.example.jordi.alimentacio.Dades.Usuari;
import com.example.jordi.alimentacio.Pantalles.SettingsActivity;
import com.example.jordi.alimentacio.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jordi on 18/12/16.
 */

public class AdapterAliment extends ArrayAdapter<Map.Entry<Aliment, Double>> {

    public AdapterAliment (Context context, List<Map.Entry<Aliment, Double>> data) {
        super(context, R.layout.user_element, data);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View row = inflater.inflate(R.layout.layout_aliment, parent, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SettingsActivity.settingsPref, Context.MODE_PRIVATE);
        int numActius = sharedPreferences.getInt(SettingsActivity.numActius, 1);

        TextView aliment = (TextView) row.findViewById(R.id.aliment);
        TextView quantitat = (TextView) row.findViewById(R.id.quantitat);
        TextView unitats = (TextView) row.findViewById(R.id.unitats);

        aliment.setText(getItem(position).getKey().getNom());
        DecimalFormat df = new DecimalFormat("####0.0");
        quantitat.setText("" + df.format(getItem(position).getValue() * numActius));
        unitats.setText(getItem(position).getKey().getUnitats());
        return row;
    }
}
