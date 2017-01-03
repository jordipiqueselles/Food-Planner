package com.example.jordi.alimentacio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jordi.alimentacio.Dades.Dades;
import com.example.jordi.alimentacio.Dades.MenuNyam;
import com.example.jordi.alimentacio.Pantalles.DishInfo;
import com.example.jordi.alimentacio.R;

import java.util.List;

/**
 * Created by jordi on 17/12/16.
 */

public class AdapterMenjada extends ArrayAdapter<MenuNyam> {
    Context context;

    public AdapterMenjada(Context context, List<MenuNyam> data) {
        super(context, R.layout.menjada, data);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View row = inflater.inflate(R.layout.menjada, parent, false);
        final MenuNyam eatingMeal = getItem(position);

        ImageView imageFirstDish = (ImageView) row.findViewById(R.id.imageFirstDish);

        TextView textEatingTime = (TextView) row.findViewById(R.id.textEatingTime);
        TextView textFirstDish = (TextView) row.findViewById(R.id.textFirstDish);
        TextView textSecondDish = (TextView) row.findViewById(R.id.textSecondDish);
        TextView textThirdDish = (TextView) row.findViewById(R.id.textThirdDish);
        TextView timeFirstDish = (TextView) row.findViewById(R.id.timeFirstDish);
        TextView timeSecondDish = (TextView) row.findViewById(R.id.timeSecondDish);
        TextView timeThirdDish = (TextView) row.findViewById(R.id.timeThirdDish);

        textEatingTime.setText(eatingMeal.getMenjada());
        textFirstDish.setText(eatingMeal.getPrimer().getNom());
        textSecondDish.setText(eatingMeal.getSegon().getNom());
        textThirdDish.setText(eatingMeal.getPostre().getNom());
        timeFirstDish.setText(eatingMeal.getPrimer().getTempsPreparacio().toString() + " min");
        timeSecondDish.setText(eatingMeal.getSegon().getTempsPreparacio().toString() + " min");
        timeThirdDish.setText(eatingMeal.getPostre().getTempsPreparacio().toString() + " min");

        imageFirstDish.setImageDrawable(ContextCompat.getDrawable(context, eatingMeal.getPrimer().getIdImatge()));

        Button infoFirstDish = (Button) row.findViewById(R.id.infoFirstDish);
        Button infoSecondDish = (Button) row.findViewById(R.id.infoSecondDish);
        Button infoThirdDish = (Button) row.findViewById(R.id.infoThirdDish);

        setListeners(infoFirstDish, eatingMeal, 0);
        setListeners(infoSecondDish, eatingMeal, 1);
        setListeners(infoThirdDish, eatingMeal, 2);

        return row;
    }

    private void setListeners (Button info, final MenuNyam eatingMeal, final int pos) {
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create new Info activity
                Intent intent = new Intent(context, DishInfo.class);
                //intent.putExtra("name", dish);
                switch (pos) {
                    case 0:
                        Dades.selectedDish = eatingMeal.getPrimer();
                        break;
                    case 1:
                        Dades.selectedDish = eatingMeal.getSegon();
                        break;
                    case 2:
                        Dades.selectedDish = eatingMeal.getPostre();
                        break;
                    default:
                        break;
                }
                context.startActivity(intent);
            }
        });
    }
}
