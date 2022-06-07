import java.awt.*; //Classes para criação de interface e imagens
import java.awt.event.*; //Classes para manuseio de eventos AWT
import java.util.*;
import javax.swing.*; //Classes para criação de gráficos leves

public class Placar extends Rectangle{
    
    static int TELA_LARGURA;
    static int TELA_ALTURA;
    int player1;
    int player2;
    
    Placar(int TELA_LARGURA, int TELA_ALTURA) {
        Placar.TELA_LARGURA = TELA_LARGURA;
        Placar.TELA_ALTURA = TELA_ALTURA;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        
        //Divisa da mesa
        g.drawLine(TELA_LARGURA/2, 0, TELA_LARGURA/2, TELA_ALTURA);
        
        g.drawString(String.valueOf(player1), (TELA_LARGURA/2)-60, 50);
        g.drawString(String.valueOf(player2), (TELA_LARGURA/2)+25, 50);
    }
    
}
