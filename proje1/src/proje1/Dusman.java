package proje1;

import java.util.ArrayList;
import java.util.Hashtable;

public class Dusman extends Karakter{

    private int dusmanID;
    private String dusmanAd;
    private int adim;
    private Lokasyon ilkLokasyon;
    private int puan;
    
    public Dusman() {
    }

    public Dusman(int dusmanID, String dusmanAd, int adim, int puan, Lokasyon lokasyon, String tur) {
        super(dusmanID, dusmanAd, lokasyon, tur);
        this.dusmanID = dusmanID;
        this.dusmanAd = dusmanAd;
        this.adim = adim;
        this.puan = puan;
        
    }
    
    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }
    
    public int getDusmanID() {
        return dusmanID;
    }

    public void setDusmanID(int dusmanID) {
        this.dusmanID = dusmanID;
    }

    public String getDusmanAd() {
        return dusmanAd;
    }

    public void setDusmanAd(String dusmanAd) {
        this.dusmanAd = dusmanAd;
    }

    public int getAdim() {
        return adim;
    }

    public void setAdim(int adim) {
        this.adim = adim;
    }

    public Lokasyon getIlkLokasyon() {
        return ilkLokasyon;
    }

    public void setIlkLokasyon(Lokasyon ilkLokasyon) {
        this.ilkLokasyon = ilkLokasyon;
    }
    
    @Override
    public ArrayList<Lokasyon> shorthPath(int[][] map, Lokasyon lokasyon) {
        
        ArrayList<Lokasyon> koordinatlar = new ArrayList<>();
        
        for(int i=0; i<11; i++){
            for(int j=0; j<13; j++){
                if(this.getLokasyon().getX() == j && this.getLokasyon().getY() == i){
                    this.getLokasyon().setDeger(0);
                    koordinatlar.add(this.getLokasyon());
                }else{
                    Lokasyon l = new Lokasyon(j,i);
                    l.setDeger(Integer.MAX_VALUE);
                    koordinatlar.add(l);
                }
            }
        }
        
        ArrayList<Lokasyon> list = new ArrayList<>();
        Hashtable<Lokasyon, Lokasyon> table = new Hashtable<>();
        
        Lokasyon ilkYer = this.getLokasyon();
        int ilkDeger = 0;
        
        for(int i=1; i<koordinatlar.size(); i++){
            ArrayList<Lokasyon> komsular = komsular(map,koordinatlar,ilkYer);
            for(int j=0; j<komsular.size(); j++){
                if(!list.contains(komsular.get(j))){
                    int yeniDeger = ilkDeger +1;
                    if(yeniDeger<komsular.get(j).getDeger()){
                        komsular.get(j).setDeger(yeniDeger);
                        table.put(komsular.get(j), ilkYer);
                    }
                }
            }
            Lokasyon yeni = null;
            int yeniDeger = Integer.MAX_VALUE;
            for(int j=0; j<koordinatlar.size(); j++){
                if(!list.contains(koordinatlar.get(j))){
                    int deger = koordinatlar.get(j).getDeger();
                    if(deger<yeniDeger){
                        yeni = koordinatlar.get(j);
                        yeniDeger = deger;
                    }
                }
            }
            
            if(yeni == null)
                break;
            
            ilkYer = yeni;
            ilkDeger = yeniDeger;
            list.add(ilkYer);
        }
        
        ArrayList<Lokasyon> path = new ArrayList<>();
        Lokasyon l = null;
        
        for(int i=0; i<koordinatlar.size(); i++){
            if(koordinatlar.get(i).getX() == lokasyon.getX() && koordinatlar.get(i).getY() == lokasyon.getY()){
                l = koordinatlar.get(i);
                break;
            }
        }
        
        while(l!=null){
            path.add(l);
            l = table.get(l);
        }
        
        
        return path;
    }
    
    private ArrayList<Lokasyon> komsular(int[][] map, ArrayList<Lokasyon> lokasyonlar,Lokasyon temp){
        
        ArrayList<Lokasyon> edges = new ArrayList<>();
        
        if(temp.getX()-1 > 0){
            for(int i=0; i<lokasyonlar.size();i++){
                if(map[temp.getY()][temp.getX()-1] == 1){
                    if(lokasyonlar.get(i).getX() == temp.getX()-1 && lokasyonlar.get(i).getY() == temp.getY()){
                        edges.add(lokasyonlar.get(i));
                        break;
                    }
                }
            }
        }
        
        if(temp.getX()+1<13){
            for(int i=0; i<lokasyonlar.size();i++){
                if(map[temp.getY()][temp.getX()+1] == 1){
                    if(lokasyonlar.get(i).getX() == temp.getX()+1 && lokasyonlar.get(i).getY() == temp.getY()){
                        edges.add(lokasyonlar.get(i));
                        break;
                    }
                }
            }
        }
        
        if(temp.getY()-1>0){
            for(int i=0; i<lokasyonlar.size();i++){
                if(map[temp.getY()-1][temp.getX()] == 1){
                    if(lokasyonlar.get(i).getX() == temp.getX() && lokasyonlar.get(i).getY() == temp.getY()-1){
                        edges.add(lokasyonlar.get(i));
                        break;
                    }
                }
            }
        }
        
        if(temp.getY()+1<11){
            for(int i=0; i<lokasyonlar.size();i++){
                if(map[temp.getY()+1][temp.getX()] == 1){
                    if(lokasyonlar.get(i).getX() == temp.getX() && lokasyonlar.get(i).getY() == temp.getY()+1){
                        edges.add(lokasyonlar.get(i));
                        break;
                    }
                }
            }
        }
        
        
        return edges;
    }
}
