package com.example.jordi.alimentacio.Dades;

import java.util.HashSet;
import java.util.List;

/**
 * Created by jordi on 16/12/16.
 */

public class Aliment implements Comparable{
    public enum caract {
        CARN, PEIX, GLUTEN, LACTOSA
    }
    public static final String kg = "kg";
    public static final String g = "g";
    public static final String u = "u";
    public static final String l = "l";

    private String nom;
    private HashSet<caract> caracteristiques;
    private String unitats;
    private Integer calories; // per cada unitat del tipus que sigui

    public Aliment(String nom, String unitats) {
        this.nom = nom;
        this.caracteristiques = new HashSet<>();
        this.unitats = unitats;
    }

    public Aliment(String nom, String unitats, List<caract> caracteristiques) {
        this.nom = nom;
        this.caracteristiques = new HashSet<>(caracteristiques);
        this.unitats = unitats;
    }

    @Override
    public int compareTo(Object o) {
        return nom.compareTo(((Aliment) o).getNom());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public HashSet<caract> getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(HashSet<caract> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public String getUnitats() {
        return unitats;
    }

    public void setUnitats(String unitats) {
        this.unitats = unitats;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}
