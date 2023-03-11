package laskujen_kirjanpito;

import java.io.Serializable;

/**
 * Lasku -luokka, joka tallennetaan tiedostoon.
 * Sisältää laskun tiedot
 * @author Veikka Nevala
 */
public class Lasku implements Serializable {

    /**
     * lyhyt tunnus laskulle
     */
    private String tunnus;

    /**
     * laajemmat laskun tiedot
     */
    private String tiedot;

    /**
     * laskun rahallinen määrä
     */
    private double maara;

    /**
     * Konstruktori Lasku -luokalle
     * @param tunnus String laskun tunnus
     * @param tiedot String laskun tiedot
     * @param maara double laskun määrä
     */
    public Lasku(String tunnus, String tiedot, double maara) {
        this.tunnus = tunnus;
        this.tiedot = tiedot;
        this.maara = maara;
    }

    /**
     * Palauttaa tunnuksen
     * @return String tunnus
     */
    public String getTunnus() {return tunnus;}

    /**
     * Asettaa tunnuksen
     * @param tunnus String tunnus
     */
    public void setTunnus(String tunnus) {this.tunnus = tunnus;}

    /**
     * Palauttaa laskun tiedot
     * @return String tiedot
     */
    public String getTiedot() {return tiedot;}

    /**
     * Asettaa tiedot
     * @param tiedot String tiedot
     */
    public void setTiedot(String tiedot) {this.tiedot = tiedot;}

    /**
     * Palauttaa laskun määrän
     * @return double määrä
     */
    public double getMaara() {return maara;}

    /**
     * Asettaa laskun määrän
     * @param maara double määrä
     */
    public void setMaara(double maara) {this.maara = maara;}
}
