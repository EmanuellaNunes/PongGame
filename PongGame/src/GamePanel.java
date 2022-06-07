import java.awt.*; //Classes para criação de interface e imagens
import java.awt.event.*; //Classes para manuseio de eventos AWT
import java.util.*;
import javax.swing.*; //Classes para criação de gráficos leves

public class GamePanel extends JPanel implements Runnable{
    
    //Configurações de tamanhos
    static final int TELA_LARGURA = 1000;
    static final int TELA_ALTURA = (int)(TELA_LARGURA * (0.5555)); //Ajusta auto.
    static final Dimension TELA_TAMANHO = new Dimension(TELA_LARGURA, 
                                                        TELA_ALTURA);
    static final int BOLA_TAMANHO = 20;
    static final int RAQUETE_LARGURA = 25;
    static final int RAQUETE_ALTURA = 100;
    
    //Declaração de objetos
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Raquete raquete1;
    Raquete raquete2;
    Bola bola;
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
        //random = new Random();
        bola = new Bola((TELA_LARGURA/2)-(BOLA_TAMANHO/2), (TELA_ALTURA/2)-(BOLA_TAMANHO/2), BOLA_TAMANHO, BOLA_TAMANHO);
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
        bola.draw(g);
        
    }
    
    public void mover() {
        raquete1.mover();   //Permite uma fluidez maior das raquetes
        raquete2.mover();   //Permite uma fluidez maior das raquetes
        bola.mover();
    }
    
    
    public void checarColisao() {
    //Impede que os elementos ultrapassem limites de outros objetos ou janela
        //Bola
        if (bola.y <= 0) {
            bola.setSentidoY(-bola.velocidadeY);
        }
        if (bola.y >= TELA_ALTURA - BOLA_TAMANHO) {
            bola.setSentidoY(-bola.velocidadeY);
        }
        
        //Raquete 1
        if (raquete1.y <= 0) {
            raquete1.y = 0;
        }
        if (raquete1.y >= (TELA_ALTURA - RAQUETE_ALTURA)) {
            raquete1.y = TELA_ALTURA - RAQUETE_ALTURA;
        }
        //Raquete 2
        if (raquete2.y <= 0) {
            raquete2.y = 0;
        }
        if (raquete2.y >= (TELA_ALTURA - RAQUETE_ALTURA)) {
            raquete2.y = TELA_ALTURA - RAQUETE_ALTURA;
        }
        
        //Bola rebate na raquete 1
        if (bola.intersects(raquete1)) {
            bola.velocidadeX = bola.velocidadeX * (-1);
            bola.velocidadeX++;
            if (bola.velocidadeY > 0) {
                bola.velocidadeY++;
            }
            else {
                bola.velocidadeY--;
            }
            bola.setSentidoX(bola.velocidadeX);
            bola.setSentidoY(bola.velocidadeY);
        }

        //Bola rebate na raquete 2
        if (bola.intersects(raquete2)) {
            bola.velocidadeX = bola.velocidadeX * (-1);
            bola.velocidadeX++;
            if (bola.velocidadeY > 0) {
                bola.velocidadeY++;
            }
            else {
                bola.velocidadeY--;
            }
            bola.setSentidoX(-bola.velocidadeX);
            bola.setSentidoY(bola.velocidadeY);
        }          
        
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
            raquete1.keyReleased(e);
            raquete2.keyReleased(e);
        }
    }
}
