package laskujen_kirjanpito.valikot;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import laskujen_kirjanpito.Lasku;
import laskujen_kirjanpito.LaskuVisualisointi;

import java.util.ArrayList;

public class LisaaValikko {
    /**
     * Metodi, joka luo valikon uuden laskun lisäämiselle
     * @param popup Popup popup ikkuna
     * @param laskut Arraylist<Lasku> lista laskuista
     * @param laskutPaneeli GridPane laskujen paneeli
     * @param ruudukkoLeveys int ruudukon leveys
     * @param stage Stage pääikkuna
     */
    public static void naytaLisaaValikko(Popup popup, ArrayList<Lasku> laskut, GridPane laskutPaneeli, int ruudukkoLeveys, Stage stage) {
        if(!popup.isShowing()) {
            // TODO lisää valikkoon kunnon sulkemisnappi
            // TODO tarkasta syötteet
            StackPane taustaPaneeli = new StackPane();
            BorderPane lisaaPaneeli = new BorderPane();
            Rectangle tausta = new Rectangle(popup.getWidth(), popup.getHeight(), Color.LIGHTBLUE);
            tausta.setStroke(Color.BLACK);
            VBox laatikkoVBox = new VBox(10);
            TextField tunnusLaatikko = new TextField("Nimi");
            tunnusLaatikko.setMaxWidth(popup.getWidth()-50);
            TextArea tiedotLaatikko = new TextArea("Tiedot");
            tiedotLaatikko.setMaxWidth(popup.getWidth()-50);
            TextField maaraLaatikko = new TextField("Määrä (€)");
            maaraLaatikko.setMaxWidth(popup.getWidth()-50);
            Button lisaaLaskuButton = new Button("LISÄÄ");
            lisaaLaskuButton.setAlignment(Pos.CENTER);
            lisaaLaskuButton.setOnAction(lisaa -> {
                System.out.println("Lisätään uusi lasku");
                Lasku uusiLasku = new Lasku(tunnusLaatikko.getText(), tiedotLaatikko.getText(), Double.parseDouble(maaraLaatikko.getText()));
                laskut.add(uusiLasku);
                LaskuVisualisointi.luoKuvakkeet(laskut, laskutPaneeli, ruudukkoLeveys);
                popup.hide();
            });
            laatikkoVBox.getChildren().addAll(tunnusLaatikko, tiedotLaatikko, maaraLaatikko, lisaaLaskuButton);
            laatikkoVBox.setAlignment(Pos.CENTER);
            lisaaPaneeli.setCenter(laatikkoVBox);
            taustaPaneeli.getChildren().addAll(tausta, lisaaPaneeli);
            popup.getContent().add(taustaPaneeli);
            popup.show(stage);
        }
        else {
            popup.hide();
        }
    }
}
