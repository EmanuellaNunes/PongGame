import java.awt.*; //Classes para criação de interface e imagens
import java.awt.event.*; //Classes para manuseio de eventos AWT
import java.util.*;
import javax.swing.*; //Classes para criação de gráficos leves

public class Raquete extends Rectangle {//implements Runnable {
    
    int id; 
    int velocidadeY;
    int velocidade = 10;
    Thread raqueteThread;
    
    Raquete(int x, int y, int RAQUETE_LARGURA, int RAQUETE_ALTURA, int id) {
        super(x, y, RAQUETE_LARGURA, RAQUETE_ALTURA);
        this.id = id;
        //raqueteThread = new Thread(this);
        //raqueteThread.start();
        
    }
    
    //Movimentar as raquetes
    public void keyPressed(KeyEvent e) {
        switch(id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setSentidoY(-velocidade);
                    mover();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setSentidoY(velocidade);
                    mover();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setSentidoY(-velocidade);
                    mover();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setSentidoY(velocidade);
                    mover();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setSentidoY(0);
                    mover();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setSentidoY(0);
                    mover();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setSentidoY(0);
                    mover();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setSentidoY(0);
                    mover();
                }
                break;
        }        
    }
    
    public void setSentidoY(int sentidoY) {
        velocidadeY = sentidoY;
    }
    
    public void mover() {
        y = y + velocidadeY;
    }
    
    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.blue);
        }
        else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);
    }
    
    /*public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 120.0;   //Regula o FPS do objeto
        double ns = 1000000000 / amountOfTicks; //Regula o FPS do objeto
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta = delta + (now - lastTime)/ns;
            lastTime = now;
            if (delta >= 1) {
                mover();
                delta--;
            }
        }    
    }*/
}
