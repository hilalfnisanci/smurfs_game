package proje1;

public class Obje {
    
    private String objeAd;
    private int puan;
    private boolean aktifMi;
    private Lokasyon lokasyon;
    private int gozukmeSuresi;

    public Obje() {
    }
    
    public Obje(String objeAd, int puan, boolean aktifMi, Lokasyon lokasyon, int gozukmeSuresi) {
        this.objeAd = objeAd;
        this.puan = puan;
        this.aktifMi = aktifMi;
        this.lokasyon = lokasyon;
        this.gozukmeSuresi = gozukmeSuresi;
    }

    public String getObjeAd() {
        return objeAd;
    }

    public void setObjeAd(String objeAd) {
        this.objeAd = objeAd;
    }

    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }

    public boolean isAktifMi() {
        return aktifMi;
    }

    public void setAktifMi(boolean aktifMi) {
        this.aktifMi = aktifMi;
    }

    public Lokasyon getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(Lokasyon lokasyon) {
        this.lokasyon = lokasyon;
    }

    public int getGozukmeSuresi() {
        return gozukmeSuresi;
    }

    public void setGozukmeSuresi(int gozukmeSuresi) {
        this.gozukmeSuresi = gozukmeSuresi;
    }
    
    
}
