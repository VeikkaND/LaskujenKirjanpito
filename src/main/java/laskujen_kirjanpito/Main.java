package laskujen_kirjanpito;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {

    Lasku lasku1 = new Lasku("Lasku", "Tämä on jokin lasku", 50);
    Lasku lasku2 = new Lasku("Lasku2", "Tämäkin on lasku", 40);
    Lasku lasku3 = new Lasku("Lasku3", "Tämäkin on lasku ehkä", 30);
    Lasku lasku4 = new Lasku("Lasku4", "Tämäkin on kai lasku", 44);

    /**
     * Lista kaikista tämänhetkisistä laskuista
     */
    private ArrayList<Lasku> laskut = new ArrayList<Lasku>();

    /**
     * lasku -ruudukon leveys
     */
    private int ruudukkoLeveys = 3;

    /**
     * tiedostopolku tallennettaville laskuille
     */
    private String polku = "laskut.dat";
    private ObjectInputStream luettavaTiedosto = null;
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        GridPane laskutPaneeli = new GridPane();
        HBox valintaPaneeli = new HBox();
        /*
        laskut.add(lasku1);
        laskut.add(lasku2);
        laskut.add(lasku3);
        laskut.add(lasku4);

         */

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
        if(laskut.size() > 0) {
            int sarake = 0;
            int rivi = 0;
            for(int i = 0; i < laskut.size(); i++) {
                Lasku lasku = laskut.get(i);
                laskutPaneeli.add(LaskuVisualisointi.luoPaneeli(lasku.getTunnus(), lasku.getTiedot(), lasku.getMaara()),sarake, rivi);
                if(sarake >= (ruudukkoLeveys-1)) { // -> vaihdetaan riviä
                    rivi++;
                    sarake = 0;
                }
                else {
                    sarake++;
                }
            }
        }

        // valintaPaneelin näppäimet
        Button lisaaButton = new Button("Lisää");
        Button tallennaButton = new Button("Tallenna");
        tallennaButton.setOnAction(e -> {
            // tallennetaan laskut tiedostoon
            try {
                ObjectOutputStream tallennettavaTiedosto = new ObjectOutputStream(new FileOutputStream(polku));
                for(Lasku lasku : laskut) {
                    tallennettavaTiedosto.writeObject(lasku);
                }

                tallennettavaTiedosto.close();
            } catch (FileNotFoundException fileNotFound) {
                System.out.println("Tiedostoa ei löytynyt");
            } catch (IOException ioex) {
                System.out.println(ioex);
            }

        });
        valintaPaneeli.getChildren().addAll(lisaaButton, tallennaButton);

        root.setCenter(laskutPaneeli);
        root.setTop(valintaPaneeli);
        Scene scene = new Scene(root, 1000,800);
        stage.setTitle("Laskujen kirjanpito");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
