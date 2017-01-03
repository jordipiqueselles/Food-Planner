package com.example.jordi.alimentacio.Dades;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jordi on 16/12/16.
 */

public class MenuNyam {
    private Plat primer;
    private Plat segon;
    private Plat postre;
    private String menjada;

    public MenuNyam(Plat primer, Plat segon, Plat postre, String menjada) {
        this.primer = primer;
        this.segon = segon;
        this.postre = postre;
        this.menjada = menjada;
    }

    public Plat getPrimer() {
        return primer;
    }

    public void setPrimer(Plat primer) {
        this.primer = primer;
    }

    public Plat getSegon() {
        return segon;
    }

    public void setSegon(Plat segon) {
        this.segon = segon;
    }

    public Plat getPostre() {
        return postre;
    }

    public void setPostre(Plat postre) {
        this.postre = postre;
    }

    public String getMenjada() {
        return menjada;
    }

    public void setMenjada(String menjada) {
        this.menjada = menjada;
    }

    public TreeMap<Aliment, Double> getAllAliments () {
        TreeMap<Aliment, Double> aliments = new TreeMap<>();
        Plat[] plats = new Plat[]{primer, segon, postre};
        for (Plat plat : plats) {
            for (Map.Entry<Aliment, Double> elem : plat.getConjuntAliments().entrySet()) {
                if (aliments.containsKey(elem.getKey())) {
                    aliments.put(elem.getKey(), aliments.get(elem.getKey()) + elem.getValue());
                } else {
                    aliments.put(elem.getKey(), elem.getValue());
                }
            }
        }
        return aliments;
    }
}
