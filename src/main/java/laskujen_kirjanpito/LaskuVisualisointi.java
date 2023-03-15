package laskujen_kirjanpito;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class LaskuVisualisointi {
    /**
     * Metodi, jolla voidaan visualisoida lasku luodun paneelin avulla
     * @param tunnus String laskun tunnus
     * @param tiedot String laskun tiedot
     * @param maara double laskun määrä
     * @return BorderPane paneeli
     */
    public static BorderPane luoPaneeli(String tunnus, String tiedot, double maara) {
        // luodaan tarvittavat komponentit
        BorderPane paneeli = new BorderPane();
        Label tunnusTeksti = new Label(tunnus);
        TextArea tiedotTeksti = new TextArea(tiedot);
        tiedotTeksti.setEditable(false);
        Label maaraTeksti = new Label(Double.toString(maara));

        // määritellään asettelu paneelin komponenteille
        paneeli.setTop(tunnusTeksti);
        BorderPane.setAlignment(tunnusTeksti, Pos.CENTER);
        paneeli.setCenter(tiedotTeksti);
        paneeli.setBottom(maaraTeksti);
        BorderPane.setAlignment(maaraTeksti, Pos.CENTER);

        return paneeli;
    }

    /**
     * Luo kuvakkeet halutuun GridPaneen
     * @param laskut Arraylist<Lasku> lista laskuista
     * @param laskutPaneeli GridPane pääohjelman laskupaneeli
     * @param ruudukkoLeveys int ruudukon haluttu leveys
     */
    public static void luoKuvakkeet(ArrayList<Lasku> laskut, GridPane laskutPaneeli, int ruudukkoLeveys) {
        laskutPaneeli.getChildren().removeAll();
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
    }
}
