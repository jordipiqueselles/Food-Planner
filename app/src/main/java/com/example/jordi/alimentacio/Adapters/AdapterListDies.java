package com.example.jordi.alimentacio.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jordi.alimentacio.Dades.Dades;
import com.example.jordi.alimentacio.Dades.Dia;
import com.example.jordi.alimentacio.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by jordi on 16/12/16.
 */

public class AdapterListDies extends ArrayAdapter<Dia> {
    public AdapterListDies(Context context, List<Dia> objects) {
        super(context, R.layout.dia_element_list,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View row = inflater.inflate(R.layout.dia_element_list, parent, false);
        final Dia dia = getItem(position);

        TextView dayWeek = (TextView) row.findViewById(R.id.dayWeek);
        TextView dayMonth = (TextView) row.findViewById(R.id.dayMonth);
        TextView breakfastFirstDish = (TextView) row.findViewById(R.id.breakfastFirstDish);
        TextView breakfastSecondDish = (TextView) row.findViewById(R.id.breakfastSecondDish);
        TextView breakfastThirdDish = (TextView) row.findViewById(R.id.breakfastThirdDish);
        TextView lunchFirstDish = (TextView) row.findViewById(R.id.lunchFirstDish);
        TextView lunchSecondDish = (TextView) row.findViewById(R.id.lunchSecondDish);
        TextView lunchThirdDish = (TextView) row.findViewById(R.id.lunchThirdDish);
        TextView dinnerFirstDish = (TextView) row.findViewById(R.id.dinnerFirstDish);
        TextView dinnerSecondDish = (TextView) row.findViewById(R.id.dinnerSecondDish);
        TextView dinnerThirdDish = (TextView) row.findViewById(R.id.dinnerThirdDish);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis() + position*24*3600*1000);
        dayWeek.setText(Dades.daysOfWeek[calendar.get(Calendar.DAY_OF_WEEK)-1]);
        dayMonth.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH));
        breakfastFirstDish.setText(dia.getEsmorzar().getPrimer().getNom());
        breakfastSecondDish.setText(dia.getEsmorzar().getSegon().getNom());
        breakfastThirdDish.setText(dia.getEsmorzar().getPostre().getNom());
        lunchFirstDish.setText(dia.getDinar().getPrimer().getNom());
        lunchSecondDish.setText(dia.getDinar().getSegon().getNom());
        lunchThirdDish.setText(dia.getDinar().getPostre().getNom());
        dinnerFirstDish.setText(dia.getSopar().getPrimer().getNom());
        dinnerSecondDish.setText(dia.getSopar().getSegon().getNom());
        dinnerThirdDish.setText(dia.getSopar().getPostre().getNom());

        return row;
    }
}
