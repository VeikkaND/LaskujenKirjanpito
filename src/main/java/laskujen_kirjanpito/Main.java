package laskujen_kirjanpito;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import laskujen_kirjanpito.valikot.LisaaValikko;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {

    /**
     * Lista kaikista tämänhetkisistä laskuista
     */
    private static ArrayList<Lasku> laskut = new ArrayList<Lasku>();

    /**
     * lasku -ruudukon leveys
     */
    private static int ruudukkoLeveys = 3;

    /**
     * tiedostopolku tallennettaville laskuille
     */
    private String polku = "laskut.dat";

    /**
     * merkkijono, joka näytetään laskujen summana
     */
    private static String laskujenSumma;

    /**
     * tekstilaatikko näytettävälle summalle
     */
    private static Label summa = new Label();
    private ObjectInputStream luettavaTiedosto = null;
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        ScrollPane paaPaneeli = new ScrollPane(root);
        GridPane laskutPaneeli = new GridPane();
        HBox valintaPaneeli = new HBox();

        Popup popup = new Popup();
        popup.setWidth(350);
        popup.setHeight(350);

        // luetaan laskut tiedostosta
        try {
            luettavaTiedosto = new ObjectInputStream(new FileInputStream(polku));
            if(luettavaTiedosto != null) {
                Object objekti = null;
                try {
                    while((objekti = luettavaTiedosto.readObject()) != null) {
                        Lasku lasku = (Lasku) objekti;
                        laskut.add(lasku);
                    }
                } catch (EOFException eofe) {}
            }
            luettavaTiedosto.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei löytynyt");
        }

        // luodaan kuvakkeet kaikille laskuille
        LaskuVisualisointi.luoKuvakkeet(laskut, laskutPaneeli, ruudukkoLeveys);

        // valintaPaneelin näppäimet
        Button lisaaButton = new Button("Lisää");
        Button tallennaButton = new Button("Tallenna");
        // toiminnallisuus uuden laskun luonnille
        lisaaButton.setOnAction(e -> {
            LisaaValikko.naytaLisaaValikko(popup, laskut, laskutPaneeli, ruudukkoLeveys, stage);
        });
        tallennaButton.setOnAction(e -> {
            // tallennetaan laskut tiedostoon
            try {
                ObjectOutputStream tallennettavaTiedosto = new ObjectOutputStream(new FileOutputStream(polku));
                for(Lasku lasku : laskut) {
                    tallennettavaTiedosto.writeObject(lasku);
                }

                tallennettavaTiedosto.close();
            } catch (FileNotFoundException fileNotFound) {
                System.out.println("Tiedostoa ei löytynyt, luodaan uusi tiedosto");
            } catch (IOException ioex) {
                System.out.println(ioex);
            }

        });
        valintaPaneeli.getChildren().addAll(lisaaButton, tallennaButton);

        // Summapaneeli
        VBox summaPaneeli = new VBox(30);
        Label summaTeksti = new Label("Summa:");
        summaPaneeli.getChildren().addAll(summaTeksti, summa);
        summaPaneeli.setAlignment(Pos.CENTER);
        summaPaneeli.setPadding(new Insets(10,10,10,10));

        root.setCenter(laskutPaneeli);
        root.setTop(valintaPaneeli);
        root.setLeft(summaPaneeli);

        Scene scene = new Scene(paaPaneeli, 1000,800);
        stage.setTitle("Laskujen kirjanpito");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Poistaa halutun Laskun laskut -listasta
     * @param lasku Lasku poistettava lasku
     */
    public static void poistaLasku(Lasku lasku) {
        laskut.remove(lasku);
    }

    /**
     * Palauttaa listan laskuista
     * @return Arraylist<Lasku> laskut
     */
    public static ArrayList<Lasku> getLaskut() {
        return laskut;
    }

    /**
     * Palauttaa ruudukon leveyden
     * @return int ruudukon leveys
     */
    public static int getRuudukkoLeveys() {
        return ruudukkoLeveys;
    }

    /**
     * Laskujen summan päivittäminen vastaamaan nykyisten laskujen summaa
     */
    public static void paivitaSumma() {
        double uusiSumma = 0;
        for(Lasku lasku : laskut) {
            uusiSumma += lasku.getMaara();
        }
        laskujenSumma = Double.toString(uusiSumma) + "€";
        summa.setText(laskujenSumma);
    }
}
