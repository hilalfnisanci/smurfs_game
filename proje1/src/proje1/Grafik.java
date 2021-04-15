package proje1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;

public class Grafik extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.DARK_GRAY);

        JLayeredPane EverythingButPlayer = null;
        BufferedImage img = null;

        int puan = Main.oyuncu.getPuan();

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {

                //labirent çizimi
                if (Main.Map[i][j] == 1) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fill3DRect(50 + j * 40, 150 + i * 40, 40, 40, true);
                    g.setColor(Color.WHITE);
                    g.drawString("1", 80 + j * 40, 180 + i * 40);//yollar
                } else {
                    g.setColor(Color.BLUE);
                    g.fill3DRect(50 + j * 40, 150 + i * 40, 40, 40, true);
                    g.setColor(Color.WHITE);
                    g.drawString("0", 80 + j * 40, 180 + i * 40);//duvarlar
                }

                //girişler
                if (j == 3 && i == 0) {
                    //A girişi

                    try {
                        img = ImageIO.read(new File("girisAB.png"));
                        g.drawImage(img, 50 + j * 40, 110 + i * 40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {

                    }
                    g.setColor(Color.ORANGE);
                    g.fill3DRect(50 + j * 40, 150 + i * 40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("A", 80 + j * 40, 180 + i * 40);

                } else if (j == 10 && i == 0) {
                    //B girişi
                    try {
                        img = ImageIO.read(new File("girisAB.png"));
                        g.drawImage(img, 50 + j * 40, 110 + i * 40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {

                    }
                    g.setColor(Color.ORANGE);
                    g.fill3DRect(50 + j * 40, 150 + i * 40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("B", 80 + j * 40, 180 + i * 40);

                } else if (j == 0 && i == 5) {
                    //C girisi
                    try {
                        img = ImageIO.read(new File("girisC.png"));
                        g.drawImage(img, 10 + j * 40, 150 + i * 40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {

                    }
                    g.setColor(Color.ORANGE);
                    g.fill3DRect(50 + j * 40, 150 + i * 40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("C", 80 + j * 40, 180 + i * 40);

                } else if (j == 3 && i == 10) {
                    //D girisi
                    try {
                        img = ImageIO.read(new File("girisD.png"));
                        g.drawImage(img, 50 + j * 40, 190 + i * 40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {

                    }
                    g.setColor(Color.ORANGE);
                    g.fill3DRect(50 + j * 40, 150 + i * 40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("D", 80 + j * 40, 180 + i * 40);
                } else if (j == 12 && i == 7) {
                    //sirine
                    try {
                        img = ImageIO.read(new File("sirine.png"));
                        g.drawImage(img, 90 + j * 40, 150 + i * 40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {

                    }
                } else if (j == 6 && i == 5) {
                    g.setColor(Color.MAGENTA);
                    g.fill3DRect(50 + j * 40, 150 + i * 40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("1", 80 + j * 40, 180 + i * 40);
                }
            }
        }

        if (puan > 0) {
            int x = Main.oyuncu.getLokasyon().getX();
            int y = Main.oyuncu.getLokasyon().getY();

            switch (Main.oyuncu.getAd()) {
                case "Tembel Şirin":
                    try {
                    img = ImageIO.read(new File("tembelSirin.jpg"));
                    g.drawImage(img, 50 + x * 40, 150 + y * 40, 40, 40, EverythingButPlayer);
                    if (x == 12 && y == 7) {
                        try {
                            img = ImageIO.read(new File("youWin.png"));
                            g.drawImage(img, 660, 380, 325, 225, EverythingButPlayer);
                            Main.kazandiMi = true;
                        } catch (IOException e) {
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        showMessageDialog(null, "Kazandınız. Puan : " + Main.oyuncu.getPuan());
                        System.exit(0);
                    }
                } catch (IOException e) {
                }
                break;
                case "Gözlüklü Şirin":
                    try {
                    img = ImageIO.read(new File("gozlukluSirin.jpg"));
                    g.drawImage(img, 50 + x * 40, 150 + y * 40, 40, 40, EverythingButPlayer);
                    if (x == 12 && y == 7) {

                        try {
                            img = ImageIO.read(new File("youWin.png"));
                            g.drawImage(img, 660, 380, 325, 225, EverythingButPlayer);
                            Main.kazandiMi = true;
                        } catch (IOException e) {
                        }

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        showMessageDialog(null, "Kazandınız. Puan : " + Main.oyuncu.getPuan());
                        System.exit(0);

                    }
                } catch (IOException e) {
                }
                break;
            }

        } else if (puan <= 0) {

            try {
                img = ImageIO.read(new File("gameOver.png"));
                g.drawImage(img, 660, 380, 325, 225, EverythingButPlayer);

            } catch (IOException e) {
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            showMessageDialog(null, "Oyun Bitti. Kaybettiniz.");
            System.exit(0);

        }
        String str = "PUAN = " + Main.oyuncu.getPuan();
        g.setColor(Color.WHITE);
        g.drawString(str, 650, 230);

        //en kısa yol çizimi 
        if (puan > 0) {
            if (Main.kazandiMi != true) {
                for (int i = 0; i < Main.pathList.size(); i++) {

                    if ("Gargamel".equals(Main.kotuList.get(i).getAd())) {
                        for (int j = 1; j < Main.pathList.get(i).size() - 2; j++) {

                            int x = Main.pathList.get(i).get(j).getX();
                            int y = Main.pathList.get(i).get(j).getY();
                            //System.out.println("x: "+x+" y: "+y);
                            String kutuDegeri;

                            if (Main.Map[y][x] == 1) {
                                kutuDegeri = "1";
                            } else {
                                kutuDegeri = "0";
                            }

                            g.setColor(Color.DARK_GRAY);
                            g.fill3DRect(55 + x * 40, 155 + y * 40, 30, 30, true);
                            g.setColor(Color.BLACK);
                            g.drawString(kutuDegeri, 80 + x * 40, 180 + y * 40);

                        }
                    } else {
                        for (int j = 1; j < Main.pathList.get(i).size(); j++) {

                            int x = Main.pathList.get(i).get(j).getX();
                            int y = Main.pathList.get(i).get(j).getY();
                            //System.out.println("x: "+x+" y: "+y);
                            String kutuDegeri;

                            if (Main.Map[y][x] == 1) {
                                kutuDegeri = "1";
                            } else {
                                kutuDegeri = "0";
                            }

                            g.setColor(Color.DARK_GRAY);
                            g.fill3DRect(55 + x * 40, 155 + y * 40, 30, 30, true);
                            g.setColor(Color.BLACK);
                            g.drawString(kutuDegeri, 80 + x * 40, 180 + y * 40);

                        }
                    }

                }
            }
        }

        if (!Main.mantarList.isEmpty()) {
            int mantarX = Main.mantar.getLokasyon().getX();
            int mantarY = Main.mantar.getLokasyon().getY();
            Lokasyon lokasyon = new Lokasyon(mantarX, mantarY);
            if (Main.mantar.getLokasyon() != Main.oyuncu.getLokasyon()) {
                //System.out.println("kontrol");
                try {
                    img = ImageIO.read(new File("mantar.jpg"));
                    g.drawImage(img, 50 + mantarX * 40, 150 + mantarY * 40, 40, 40, EverythingButPlayer);
                } catch (IOException e) {
                }
            }
        }

        if (!Main.altinlar.isEmpty()) {
            int altinX;
            int altinY;
            for (int i = 0; i < Main.altinlar.size(); i++) {
                if (Main.altinlar.get(i).getLokasyon() != Main.oyuncu.getLokasyon()) {
                    if(Main.altinlar.get(i).isAktifMi()){
                        altinX = Main.altinlar.get(i).getLokasyon().getX();
                        altinY = Main.altinlar.get(i).getLokasyon().getY();
                        try {
                            img = ImageIO.read(new File("altin.jpg"));
                            g.drawImage(img, 50 + altinX * 40, 150 + altinY * 40, 40, 40, EverythingButPlayer);
                        } catch (IOException e) {
                        }
                    }
                }
            }
        }

        //kotu Karakterler çizim
        if (puan > 0) {
            for (int i = 0; i < Main.kotuList.size(); i++) {
                int x = Main.kotuList.get(i).getLokasyon().getX();
                int y = Main.kotuList.get(i).getLokasyon().getY();

                switch (Main.kotuList.get(i).getAd()) {
                    case "Gargamel":
                    try {
                        img = ImageIO.read(new File("gargamel.jpg"));
                        g.drawImage(img, 50 + x * 40, 150 + y * 40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {
                    }
                    break;
                    case "Azman":
                    try {
                        img = ImageIO.read(new File("azman.jpg"));
                        g.drawImage(img, 50 + x * 40, 150 + y * 40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {
                    }
                    break;
                }
            }
        }

        str = "PUAN = " + Main.oyuncu.puanGoster();
        g.setColor(Color.WHITE);
        g.drawString(str, 650, 230);
    }

}
