package com.example.jordi.alimentacio.Pantalles;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jordi.alimentacio.Adapters.AdapterMenjada;
import com.example.jordi.alimentacio.Dades.Dades;
import com.example.jordi.alimentacio.Dades.MenuNyam;
import com.example.jordi.alimentacio.Dades.Plat;
import com.example.jordi.alimentacio.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ActivityDayMenu extends AppCompatActivity {

    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_menu);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int offsetDay = getIntent().getIntExtra(POSITION, 0);

        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);
        Cursor resultSet = mydatabase.query(
                Dades.PLANIFICACIO,                     // The table to query
                null,                               // The columns to return
                Dades.DIAINT + " = ?",     // The columns for the WHERE clause
                new String[]{"" + offsetDay},        // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        resultSet.moveToFirst();
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

        ListView listView = (ListView) findViewById(R.id.listMainMenu);
        ArrayList<MenuNyam> menuNyams = new ArrayList<>(Arrays.asList(esmorzar, dinar, sopar));
        AdapterMenjada adapterMenjada = new AdapterMenjada(this, menuNyams);
        listView.setAdapter(adapterMenjada);

        TextView textView = (TextView) findViewById(R.id.date);
        textView.setText(createDate(offsetDay));
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

    private String createDate (int offsetDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis() + offsetDay*24*3600*1000); // adding one day, if necessary
        return "" + Dades.daysOfWeek[calendar.get(Calendar.DAY_OF_WEEK)-1] + ", " + calendar.get(Calendar.DAY_OF_MONTH) +
                " " + Dades.months[calendar.get(Calendar.MONTH)];
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
