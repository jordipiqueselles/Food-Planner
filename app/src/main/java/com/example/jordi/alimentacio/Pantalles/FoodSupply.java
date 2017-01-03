package com.example.jordi.alimentacio.Pantalles;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.jordi.alimentacio.Adapters.AdapterAliment;
import com.example.jordi.alimentacio.Dades.Aliment;
import com.example.jordi.alimentacio.Dades.Dades;
import com.example.jordi.alimentacio.Dades.Dia;
import com.example.jordi.alimentacio.Dades.MenuNyam;
import com.example.jordi.alimentacio.Dades.Plat;
import com.example.jordi.alimentacio.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FoodSupply extends AppCompatActivity {

    private int ini = 0;
    private int fin = 6;
    List<String> spinnerArray =  new ArrayList<String>();
    private ArrayList<Map.Entry<Aliment, Double>> listItems;
    int  year, month, day;
    private Spinner from, to;
    ArrayList<Dia> diaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_supply);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Llista compra");

        getAllDies();

        from = (Spinner) findViewById(R.id.spinnerFrom);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        String curDate = new String(day+"/"+month+"/"+year);
        spinnerArray.add(curDate);
        for (int i = 0; i < 6; ++i) {
            try {
                curDate = getNextDate(curDate);
                spinnerArray.add(curDate);
            }
            catch (Exception e) {

            }
        }

        listItems = getAliments();
        final ListView listView = (ListView) findViewById(R.id.listIngredients);
        final AdapterAliment adapterIngredients = new AdapterAliment(this, listItems);
        listView.setAdapter(adapterIngredients);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from = (Spinner) findViewById(R.id.spinnerFrom);
        to = (Spinner) findViewById(R.id.spinnerTo);
        from.setAdapter(adapter);
        to.setAdapter(adapter);

        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                // Object item = parentView.getItemAtPosition(position);

                ini = from.getSelectedItemPosition();
                listItems.clear();
                listItems = getAliments();
                AdapterAliment adapterIngredients = new AdapterAliment(FoodSupply.this, listItems);
                listView.setAdapter(adapterIngredients);
            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });

        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                // Object item = parentView.getItemAtPosition(position);

                fin = to.getSelectedItemPosition();
                listItems.clear();
                listItems = getAliments();
                AdapterAliment adapterIngredients = new AdapterAliment(FoodSupply.this, listItems);
                listView.setAdapter(adapterIngredients);

            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });

        Button comprar = (Button) findViewById(R.id.comprar);
        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodSupply.this, ActivityMaps.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Map.Entry<Aliment, Double>> getAliments () {
        TreeMap<Aliment, Double> allAliments = new TreeMap<>();
        for (int i = ini; i <= fin; ++i) {
            for (Map.Entry<Aliment, Double> elem : diaArrayList.get(i).getAllAliments().entrySet()) {
                if (allAliments.containsKey(elem.getKey())) {
                    allAliments.put(elem.getKey(), allAliments.get(elem.getKey()) + elem.getValue());
                } else {
                    allAliments.put(elem.getKey(), elem.getValue());
                }
            }
        }
        return new ArrayList<Map.Entry<Aliment, Double>>(allAliments.entrySet());
    }

    private void getAllDies () {
        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);
        Cursor resultSet = mydatabase.query(
                Dades.PLANIFICACIO,                     // The table to query
                null,                               // The columns to return
                null,     // The columns for the WHERE clause
                null,                // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        diaArrayList = new ArrayList<>();
        boolean moreResults = resultSet.moveToFirst();
        while (moreResults) {
            Plat primer = getPlat(resultSet, Dades.primersEsmorzar, Dades.PRIMERESMORZAR);
            Plat segon = getPlat(resultSet, Dades.segonsEsmorzar, Dades.SEGONESMORZAR);
            Plat postre = getPlat(resultSet, Dades.postresEsmorzar, Dades.POSTREESMORZAR);
            MenuNyam esmorzar = new MenuNyam(primer, segon, postre, Dades.ESMORZAR);

            primer = getPlat(resultSet, Dades.primersDinar, Dades.PRIMERDINAR);
            segon = getPlat(resultSet, Dades.segonsDinar, Dades.SEGONDINAR);
            postre = getPlat(resultSet, Dades.postresDinar, Dades.POSTREDINAR);
            MenuNyam dinar = new MenuNyam(primer, segon, postre, Dades.DINAR);

            primer = getPlat(resultSet, Dades.primersDinar, Dades.PRIMERSOPAR);
            segon = getPlat(resultSet, Dades.segonsDinar, Dades.SEGONSOPAR);
            postre = getPlat(resultSet, Dades.postresDinar, Dades.POSTRESOPAR);
            MenuNyam sopar = new MenuNyam(primer, segon, postre, Dades.SOPAR);

            Dia dia = new Dia(esmorzar, dinar, sopar);
            diaArrayList.add(dia);

            moreResults = resultSet.moveToNext();
        }
    }

    private Plat getPlat (Cursor resultSet, ArrayList<Plat> conjuntPlats, String menjada) {
        Plat plat = null;
        for (Plat aux : conjuntPlats) {
            if (aux.getNom().equals(resultSet.getString(resultSet.getColumnIndex(menjada)))) {
                plat = aux;
                break;
            }
        }
        return plat;
    }

    public static String getNextDate(String  curDate) throws Exception {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final Date date = format.parse(curDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
