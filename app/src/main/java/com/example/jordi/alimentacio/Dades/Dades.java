package com.example.jordi.alimentacio.Dades;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.example.jordi.alimentacio.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by jordi on 16/12/16.
 */

public class Dades {
//    public static ArrayList<Eat> demoMenuList = new ArrayList<Eat>(); // not necessary right now
// Edat
// Altura
// Pes
// Tipus de dieta -> esportiva, equilibrada, règim
// Temps per cuinar -> ja està fet
// Localització -> dada del mobil, no input d'usuari
// Radi desplaçament
// Preferències
// Alèrgies

    public static final String ALIMENTACIO = "ALIMENTACIO";
    public static final String USUARIS = "Usuaris";
    public static final String NOMANTIC = "NomAntic";
    public static final String NOM = "Nom";
    public static final String EDAT = "Edat";
    public static final String ALTURA = "Altura";
    public static final String PES = "Pes";
    public static final String TIPUSDIETA = "TipusDieta";
    public static final String LACTOSA = "Lactosa";
    public static final String GLUTEN = "Gluten";
    public static final String ACTIU = "Actiu";
    public static final String TEMPSLLIURE = "TempsLliure";
    public static final String TEMPSDINAR = "TempsDinar";
    public static final String TEMPSSOPAR = "TempsSopar";
    public static final String PLANIFICACIO = "Planificacio";
    public static final String DIAINT = "diaInt";
    public static final String PRIMERESMORZAR  = "primerEsmorzar";
    public static final String SEGONESMORZAR = "segonEsmorzar";
    public static final String POSTREESMORZAR = "postreEsmorzar";
    public static final String PRIMERDINAR = "primerDinar";
    public static final String SEGONDINAR = "segonDinar";
    public static final String POSTREDINAR = "postreDinar";
    public static final String PRIMERSOPAR = "primerSopar";
    public static final String SEGONSOPAR = "segonSopar";
    public static final String POSTRESOPAR = "postreSopar";

//    public static TreeMap<Integer, DailySchedule> weeklySchedule = new TreeMap<Integer, DailySchedule>();

    public static final String DIASETMANA = "DiaSetmana";
    public static final String ESMORZAR = "Esmorzar";
    public static final String DINAR = "Dinar";
    public static final String SOPAR = "Sopar";
    public static final String[] daysOfWeek = {"Diumenge", "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte"};
    public static final String[] months = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre",
        "Octubre", "Novembre", "Desembre"};
    public static final Integer[] arrayTemps = {10, 20, 30, 40, 50, 60, 9999};

    public static ArrayList<Aliment> conjuntAliments = new ArrayList<>();
    public static ArrayList<Plat> primersDinar = new ArrayList<>();
    public static ArrayList<Plat> segonsDinar = new ArrayList<>();
    public static ArrayList<Plat> postresDinar = new ArrayList<>();
    public static ArrayList<Plat> primersEsmorzar = new ArrayList<>();
    public static ArrayList<Plat> segonsEsmorzar = new ArrayList<>();
    public static ArrayList<Plat> postresEsmorzar = new ArrayList<>();

    public static Plat selectedDish;

    private static Activity activityAux;
    private static boolean dadesCreades = false;

    public static void createData (Activity activity) {
        activityAux = activity;
        if (! dadesCreades) {
            createNyamNyam();
        }
    }

    private static void createNyamNyam () {
        // ALIMENTS //
        Aliment arros = new Aliment("Arròs", Aliment.g);
        Aliment lletAliment = new Aliment("Llet", Aliment.l, Arrays.asList(Aliment.caract.LACTOSA));
        Aliment pomaAliment = new Aliment("Poma", Aliment.u);
        Aliment platanAliment = new Aliment("Plàtan", Aliment.u);
        Aliment taronjaAliment = new Aliment("Taronja", Aliment.u);
        Aliment peraAliment = new Aliment("Pera", Aliment.u);
        Aliment enciam = new Aliment("Enciam", Aliment.g);
        Aliment bacon = new Aliment("Bacon", Aliment.g, Arrays.asList(Aliment.caract.CARN));
        Aliment cousCousAliment = new Aliment("Cous cous", Aliment.g, Arrays.asList(Aliment.caract.GLUTEN));
        Aliment lletSoja = new Aliment("Llet de soja", Aliment.l);
        Aliment galeta = new Aliment("Galeta", Aliment.u, Arrays.asList(Aliment.caract.GLUTEN));
        Aliment caldoPollastre = new Aliment("Caldo pollastre", Aliment.l, Arrays.asList(Aliment.caract.CARN));
        Aliment galets = new Aliment("Galets", Aliment.g, Arrays.asList(Aliment.caract.GLUTEN));
        Aliment pitPollastre = new Aliment("Pit de pollastre", Aliment.g, Arrays.asList(Aliment.caract.CARN));
        Aliment farina = new Aliment("Farina", Aliment.g, Arrays.asList(Aliment.caract.GLUTEN));
        Aliment paRatllat = new Aliment("Pa ratllat", Aliment.g, Arrays.asList(Aliment.caract.GLUTEN));
        Aliment formatge = new Aliment("Formatge", Aliment.g, Arrays.asList(Aliment.caract.LACTOSA));
        Aliment ou = new Aliment("Ous", Aliment.u);
        Aliment yatekomoAliment = new Aliment("Yatekomo", Aliment.u, Arrays.asList(Aliment.caract.GLUTEN));
        Aliment alls = new Aliment("Alls", Aliment.u);
        Aliment ceba = new Aliment("Ceba", Aliment.u);
        Aliment tomataFregida = new Aliment("Tomata fregida", Aliment.g);
        Aliment hamburguesa = new Aliment("Hamburguesa", Aliment.u, Arrays.asList(Aliment.caract.CARN));
        Aliment pastanaga = new Aliment("Pastanaga", Aliment.u);
        Aliment carbasso = new Aliment("Carbassó", Aliment.u);
        Aliment bacallaAliment = new Aliment("Bacallà", Aliment.u, Arrays.asList(Aliment.caract.PEIX));
        Aliment conill = new Aliment("Conill", Aliment.g, Arrays.asList(Aliment.caract.CARN));
        Aliment iogurtAliment = new Aliment("Iogurt", Aliment.u, Arrays.asList(Aliment.caract.LACTOSA));
        Aliment sucPressecAliment = new Aliment("Suc de préssec", Aliment.l);

        // DINAR I SOPAR //

        // PRIMERS PLATS //
        Plat sopa = new Plat("Sopa", setIngredients(new Aliment[]{caldoPollastre, galets}, new Double[]{0.5, 50.}),
                "Posa a bullir el caldo i afegeix els galets. Deixa bullir durant 20 min", 25,
                (R.drawable.soup));
        Plat cousCousPlat = new Plat("Cous cous", setIngredients(new Aliment[]{cousCousAliment, ceba, pastanaga, carbasso},
                new Double[]{100., 1., 2., 1.,}), "Posa aigua a bullir i tira-hi el cous cous. Apaga el foc. A continuació," +
                "salteja les verdures i, finalment, barreja-ho tot", 30, (R.drawable.cous_cous));
        Plat yatekomoPlat = new Plat("Yatekomo", setIngredients(new Aliment[]{yatekomoAliment}, new Double[]{1.}), "Posa aigua " +
                "a bullir i tira-la a l'envàs del Yatekomo", 5, (R.drawable.yatekomo));
        Plat arrosCubana = new Plat("Arròs a la cubana", setIngredients(new Aliment[]{arros, tomataFregida, ou},
                new Double[]{100., 50., 1.}), "Fes bullir l'arròs. A continuació, fregeix l'ou, escalfa la tomata i " +
                "barreja-ho tot", 10, (R.drawable.arros_cubana));
        Plat arrosBullit = new Plat("Arròs bullit", setIngredients(new Aliment[]{arros, alls}, new Double[]{100., 4.}),
                "Posa l'arròs a bullir durant 15 min juntament amb els alls", 20,
                (R.drawable.arros_bullit));
        Plat amanida = new Plat("Amanida", setIngredients(new Aliment[]{enciam, pastanaga, formatge}, new Double[]
                {100., 1., 50.}), "Renta l'enciam, ratlla la pastanaga i afegeix el formatge tallat en bocins petits", 3,
                (R.drawable.amanida));
        primersDinar.addAll(Arrays.asList(sopa, cousCousPlat, yatekomoPlat, arrosCubana, arrosBullit, amanida));

        // SEGONS PLATS //
        Plat pollastreArrebossat = new Plat("Pollastre arrebossat", setIngredients(new Aliment[]{pitPollastre, ou, paRatllat},
                new Double[]{100., 1., 20.}), "Unta el pollastre amb l'ou batut i arrebossa'l amb el pa rattlat." +
                "Després, posa'l a la paella durant 5 min", 15, (R.drawable.pollastre_arrebossat));
        Plat americanHamburguer = new Plat("Hamburguesa americana", setIngredients(new Aliment[]{hamburguesa, ceba, bacon, ou},
                new Double[]{1., 1., 30., 1.,}), "Cou l'hamburguesa a la brasa, caramelitza la ceba, fregeix el bacon i l'ou" +
                "i gaudeix d'aquest deliciós i poc saludable plat", 15, (R.drawable.hamburger_meal));
        Plat ouFerrat = new Plat("Ou ferrat", setIngredients(new Aliment[]{ou}, new Double[]{1.}), "Trenca l'ou i fregeix-lo" +
                "a la paella", 3, (R.drawable.ou_ferrat));
        Plat bacallaPlat = new Plat("Bacallà", setIngredients(new Aliment[]{bacallaAliment, farina}, new Double[]
                {1., 20.}), "Enfarina el bacallà i posa'l a la paella", 10,
                (R.drawable.bacalla));
        Plat truitaFrancesa = new Plat("Truita a la francesa", setIngredients(new Aliment[]{ou}, new Double[]{1.}),
                "Remena l'ou i tira'l a la paella", 5, (R.drawable.truita));
        Plat conillForn = new Plat("Conill al forn", setIngredients(new Aliment[]{conill, alls, ceba}, new Double[]
                {100., 4., 1.}), "Posa el conill en una safata dins al forn amb la ceba tallada i els alls", 30,
                (R.drawable.conill));
        segonsDinar.addAll(Arrays.asList(pollastreArrebossat, americanHamburguer, ouFerrat, bacallaPlat,
                truitaFrancesa, conillForn));

        // POSTRES //
        Plat pomaPlat = new Plat("Poma", setIngredients(new Aliment[]{pomaAliment}, new Double[]{1.}), "", 0,
                (R.drawable.poma));
        Plat platanPlat = new Plat("Plàtan", setIngredients(new Aliment[]{platanAliment}, new Double[]{1.}), "", 0,
                (R.drawable.platan));
        Plat taronjaPlat = new Plat("Taronja", setIngredients(new Aliment[]{taronjaAliment}, new Double[]{1.}), "", 0,
                (R.drawable.taronja));
        Plat peraPlat = new Plat("Pera", setIngredients(new Aliment[]{peraAliment}, new Double[]{1.}), "", 0,
                (R.drawable.pera));
        Plat iogurtPlat = new Plat("Iogurt", setIngredients(new Aliment[]{iogurtAliment}, new Double[]{1.}), "", 0,
                (R.drawable.iogurt));
        postresDinar.addAll(Arrays.asList(pomaPlat, platanPlat, taronjaPlat, peraPlat, iogurtPlat));


        // ESMORZAR //
        Plat lletPlat = new Plat("Llet", setIngredients(new Aliment[]{lletAliment}, new Double[]{0.3}), "", 0,
                (R.drawable.llet));
        primersEsmorzar.addAll(Arrays.asList(lletPlat));
        Plat galetesPlat = new Plat("Galetes", setIngredients(new Aliment[]{galeta},
                new Double[]{5.}), "", 0, (R.drawable.milk_cookies));
        segonsEsmorzar.addAll(Arrays.asList(galetesPlat));
        Plat sucPressecPlat = new Plat("Suc de préssec", setIngredients(new Aliment[]{sucPressecAliment},
                new Double[]{0.3}), "", 0, (R.drawable.suc_pressec));
        postresEsmorzar.addAll(Arrays.asList(sucPressecPlat));
    }

    private static TreeMap<Aliment, Double> setIngredients (Aliment[] conjAlim, Double[] conjQuant) {
        TreeMap<Aliment, Double> ingredients = new TreeMap<>();
        assert(conjAlim.length == conjQuant.length);
        for (int i = 0; i < conjAlim.length; ++i) {
            ingredients.put(conjAlim[i], conjQuant[i]);
        }
        return ingredients;
    }
}
