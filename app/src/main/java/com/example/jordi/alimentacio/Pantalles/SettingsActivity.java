package com.example.jordi.alimentacio.Pantalles;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jordi.alimentacio.Adapters.AdapterUsuari;
import com.example.jordi.alimentacio.Dades.Dades;
import com.example.jordi.alimentacio.Dades.Usuari;
import com.example.jordi.alimentacio.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class SettingsActivity extends AppCompatActivity {

    // Edat
    // Altura
    // Pes
    // Tipus de dieta -> esportiva, equilibrada, règim
    // Temps per cuinar -> ja està fet
    // Localització -> dada del mobil, no input d'usuari
    // Radi desplaçament
    // Preferències
    // Alèrgies
    public static final String usuaris = "usuaris";
    public static final String settingsPref = "settingPref";
    public static final String numActius = "numActius";

    private Button addButton;
    private ListView listView;
    private AdapterUsuari adapterUsuari;
    private ArrayList<Usuari> arrayUsuaris;
    private TextView infoNumUsuaris;
    private Spinner[] spinnerDinar;
    private Spinner[] spinnerSopar;
    private Spinner dillunsDinar, dillunsSopar, dimartsDinar, dimartsSopar, dimecresDinar, dimecresSopar, dijousDinar,
            dijousSopar, divendresDinar, divendresSopar, dissabteDinar, dissabteSopar, diumengeDinar, diumengeSopar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initVariables();
        setListeners();
    }

    public void updateActiu (Usuari usuari) {
        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put(Dades.ACTIU, usuari.isActiu() ? 1 : 0);;
        mydatabase.update(Dades.USUARIS, values, Dades.NOM + " = ?", new String[]{usuari.getNom()});
        updateNumUsuaris();
    }

    private void setListeners () {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, UserSettings.class);
                startActivityForResult(intent, 1);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SettingsActivity.this, UserSettings.class);
                intent.putExtra(Dades.NOM, arrayUsuaris.get(position).getNom());
                startActivityForResult(intent, 1);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setMessage("Segur que vols eliminar l'usuari " + arrayUsuaris.get(position) + "?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);
                        mydatabase.delete(Dades.USUARIS, Dades.NOM + " = ?", new String[]{arrayUsuaris.get(position).getNom()});
                        arrayUsuaris.remove(position);
                        adapterUsuari.notifyDataSetChanged();
                        updateNumUsuaris();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    private int updateNumUsuaris () {
        Integer numActius = 0;
        for (Usuari usuari : arrayUsuaris) {
            numActius += usuari.isActiu() ? 1 : 0;
        }
        Integer numUsuaris = arrayUsuaris.size();
        infoNumUsuaris.setText(numActius + " actius / " + numUsuaris + " usuaris");
        return numActius;
    }

    private void initVariables () {
        addButton = (Button) findViewById(R.id.addUser);
        listView = (ListView) findViewById(R.id.llistaUsuaris);
        arrayUsuaris = new ArrayList<>();
        adapterUsuari = new AdapterUsuari(this, arrayUsuaris, this);
        listView.setAdapter(adapterUsuari);
        infoNumUsuaris = (TextView) findViewById(R.id.infoNumUsuaris);

        dillunsDinar = (Spinner) findViewById(R.id.dillunsDinar);
        dillunsSopar = (Spinner) findViewById(R.id.dillunsSopar);
        dimartsDinar = (Spinner) findViewById(R.id.dimartsDinar);
        dimartsSopar = (Spinner) findViewById(R.id.dimartsSopar);
        dimecresDinar = (Spinner) findViewById(R.id.dimecresDinar);
        dimecresSopar = (Spinner) findViewById(R.id.dimecresSopar);
        dijousDinar = (Spinner) findViewById(R.id.dijousDinar);
        dijousSopar = (Spinner) findViewById(R.id.dijousSopar);
        divendresDinar = (Spinner) findViewById(R.id.divendresDinar);
        divendresSopar = (Spinner) findViewById(R.id.dijousSopar);
        dissabteDinar = (Spinner) findViewById(R.id.dissabteDinar);
        dissabteSopar = (Spinner) findViewById(R.id.dissabteSopar);
        diumengeDinar = (Spinner) findViewById(R.id.diumengeDinar);
        diumengeSopar = (Spinner) findViewById(R.id.diumengeSopar);

        spinnerDinar = new Spinner[]{diumengeDinar, dillunsDinar, dimartsDinar, dimecresDinar, dijousDinar, divendresDinar, dissabteDinar};
        spinnerSopar = new Spinner[]{diumengeSopar, dillunsSopar, dimartsSopar, dimecresSopar, dijousSopar, divendresSopar, dissabteSopar};

        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);
//        mydatabase.execSQL("DROP TABLE " + Dades.USUARIS);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Dades.USUARIS + " (" +
                Dades.NOM + " VARCHAR PRIMARY KEY, " +
                Dades.EDAT + " INT, " +
                Dades.ALTURA + " INT, " +
                Dades.PES + " INT," +
                Dades.TIPUSDIETA + " INT," +
                Dades.LACTOSA + " INT," +
                Dades.GLUTEN + " INT," +
                Dades.ACTIU + " INT);");
        Cursor resultSet = mydatabase.rawQuery("Select * from " + Dades.USUARIS, null);
        boolean hiHaDada = resultSet.moveToFirst();
        while (hiHaDada) {
            int columnIndex = resultSet.getColumnIndex(Dades.NOM);
            String nomUsuari = resultSet.getString(columnIndex);
            boolean actiu = resultSet.getInt(resultSet.getColumnIndex(Dades.ACTIU)) != 0;
            arrayUsuaris.add(new Usuari(nomUsuari, actiu));
            hiHaDada = resultSet.moveToNext();
        }
        updateNumUsuaris();
        loadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String nomAntic = data.getExtras().getString(Dades.NOMANTIC);
                if (nomAntic != null) {
                    arrayUsuaris.remove(new Usuari(nomAntic, true));
                }
                arrayUsuaris.add(new Usuari(data.getExtras().getString(Dades.NOM), true));
                adapterUsuari.notifyDataSetChanged();
                updateNumUsuaris();
            }
        }
    }

    private void loadData () {
        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Dades.TEMPSLLIURE + " (" +
                Dades.DIASETMANA + " VARCHAR PRIMARY KEY, " +
                Dades.TEMPSDINAR + " INT, " + // indica posicio en l'array arrayTemps, no el temps lliure en si
                Dades.TEMPSSOPAR + " INT);"); // indica posicio en l'array arrayTemps, no el temps lliure en si

        Cursor resultSet = mydatabase.rawQuery("Select * from " + Dades.TEMPSLLIURE, null);
        boolean hiHaDada = resultSet.moveToFirst();
        int i = 0;
        while (hiHaDada) {
            spinnerDinar[i].setSelection(resultSet.getInt(resultSet.getColumnIndex(Dades.TEMPSDINAR)));
            spinnerSopar[i].setSelection(resultSet.getInt(resultSet.getColumnIndex(Dades.TEMPSSOPAR)));
            hiHaDada = resultSet.moveToNext();
            ++i;
        }
    }

    private void saveChanges () {
        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);

        mydatabase.delete(Dades.TEMPSLLIURE, null, null);
        for (int i = 0; i < 7; ++i) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Dades.DIASETMANA, Dades.daysOfWeek[i]);
            contentValues.put(Dades.TEMPSDINAR, spinnerDinar[i].getSelectedItemPosition());
            contentValues.put(Dades.TEMPSSOPAR, spinnerSopar[i].getSelectedItemPosition());
            mydatabase.insert(Dades.TEMPSLLIURE, null, contentValues);
        }

        // guardar el nombre d'usuaris actius
        SharedPreferences sharedPref = this.getSharedPreferences(settingsPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(numActius, updateNumUsuaris());
        editor.commit();
        // generar i guardar els plats
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Dades.PLANIFICACIO + " (" +
                Dades.DIAINT + " INT PRIMARY KEY, " +
                Dades.PRIMERESMORZAR + " VARCHAR, " +
                Dades.SEGONESMORZAR + " VARCHAR, " +
                Dades.POSTREESMORZAR + " VARCHAR, " +
                Dades.PRIMERDINAR + " VARCHAR, " +
                Dades.SEGONDINAR + " VARCHAR, " +
                Dades.POSTREDINAR + " VARCHAR, " +
                Dades.PRIMERSOPAR + " VARCHAR, " +
                Dades.SEGONSOPAR + " VARCHAR, " +
                Dades.POSTRESOPAR + " VARCHAR);");

        mydatabase.delete(Dades.PLANIFICACIO, null, null);
        Random random = new Random();
        for (int i = 0; i < 7; ++i) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Dades.DIAINT, i);
            contentValues.put(Dades.PRIMERESMORZAR, Dades.primersEsmorzar.get(random.nextInt(Dades.primersEsmorzar.size())).getNom());
            contentValues.put(Dades.SEGONESMORZAR, Dades.segonsEsmorzar.get(random.nextInt(Dades.segonsEsmorzar.size())).getNom());
            contentValues.put(Dades.POSTREESMORZAR, Dades.postresEsmorzar.get(random.nextInt(Dades.postresEsmorzar.size())).getNom());
            contentValues.put(Dades.PRIMERDINAR, Dades.primersDinar.get(random.nextInt(Dades.primersDinar.size())).getNom());
            contentValues.put(Dades.SEGONDINAR, Dades.segonsDinar.get(random.nextInt(Dades.segonsDinar.size())).getNom());
            contentValues.put(Dades.POSTREDINAR, Dades.postresDinar.get(random.nextInt(Dades.postresDinar.size())).getNom());
            contentValues.put(Dades.PRIMERSOPAR, Dades.primersDinar.get(random.nextInt(Dades.primersDinar.size())).getNom());
            contentValues.put(Dades.SEGONSOPAR, Dades.segonsDinar.get(random.nextInt(Dades.segonsDinar.size())).getNom());
            contentValues.put(Dades.POSTRESOPAR, Dades.postresDinar.get(random.nextInt(Dades.postresDinar.size())).getNom());
            mydatabase.insert(Dades.PLANIFICACIO, null, contentValues);
        }

        setResult(RESULT_OK);
    }

    private void generateAlert (String message, final boolean save) {
        new AlertDialog.Builder(this)
                .setTitle("Canvis")
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (save) {
                            saveChanges();
                        }
                        SettingsActivity.this.finish();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                generateAlert("Els canvis no es guardaran.\nEstà segur que vol tornar a la pantalla anterior?", false);
                return true;
            case R.id.ok:
                generateAlert("Els canvis es guardaran i es generarà un nou menú.\nVol continuar?", true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed () {
        generateAlert("Els canvis no es guardaran.\nEstà segur que vol tornar a la pantalla anterior?", false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
