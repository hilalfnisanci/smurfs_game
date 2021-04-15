package proje1;

import java.util.ArrayList;

public abstract class Karakter {
    
    private int ID;
    private String ad;
    private Lokasyon lokasyon;
    private String tur;
    
    public Karakter(){
        
    }

    public Karakter(int ID, String ad, Lokasyon lokasyon, String tur) {
        this.ID = ID;
        this.ad = ad;
        this.lokasyon = lokasyon;
        this.tur = tur;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Lokasyon getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(Lokasyon lokasyon) {
        this.lokasyon = lokasyon;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }
    
    public abstract ArrayList<Lokasyon> shorthPath(int[][] map, Lokasyon lokasyon);
}
