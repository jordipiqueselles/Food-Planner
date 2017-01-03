package com.example.jordi.alimentacio.Pantalles;

import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jordi.alimentacio.Dades.Aliment;
import com.example.jordi.alimentacio.Dades.Dades;
import com.example.jordi.alimentacio.R;

import java.util.Map;

public class DishInfo extends AppCompatActivity {

    private TextView title;
    private TextView textIngredients;
    private TextView explanationCook;
    private TextView tempsElaboracio;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_info);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Informació");

        title = (TextView) findViewById(R.id.title);
        textIngredients = (TextView) findViewById(R.id.textIngredients);
        explanationCook = (TextView) findViewById(R.id.explanationCook);
        tempsElaboracio = (TextView) findViewById(R.id.textTempsElaboracio);
        imageView = (ImageView) findViewById(R.id.imgDish);

        title.setText(Dades.selectedDish.getNom());
        explanationCook.setText(Dades.selectedDish.getRecepta());
        tempsElaboracio.setText(Dades.selectedDish.getTempsPreparacio() + " min");
        imageView.setImageDrawable(ContextCompat.getDrawable(this, Dades.selectedDish.getIdImatge()));
        String ingredients = "";
        for (Map.Entry<Aliment, Double> elem : Dades.selectedDish.getConjuntAliments().entrySet()) {
            ingredients += "- " + elem.getKey().getNom() + "   " + elem.getValue() + " " + elem.getKey().getUnitats() + "\n";
        }
        textIngredients.setText(ingredients);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
