import java.awt.*; //Classes para criação de interface e imagens
import java.awt.event.*; //Classes para manuseio de eventos AWT
import java.util.*;
import javax.swing.*; //Classes para criação de gráficos leves

public class GamePanel extends JPanel implements Runnable{
    
    static final int TELA_LARGURA = 1000;
    static final int TELA_ALTURA = (int)(TELA_LARGURA * (0.5555)); //Ajusta auto.
    static final Dimension TELA_TAMANHO = new Dimension(TELA_LARGURA, 
                                                        TELA_ALTURA);
    static final int BOLA_TAMANHO = 20;
    static final int RAQUETE_LARGURA = 25;
    static final int RAQUETE_ALTURA = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Raquete raquete1;
    Raquete raquete2;
    Placar placar;
    
    GamePanel() {
        newRaquete();
        newBola();
        placar = new Placar(TELA_LARGURA, TELA_ALTURA);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(TELA_TAMANHO);
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void newBola() {
        
    }
    public void newRaquete() {
        raquete1 = new Raquete(0,(TELA_ALTURA/2)-(RAQUETE_ALTURA/2), RAQUETE_LARGURA, RAQUETE_ALTURA,1);
        raquete2 = new Raquete(TELA_LARGURA-RAQUETE_LARGURA,(TELA_ALTURA/2)-(RAQUETE_ALTURA/2), RAQUETE_LARGURA, RAQUETE_ALTURA,2);        
    }
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g) {
        raquete1.draw(g);
        raquete2.draw(g);
        
    }
    public void mover() {
        
    }
    public void checarColisao() {
        
    }
    public void run() {     //Para rodar o game a 60 fps; Trecho de código do Minecraft
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta = delta + (now - lastTime)/ns;
            lastTime = now;
            if (delta >= 1) {
                mover();
                checarColisao();
                repaint();
                delta--;
                //System.out.println("Test");
            }
        }
    }
    public class AL extends KeyAdapter{     //Recebe os comandos da raquete
        public void keyPressed(KeyEvent e){
            raquete1.keyPressed(e);
            raquete2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            raquete1.keyPressed(e);
            raquete2.keyReleased(e);
        }
    }
}
