package laskujen_kirjanpito;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class LaskuVisualisointi {
    /**
     * Metodi, jolla voidaan visualisoida lasku luodun paneelin avulla
     * @param tunnus String laskun tunnus
     * @param tiedot String laskun tiedot
     * @param maara double laskun määrä
     * @param laskutPaneeli GridPane laskupaneeli
     * @return StackPane paneeli
     */
    public static StackPane luoPaneeli(String tunnus, String tiedot, double maara, GridPane laskutPaneeli) {
        // TODO poistonappi
        int leveys = 300;
        int korkeus = 250;
        // luodaan tarvittavat komponentit
        StackPane paneeli = new StackPane();
        Rectangle tausta = new Rectangle(leveys, korkeus, Color.LIGHTSKYBLUE);
        tausta.setStroke(Color.BLACK);
        VBox tiedotVBox = new VBox(10);
        Label tunnusTeksti = new Label(tunnus);
        tunnusTeksti.setFont(Font.font(25));
        TextArea tiedotTeksti = new TextArea(tiedot);
        tiedotTeksti.setEditable(false);
        tiedotTeksti.setMaxWidth(leveys-40);
        Label maaraTeksti = new Label(Double.toString(maara) + " €");
        maaraTeksti.setFont(Font.font(20));
        tiedotVBox.getChildren().addAll(tunnusTeksti, tiedotTeksti, maaraTeksti);
        tiedotVBox.setAlignment(Pos.CENTER);
        paneeli.getChildren().addAll(tausta, tiedotVBox);

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
                laskutPaneeli.add(LaskuVisualisointi.luoPaneeli(lasku.getTunnus(), lasku.getTiedot(), lasku.getMaara(), laskutPaneeli),sarake, rivi);
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
