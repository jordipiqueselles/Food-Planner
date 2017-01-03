package com.example.jordi.alimentacio.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.jordi.alimentacio.Dades.Usuari;
import com.example.jordi.alimentacio.Pantalles.SettingsActivity;
import com.example.jordi.alimentacio.R;

import java.util.List;

/**
 * Created by jordi on 08/12/16.
 */

public class AdapterUsuari extends ArrayAdapter<Usuari> {
    private SettingsActivity activity;

    public AdapterUsuari (Context context, List<Usuari> data, SettingsActivity activity) {
        super(context, R.layout.user_element, data);
        this.activity = activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View row = inflater.inflate(R.layout.user_element, parent, false);

        TextView nomUsuari = (TextView) row.findViewById(R.id.nomUsuari);
        String strNomUsuari = getItem(position).getNom();
        nomUsuari.setText(strNomUsuari);

        Switch mSwitch = (Switch) row.findViewById(R.id.switchUsuari);
        final boolean actiu = getItem(position).isActiu();
        mSwitch.setChecked(actiu);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getItem(position).setActiu(isChecked);
                activity.updateActiu(getItem(position));
            }
        });
        return row;
    }
}
