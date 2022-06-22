import java.awt.*; //Classes para criação de interface e imagens
import java.awt.event.*; //Classes para manuseio de eventos AWT
import java.util.*;
import javax.swing.*; //Classes para criação de gráficos leves

public class Bola extends Rectangle implements Runnable {
    
    Random random;
    int velocidadeX;
    int velocidadeY;
    int velocidadeInicial = 1;
    Thread bolaThread;

    
    Bola(int x, int y, int largura, int altura) {
        super (x, y, largura, altura);
        bolaThread = new Thread(this);
        bolaThread.start();
        
        random = new Random();
        
        int randomX = random.nextInt(2);
        if (randomX == 0) {
            randomX--;
        }
        setSentidoX(randomX*velocidadeInicial);
        
        int randomY = random.nextInt(2);
        if (randomY == 0) {
            randomY--;
        }
        setSentidoY(randomY*velocidadeInicial);
    }
    
    public void setSentidoX(int randomX) {
        velocidadeX = randomX;
    }
    public void setSentidoY(int randomY) {
        velocidadeY = randomY;
    }
    public void mover() {
        x = x + velocidadeX;
        y = y + velocidadeY;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }

    @Override
    public void run() {
        try {
            while(true) {
                mover();
                Thread.sleep(8);
            }
        } catch(Exception e) {System.err.println(e.getMessage());}
            
        }
    
}
