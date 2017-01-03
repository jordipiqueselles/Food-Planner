package com.example.jordi.alimentacio.Dades;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jordi on 16/12/16.
 */

public class Dia {
    private MenuNyam esmorzar;
    private MenuNyam dinar;
    private MenuNyam sopar;

    public Dia(MenuNyam esmorzar, MenuNyam dinar, MenuNyam sopar) {
        this.esmorzar = esmorzar;
        this.dinar = dinar;
        this.sopar = sopar;
    }

    public MenuNyam getEsmorzar() {
        return esmorzar;
    }

    public void setEsmorzar(MenuNyam esmorzar) {
        this.esmorzar = esmorzar;
    }

    public MenuNyam getDinar() {
        return dinar;
    }

    public void setDinar(MenuNyam dinar) {
        this.dinar = dinar;
    }

    public MenuNyam getSopar() {
        return sopar;
    }

    public void setSopar(MenuNyam sopar) {
        this.sopar = sopar;
    }

    public TreeMap<Aliment, Double> getAllAliments () {
        TreeMap<Aliment, Double> aliments = new TreeMap<>();
        MenuNyam[] menus = new MenuNyam[]{esmorzar, dinar, sopar};
        for (MenuNyam menuNyam : menus) {
            for (Map.Entry<Aliment, Double> elem : menuNyam.getAllAliments().entrySet()) {
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
