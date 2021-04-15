package proje1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class myOwnKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int puan = Main.oyuncu.getPuan();

        if (puan != 0) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    //System.out.println("Yukarı");
                    Main.yukariHareket();
                    
                    break;
                case KeyEvent.VK_RIGHT:
                    //System.out.println("saga");
                    Main.sagaHareket();
                    break;
                case KeyEvent.VK_LEFT:
                    //System.out.println("sola");
                    Main.solaHareket();
                    break;
                case KeyEvent.VK_DOWN:
                    //System.out.println("aşağı");
                    Main.asagiHareket();
                    break;

            }

        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
