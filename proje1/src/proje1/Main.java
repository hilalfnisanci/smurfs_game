package proje1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;

public class Main {

    static int mapMeter1, mapMeter2;
    public static int[][] Map;
    public static Oyuncu oyuncu;
    static Scanner scan;
    static JFrame jf;
    static Grafik grafik;
    public static boolean kazandiMi;
    public static ArrayList<Dusman> kotuList = new ArrayList<>();
    public static ArrayList<ArrayList<Lokasyon>> pathList = new ArrayList<>();
    public static ArrayList<String> kapiList = new ArrayList<>();
    public static Mantar mantar;
    public static ArrayList<Mantar> mantarList = new ArrayList<>();
    public static ArrayList<Altin> altinlar = new ArrayList<>();
    public static Lokasyon finish = new Lokasyon(12, 7);

    public static void main(String[] args) {
        mapMeter1 = 0;
        mapMeter2 = 0;
        Map = new int[11][13];
        scan = new Scanner(System.in);
        jf = new JFrame("Şirinler Oyunu");
        grafik = new Grafik();
        
        
        iyiKarakterOlustur();
        fileReader();
        Thread altin = new Thread(() -> {
            altinOlustur();
        });
        Thread mantar = new Thread(() -> {
            mantarOlustur();
        });
        altin.start();
        mantar.start();
        grafik();

    }

    static void fileReader() {
        try {
            File dosya = new File("harita.txt");

            try ( BufferedReader oku = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(dosya), "UTF8"))) {
                String line;

                while ((line = oku.readLine()) != null) {
                    String[] str = line.split(":");
                    if (str[0].equals("Karakter")) {
                        kotuKarakterOlustur(line);
                    } else {
                        createMap(line);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void createMap(String line) {
        mapMeter2 = 0;

        for (int i = 0; i < line.length(); i++) {

            if (line.charAt(i) == '0') {
                Map[mapMeter1][mapMeter2] = 0;
                mapMeter2++;
            } else if (line.charAt(i) == '1') {
                Map[mapMeter1][mapMeter2] = 1;
                mapMeter2++;
            }
        }
        mapMeter1++;
    }

    static void grafik() {
        myOwnKeyListener klavye = new myOwnKeyListener();
        jf.setBackground(Color.DARK_GRAY);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setSize(1000, 700);
        jf.setVisible(true);
        jf.addKeyListener(klavye);
        jf.add(grafik);
    }

    static void iyiKarakterOlustur() {
        //int oyuncuID, String oyuncuAd, int oyuncuAdim, int puan, Lokasyon lokasyon, String tur
        System.out.println("Tembel Şirin için 1'e , Gözlüklü Şirin için 2'ye basınız ");
        int choice = scan.nextInt();
        if (choice == 1) {
            oyuncu = new TembelSirin(1, "Tembel Şirin", 1, 20, new Lokasyon(6, 5), "good");
        } else {
            oyuncu = new GozlukluSirin(2, "Gözlüklü Şirin", 2, 20, new Lokasyon(6, 5), "good");
        }

    }

    static void kotuKarakterOlustur(String line) {
        String[] dizi1 = line.split(",");
        Dusman kotu;

        String[] dizi2 = dizi1[0].split(":");
        String[] dizi3 = dizi1[1].split(":");

        switch (dizi2[1]) {

            case "Gargamel":
                kapiList.add(dizi3[1]);
                kotu = new Gargamel(1, "Gargamel", 2, 15, kapiOlustur(dizi3[1]), "bad");
                kotu.setIlkLokasyon(kapiOlustur(dizi3[1]));
                kotuList.add(kotu);
                break;
            case "Azman":
                kapiList.add(dizi3[1]);
                kotu = new Azman(2, "Azman", 1, 5, kapiOlustur(dizi3[1]), "bad");
                kotu.setIlkLokasyon(kapiOlustur(dizi3[1]));
                kotuList.add(kotu);
                break;
        }
    }

    static Lokasyon kapiOlustur(String kapiAdi) {

        switch (kapiAdi) {
            case "A":
                return new Lokasyon(3, 0);
            case "B":
                return new Lokasyon(10, 0);
            case "C":
                return new Lokasyon(0, 5);
            case "D":
                return new Lokasyon(3, 10);
            default:
                return new Lokasyon(0, 0);
        }

    }

    public static void sagaHareket() {
        int x = oyuncu.getLokasyon().getX();
        int y = oyuncu.getLokasyon().getY();
        int adim = oyuncu.getOyuncuAdim();

        if ((x + adim) <= 12) {
            if (Map[y][x + adim] == 1) {
                //sag tarafta yol var mı? 

                oyuncu.getLokasyon().setX(x + adim);
                objePuan();
                if (isGameOver()) {
                    resetLokasyon();
                } else {
                    pathList.clear();
                    enKisaYol();
                    moveDusman();
                    if (isGameOver()) {
                        resetLokasyon();
                    }
                }
                jf.getContentPane().repaint();
            } else {
                System.out.println("Sağa gidemezsiniz");
            }
        } else if ((x + adim) == 13 && y == 8) {//şirineye ulaştı
            kazandiMi = true;
            jf.getContentPane().repaint();
        } else {
            System.out.println("Sağa gidemezsiniz");
        }
    }

    public static void solaHareket() {
        int x = oyuncu.getLokasyon().getX();
        int y = oyuncu.getLokasyon().getY();
        int adim = oyuncu.getOyuncuAdim();

        if ((x - adim) >= 0) {
            if (Map[y][x - adim] == 1) {

                oyuncu.getLokasyon().setX(x - adim);
                objePuan();
                if (isGameOver()) {
                    resetLokasyon();
                } else {
                    pathList.clear();
                    enKisaYol();
                    moveDusman();
                    if (isGameOver()) {
                        resetLokasyon();
                    }
                }
                jf.getContentPane().repaint();
            } else {
                System.out.println("Sola gidemezsiniz");
            }
        } else {
            System.out.println("Sola gidemezsiniz");
        }
    }

    public static void asagiHareket() {
        int x = oyuncu.getLokasyon().getX();
        int y = oyuncu.getLokasyon().getY();
        int adim = oyuncu.getOyuncuAdim();

        if ((y + adim) <= 10) {
            if (Map[y + 1][x] == 1) {

                oyuncu.getLokasyon().setY(y + adim);
                objePuan();
                if (isGameOver()) {
                    resetLokasyon();
                } else {
                    pathList.clear();
                    enKisaYol();
                    moveDusman();
                    if (isGameOver()) {
                        resetLokasyon();
                    }
                }
                jf.getContentPane().repaint();

            } else {
                System.out.println("Aşağı gidemezsiniz");
            }
        } else {
            System.out.println("Aşağı gidemezsiniz");
        }
    }

    public static void yukariHareket() {
        int x = oyuncu.getLokasyon().getX();
        int y = oyuncu.getLokasyon().getY();
        int adim = oyuncu.getOyuncuAdim();

        if ((y - adim) >= 0) {
            if (Map[y - 1][x] == 1) {

                oyuncu.getLokasyon().setY(y - adim);
                objePuan();
                if (isGameOver()) {
                    resetLokasyon();
                } else {
                    pathList.clear();
                    enKisaYol();
                    moveDusman();
                    if (isGameOver()) {
                        resetLokasyon();
                    }
                }
                jf.getContentPane().repaint();
            } else {
                System.out.println("Yukarı gidemezsiniz");
            }
        } else {
            System.out.println("Yukarı gidemezsiniz");
        }
    }

    static void enKisaYol() {

        for (int i = 0; i < kotuList.size(); i++) {

            switch (kotuList.get(i).getDusmanAd()) {
                case "Gargamel":
                    pathList.add(kotuList.get(i).shorthPath(Map, oyuncu.getLokasyon()));
                    break;
                case "Azman":
                    pathList.add(kotuList.get(i).shorthPath(Map, oyuncu.getLokasyon()));
                    break;
            }
        }

    }

    public static void moveDusman() {
        int n, adim;

        for (int i = 0; i < kotuList.size(); i++) {
            switch (kotuList.get(i).getDusmanAd()) {
                case "Azman":
                    n = pathList.get(i).size() - 1;
                    adim = kotuList.get(i).getAdim();
                    kotuList.get(i).getLokasyon().setX(pathList.get(i).get(n - adim).getX());
                    kotuList.get(i).getLokasyon().setY(pathList.get(i).get(n - adim).getY());
                    //System.out.println("Azman: " + kotuList.get(i).getLokasyon().getX() + "," + kotuList.get(i).getLokasyon().getY());
                    break;
                case "Gargamel":
                    n = pathList.get(i).size() - 1;
                    adim = kotuList.get(i).getAdim();
                    if (n - adim >= 0) {
                        kotuList.get(i).getLokasyon().setX(pathList.get(i).get(n - adim).getX());
                        kotuList.get(i).getLokasyon().setY(pathList.get(i).get(n - adim).getY());
                    } else {
                        kotuList.get(i).getLokasyon().setX(pathList.get(i).get(n - 1).getX());
                        kotuList.get(i).getLokasyon().setY(pathList.get(i).get(n - 1).getY());
                    }
                    //System.out.println("Gargamel: " + kotuList.get(i).getLokasyon().getX() + "," + kotuList.get(i).getLokasyon().getY());
                    break;
            }
        }
        //System.out.println("Oyuncu: " + oyuncu.getLokasyon().getX() + "," + oyuncu.getLokasyon().getY());
    }

    public static void altinOlustur() {
        Random rand = new Random();
        int time = rand.nextInt(10) + 1;
        int randX = 0, randY = 0, n = 0;
        Lokasyon lokasyon = null;
        try {
            Thread.sleep(time*1000);
            while (true) {
                if (n == 5) {
                    break;
                }
                randX = rand.nextInt(12);
                randY = rand.nextInt(10);
                if (Map[randY][randX] == 1) {
                    lokasyon = new Lokasyon(randX, randY);
                    Altin altin = new Altin("altın", 5, true, lokasyon, 5);
                    altinlar.add(altin);
                    //System.out.println("Altin "+n+": "+randX+","+randY);
                    n++;
                } else {
                }
            }
            Thread.sleep(5000);
            if (!altinlar.isEmpty()) {
                altinlar.clear();
            }
        } catch (InterruptedException ex) {
        }
    }

    public static void mantarOlustur() {
        Random rand = new Random();
        int time = rand.nextInt(20) + 1;
        int randX = 0, randY = 0;
        Lokasyon lokasyon = null;
        try {
            Thread.sleep(time * 1000);
            while (true) {
                randX = rand.nextInt(12);
                randY = rand.nextInt(10);
                //System.out.println("Mantar: " + randX + "," + randY);
                if (Map[randY][randX] == 1) {
                    lokasyon = new Lokasyon(randX, randY);
                    mantar = new Mantar("mantar", 50, true, lokasyon, 7);
                    mantarList.add(mantar);
                    break;
                }
            }
            Thread.sleep(mantar.getGozukmeSuresi() * 1000);
            if (!mantarList.isEmpty()) {
                mantarList.clear();
            }
        } catch (InterruptedException ex) {
        }
    }

    public static void resetLokasyon() {

        oyuncu.getLokasyon().setX(oyuncu.getLokasyon().getX());
        oyuncu.getLokasyon().setY(oyuncu.getLokasyon().getY());

        for (int i = 0; i < kotuList.size(); i++) {
            if (kotuList.get(i).getLokasyon().getX() == oyuncu.getLokasyon().getX() && kotuList.get(i).getLokasyon().getY() == oyuncu.getLokasyon().getY()) {
                
                if (oyuncu.getLokasyon() != finish) {
                    //System.out.println("kontrol");
                    oyuncu.setPuan(oyuncu.getPuan() - kotuList.get(i).getPuan());
                }
            }

        }
        for (int j = 0; j < kotuList.size(); j++) {

            Lokasyon lokasyon;

            switch (kapiList.get(j)) {
                case "A":
                    lokasyon = new Lokasyon(3, 0);
                    kotuList.get(j).setLokasyon(lokasyon);
                    break;
                case "B":
                    lokasyon = new Lokasyon(10, 0);
                    kotuList.get(j).setLokasyon(lokasyon);
                    break;
                case "C":
                    lokasyon = new Lokasyon(0, 5);
                    kotuList.get(j).setLokasyon(lokasyon);
                    break;
                case "D":
                    lokasyon = new Lokasyon(3, 10);
                    kotuList.get(j).setLokasyon(lokasyon);
                    break;
            }
        }
        pathList = new ArrayList<>();
        enKisaYol();

    }
    
    public static void objePuan(){
        if(!mantarList.isEmpty()){
            
            if(oyuncu.getLokasyon().getX() == mantar.getLokasyon().getX() && oyuncu.getLokasyon().getY() == mantar.getLokasyon().getY()){
                //System.out.println("kontrol 1");
                oyuncu.setPuan(oyuncu.getPuan()+mantar.getPuan());
                mantarList.clear();
            }
        }
        
        if(!altinlar.isEmpty()){
            for(int i=0; i<altinlar.size();i++){
                if(oyuncu.getLokasyon().getX()== altinlar.get(i).getLokasyon().getX() && oyuncu.getLokasyon().getY() == altinlar.get(i).getLokasyon().getY()){
                    //System.out.println("kontrol 2");
                    oyuncu.setPuan(oyuncu.getPuan()+altinlar.get(i).getPuan());
                    altinlar.get(i).setAktifMi(false);
                    break;
                }
            }
        }
    }
    
    public static boolean isGameOver() {

        for (int i = 0; i < kotuList.size(); i++) {
            if (kotuList.get(i).getLokasyon().getX() == oyuncu.getLokasyon().getX() && kotuList.get(i).getLokasyon().getY() == oyuncu.getLokasyon().getY()) {
                return true;
            }
        }

        return false;
    }

}
