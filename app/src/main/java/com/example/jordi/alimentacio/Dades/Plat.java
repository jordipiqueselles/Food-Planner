package com.example.jordi.alimentacio.Dades;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by jordi on 16/12/16.
 */

public class Plat implements Comparable{
    private String nom;
    private TreeMap<Aliment, Double> conjuntAliments;
    private String recepta;
    private Integer tempsPreparacio;
    private int idImatge;

    public Plat(String nom, TreeMap<Aliment, Double> conjuntAliments, String recepta, Integer tempsPreparacio, int imatge) {
        this.nom = nom;
        this.conjuntAliments = conjuntAliments;
        this.recepta = recepta;
        this.tempsPreparacio = tempsPreparacio;
        this.idImatge = imatge;
    }

    @Override
    public int compareTo(Object o) {
        return nom.compareTo(((Plat) o).getNom());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TreeMap<Aliment, Double> getConjuntAliments() {
        return conjuntAliments;
    }

    public void setConjuntAliments(TreeMap<Aliment, Double> conjuntAliments) {
        this.conjuntAliments = conjuntAliments;
    }

    public String getRecepta() {
        return recepta;
    }

    public void setRecepta(String recepta) {
        this.recepta = recepta;
    }

    public Integer getTempsPreparacio() {
        return tempsPreparacio;
    }

    public void setTempsPreparacio(Integer tempsPreparacio) {
        this.tempsPreparacio = tempsPreparacio;
    }

    public int getIdImatge() {
        return idImatge;
    }

    public void setIdImatge(int idImatge) {
        this.idImatge = idImatge;
    }
}
