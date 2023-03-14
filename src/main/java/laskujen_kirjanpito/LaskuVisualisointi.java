package laskujen_kirjanpito;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

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
}
