import java.awt.*; //Classes para criação de interface e imagens
import java.awt.event.*; //Classes para manuseio de eventos AWT
import java.util.*;
import javax.swing.*; //Classes para criação de gráficos leves

//Classe que conterá a janela do jogo
public class GameFrame extends JFrame {
    
    GamePanel panel;
    
    GameFrame() {
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("SO - Pong");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); //Ajusta o tamanho exato do jogo na janela
        this.setVisible(true);
        this.setLocationRelativeTo(null); //Inicializa a janela no meio da tela
    }
}
