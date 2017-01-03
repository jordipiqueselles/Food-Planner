package com.example.jordi.alimentacio;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.LiveFolders;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.jordi.alimentacio.Adapters.AdapterListDies;
import com.example.jordi.alimentacio.Adapters.AdapterMenjada;
import com.example.jordi.alimentacio.Dades.Dades;
import com.example.jordi.alimentacio.Dades.Dia;
import com.example.jordi.alimentacio.Dades.MenuNyam;
import com.example.jordi.alimentacio.Dades.Plat;
import com.example.jordi.alimentacio.Pantalles.ActivityDayMenu;
import com.example.jordi.alimentacio.Pantalles.FoodSupply;
import com.example.jordi.alimentacio.Pantalles.SettingsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Dades.createData(this);
        initVariables();
    }

    private void initListDays () {
        viewFlipper.setDisplayedChild(2);
        setTitle("Menú setmanal");

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

        ArrayList<Dia> diaArrayList = new ArrayList<>();
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

        AdapterListDies adapterListDies = new AdapterListDies(this, diaArrayList);
        ListView listView = (ListView) findViewById(R.id.listAllDays);
        listView.setAdapter(adapterListDies);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ActivityDayMenu.class);
                intent.putExtra(ActivityDayMenu.POSITION, position);
                startActivity(intent);
            }
        });
    }

    private String createDate () {
        Calendar calendar = Calendar.getInstance();
        return "" + Dades.daysOfWeek[calendar.get(Calendar.DAY_OF_WEEK)-1] + ", " + calendar.get(Calendar.DAY_OF_MONTH) +
                " " + Dades.months[calendar.get(Calendar.MONTH)];
    }

    private void initDayMenu () {
        viewFlipper.setDisplayedChild(1);
        setTitle("Avui");

        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);
        Cursor resultSet = mydatabase.query(
                Dades.PLANIFICACIO,                     // The table to query
                null,                               // The columns to return
                Dades.DIAINT + " = 0",     // The columns for the WHERE clause
                null,                // The values for the WHERE clause
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
        textView.setText(createDate());
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

    private void initVariables () {
        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);
        boolean hiHaPlan = false;
        try {
            mydatabase.rawQuery("Select * from " + Dades.PLANIFICACIO, null);
            hiHaPlan = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        if (hiHaPlan) {
            initDayMenu();
        } else {
            viewFlipper.setDisplayedChild(0);
        }
//        viewFlipper.setFlipInterval(3000);
//        viewFlipper.setAutoStart(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (viewFlipper.getDisplayedChild() == 0 || viewFlipper.getDisplayedChild() == 1) {
                    initDayMenu();
                } else if (viewFlipper.getDisplayedChild() == 0 || viewFlipper.getDisplayedChild() == 2) {
                    viewFlipper.setDisplayedChild(0);
                    initListDays();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, 2);
            //overridePendingTransition();
        } else if (id == R.id.hoy) {
            initDayMenu();
        } else if (id == R.id.menuSemanal) {
            initListDays();
        } else if (id == R.id.listaCompra) {
            Intent intent = new Intent(this, FoodSupply.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
