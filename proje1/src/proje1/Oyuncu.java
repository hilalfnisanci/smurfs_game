package proje1;

import java.util.ArrayList;

public class Oyuncu extends Karakter{

    private int oyuncuID;
    private String oyuncuAd;
    private int oyuncuAdim;
    private int puan;

    public Oyuncu() {
        super();
    }

    public Oyuncu(int oyuncuID, String oyuncuAd, int oyuncuAdim, int puan, Lokasyon lokasyon, String tur) {
        super(oyuncuID, oyuncuAd, lokasyon, tur);
        this.oyuncuID = oyuncuID;
        this.oyuncuAd = oyuncuAd;
        this.oyuncuAdim = oyuncuAdim;
        this.puan = puan;
    }

    public int getOyuncuID() {
        return oyuncuID;
    }
    
    public void setOyuncuID(int oyuncuID) {
        this.oyuncuID = oyuncuID;
    }

    public String getOyuncuAd() {
        return oyuncuAd;
    }

    public void setOyuncuAd(String oyuncuAd) {
        this.oyuncuAd = oyuncuAd;
    }

    public int getOyuncuAdim() {
        return oyuncuAdim;
    }

    public void setOyuncuAdim(int oyuncuAdim) {
        this.oyuncuAdim = oyuncuAdim;
    }

    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }

    public int puanGoster(){
        return puan;
    }
    
    @Override
    public ArrayList<Lokasyon> shorthPath(int[][] map, Lokasyon lokasyon) {
        return null;
    }
    
}
