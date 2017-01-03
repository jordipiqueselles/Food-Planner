package com.example.jordi.alimentacio.Dades;

import android.content.Intent;

/**
 * Created by jordi on 17/12/16.
 */

public class Usuari {
    private String nom;
    private Integer altura;
    private Integer pes;
    private Integer edat;
    private Integer tipusDieta;
    private boolean gluten;
    private boolean lactosa;
    private boolean actiu;

    public Usuari(String nom, boolean actiu) {
        this.nom = nom;
        this.actiu = actiu;
    }

    public Usuari(String nom, Integer altura, Integer pes, Integer edat,
                  Integer tipusDieta, boolean gluten, boolean lactosa, boolean actiu) {
        this.nom = nom;
        this.altura = altura;
        this.pes = pes;
        this.edat = edat;
        this.tipusDieta = tipusDieta;
        this.gluten = gluten;
        this.lactosa = lactosa;
        this.actiu = actiu;
    }

    @Override
    public boolean equals (Object o) {
        return nom.equals(((Usuari) o).getNom());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Integer getPes() {
        return pes;
    }

    public void setPes(Integer pes) {
        this.pes = pes;
    }

    public Integer getEdat() {
        return edat;
    }

    public void setEdat(Integer edat) {
        this.edat = edat;
    }

    public Integer getTipusDieta() {
        return tipusDieta;
    }

    public void setTipusDieta(Integer tipusDieta) {
        this.tipusDieta = tipusDieta;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public boolean isLactosa() {
        return lactosa;
    }

    public void setLactosa(boolean lactosa) {
        this.lactosa = lactosa;
    }

    public boolean isActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }
}
