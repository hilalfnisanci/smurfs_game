package proje1;

public class Puan extends Oyuncu{
    
    private final Oyuncu iyiKarakter;
    private final Dusman kotuKarakter;

    public Puan(Oyuncu iyiKarakter, Dusman kotuKarakter) {
        this.iyiKarakter = iyiKarakter;
        this.kotuKarakter = kotuKarakter;
    }

    @Override
    public int puanGoster() {
        
        if("Gargamel".equals(kotuKarakter.getAd())){
            iyiKarakter.setPuan(iyiKarakter.getPuan()- 15);
        }else if("Azman".equals(kotuKarakter.getAd()))
            iyiKarakter.setPuan(iyiKarakter.getPuan()- 5);
        
        return iyiKarakter.getPuan(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
