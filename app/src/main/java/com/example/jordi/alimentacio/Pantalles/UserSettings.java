package com.example.jordi.alimentacio.Pantalles;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jordi.alimentacio.Dades.Dades;
import com.example.jordi.alimentacio.R;

/**
 * Created by jordi on 08/12/16.
 */

public class UserSettings extends AppCompatActivity {

    private EditText nomUsuari;
    private EditText edatUsuari;
    private EditText pesUsuari;
    private EditText alturaUsuari;
    private Spinner tipusDieta;
    private CheckBox lactosa;
    private CheckBox gluten;
    private String originalName;
    private Drawable background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTitle("Editar usuari");
        setContentView(R.layout.dialog_user);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initVariables();
    }

    private boolean guardarCanvis () {
        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);
        if (originalName != null) {
            mydatabase.delete(Dades.USUARIS, Dades.NOM + " = ?", new String[]{originalName});
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(Dades.NOM, nomUsuari.getText().toString());
        contentValues.put(Dades.EDAT, Integer.parseInt(edatUsuari.getText().toString()));
        contentValues.put(Dades.PES, Integer.parseInt(pesUsuari.getText().toString()));
        contentValues.put(Dades.ALTURA, Integer.parseInt(alturaUsuari.getText().toString()));
        contentValues.put(Dades.TIPUSDIETA, (int) tipusDieta.getSelectedItemPosition());
        contentValues.put(Dades.LACTOSA, lactosa.isChecked());
        contentValues.put(Dades.GLUTEN, gluten.isChecked());
        contentValues.put(Dades.ACTIU, 1);
        long result = mydatabase.insert(Dades.USUARIS, null, contentValues);
        return result != -1;
    }

    private void loadData () {
        SQLiteDatabase mydatabase = openOrCreateDatabase(Dades.ALIMENTACIO, MODE_PRIVATE, null);
        Cursor resultSet = mydatabase.query(
                Dades.USUARIS,                     // The table to query
                null,                               // The columns to return
                Dades.NOM + " = ?",     // The columns for the WHERE clause
                new String[]{originalName},                // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        resultSet.moveToFirst();
        nomUsuari.setText(originalName);
        edatUsuari.setText(""+resultSet.getInt(resultSet.getColumnIndex(Dades.EDAT)));
        pesUsuari.setText(""+resultSet.getInt(resultSet.getColumnIndex(Dades.PES)));
        alturaUsuari.setText(""+resultSet.getInt(resultSet.getColumnIndex(Dades.ALTURA)));
        tipusDieta.setSelection(resultSet.getInt(resultSet.getColumnIndex(Dades.TIPUSDIETA)));
        lactosa.setChecked(resultSet.getInt(resultSet.getColumnIndex(Dades.LACTOSA)) != 0);
        gluten.setChecked(resultSet.getInt(resultSet.getColumnIndex(Dades.GLUTEN)) != 0);
    }

    private boolean comprovaCamps () {
        nomUsuari.setBackground(background);
        boolean correcte = true;
        try {
            Integer.parseInt(edatUsuari.getText().toString());
            edatUsuari.setBackground(background);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            edatUsuari.setBackgroundColor(Color.RED);
            correcte = false;
        }
        try {
            Integer.parseInt(pesUsuari.getText().toString());
            pesUsuari.setBackground(background);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pesUsuari.setBackgroundColor(Color.RED);
            correcte = false;
        }
        try {
            Integer.parseInt(alturaUsuari.getText().toString());
            alturaUsuari.setBackground(background);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            alturaUsuari.setBackgroundColor(Color.RED);
            correcte = false;
        }
        return correcte;
    }

    private void initVariables () {
        nomUsuari = (EditText) findViewById(R.id.nomUsuari);
        edatUsuari = (EditText) findViewById(R.id.edatUsuari);
        pesUsuari = (EditText) findViewById(R.id.pesUsuari);
        alturaUsuari = (EditText) findViewById(R.id.alturaUsuari);
        tipusDieta = (Spinner) findViewById(R.id.tipusDieta);
        lactosa = (CheckBox) findViewById(R.id.lactosa);
        gluten = (CheckBox) findViewById(R.id.gluten);
        background = alturaUsuari.getBackground();

        originalName = getIntent().getStringExtra(Dades.NOM);
        if (originalName != null) {
            loadData();
        }
    }

    private void generateAlert () {
        new AlertDialog.Builder(this)
                .setTitle("Canvis")
                .setMessage("Els canvis no es guardaran.\nEstà segur que vol tornar a la pantalla anterior?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        UserSettings.this.finish();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                generateAlert();
                return true;
            case R.id.ok:
                if (comprovaCamps()) {
                    if (guardarCanvis()) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra(Dades.NOM, nomUsuari.getText().toString());
                        resultIntent.putExtra(Dades.NOMANTIC, originalName);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {
                        Toast.makeText(UserSettings.this, "El nom " + nomUsuari.getText().toString() + " ja existeix",
                                Toast.LENGTH_SHORT).show();
                        nomUsuari.setBackgroundColor(Color.RED);
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed () {
        generateAlert();
    }
}
