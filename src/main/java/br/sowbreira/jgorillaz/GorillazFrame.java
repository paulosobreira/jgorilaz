package br.sowbreira.jgorillaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import br.sowbreira.geometry.GeoUtil;


/**
 * @author Paulo
 */
public class GorillazFrame extends JFrame {
    public static final int DESENHANDO = 0;
    public static final int POSICIONANDO_JOGADORES = 1;
    public static final int INICIADO = 2;
    public static final int FIM_JOGO = 3;
    private GorillazPanel gorillazPanel;
    private Point lastClick;
    private int gameState;
    private int jogadoresDesenhados;
    private GameManager playerManager;
    
    public GorillazFrame() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("JGorillaz por {sowbreira@gmail.com 2004-2023}");
        construitPainel();
        constriurMenu();
        aviso("Clique para desenhar o terreno. Botão esquerdo finaliza.");
    }
    private void reset(){
        gameState = DESENHANDO;
        jogadoresDesenhados = 0;
        playerManager = new GameManager(this);
        gorillazPanel.reset(this.getSize(),playerManager);
        playerManager.setGorillazPanel(gorillazPanel);
        lastClick = new Point(0, 540);
        aviso("Clique para desenhar o terreno. Botão esquerdo finaliza.");
    }
    
    /**
     *
     */
    private void construitPainel() {
        playerManager = new GameManager(this);
        gorillazPanel = new GorillazPanel(this.getSize(), playerManager);
        playerManager.setGorillazPanel(gorillazPanel);
        lastClick = new Point(0, 540);
        getContentPane().add(gorillazPanel, BorderLayout.CENTER);
        gorillazPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    gorillazPanel.repaint();
                    gorillazPanel.click(e.getPoint());
                    if (gameState == DESENHANDO) {
                        if (e.getButton() != MouseEvent.BUTTON1) {
                            gameState = POSICIONANDO_JOGADORES;
                            aviso("Clique para desenhar P1");
                        } else {
                            desenharLinha(lastClick, e.getPoint());
                            lastClick = e.getPoint();
                        }
                    } else if (gameState == POSICIONANDO_JOGADORES) {
                        if (jogadoresDesenhados == 0) {
                            desenharPlayer(e.getPoint(), GorillazPanel.P1COLOR);
                            aviso("Clique para desenhar P2");
                            playerManager.setP1Cannon(e.getPoint());
                            jogadoresDesenhados++;
                        } else {
                            desenharPlayer(e.getPoint(), GorillazPanel.P2COLOR);
                            playerManager.setP2Cannon(e.getPoint());
                            jogadoresDesenhados++;
                        }

                        if (jogadoresDesenhados > 1) {
                            gameState = INICIADO;
                        }
                    }
                    else if (gameState == FIM_JOGO){
                        reset();
                    }
                }
            });
    }

    private void desenharPlayer(Point point, Color c) {
        Point lefUp = point;
        Point lefDn = new Point(point.x, point.y + 20);
        Point rigUp = new Point(point.x + 30, point.y);
        Point rigDn = new Point(point.x + 30, point.y + 20);
        List list = new ArrayList();

        if (c.equals(GorillazPanel.P1COLOR)) {
            list.addAll(GeoUtil.drawBresenhamLine(rigDn,
                    new Point(rigUp.x, rigUp.y + 5))); //|
        } else {
            list.addAll(GeoUtil.drawBresenhamLine(rigUp, rigDn)); //|
        }

        if (c.equals(GorillazPanel.P2COLOR)) {
            list.addAll(GeoUtil.drawBresenhamLine(lefDn,
                    new Point(lefUp.x, lefUp.y + 5))); //| |
        } else {
            list.addAll(GeoUtil.drawBresenhamLine(lefUp, lefDn)); //| |
        }

        list.addAll(GeoUtil.drawBresenhamLine(rigDn, lefDn)); //|_|
        list.addAll(GeoUtil.drawBresenhamLine(rigUp, lefUp));
        gorillazPanel.addPlayers(list, c);
    }

    private void desenharLinha(Point lastClick, Point point) {
        List list = GeoUtil.drawBresenhamLine(lastClick, point);
        gorillazPanel.addpoints(list);
    }

    /**
     *
     */
    private void constriurMenu() {
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);

        JMenu menu = new JMenu("Jogo");
        JMenuItem jogarP1 = new JMenuItem("Jogar P1");
        jogarP1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if (gameState!=INICIADO){
                        aviso("Desenhe o terreno e os jogadores antes!");
                        return;
                    }
                    else if (gameState == FIM_JOGO){
                            reset();
                            return;
                        }
                    playerManager.jogar(GameManager.P1);
                }
            });
        JMenuItem jogarP2 = new JMenuItem("Jogar P2");
        jogarP2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if (gameState!=INICIADO){
                        aviso("Desenhe o terreno e os jogadores antes!");
                        return;
                    }
                    else if (gameState == FIM_JOGO){
                        reset();
                        return;
                    }                    
                    playerManager.jogar(GameManager.P2);
                }
            });
        menu.add(jogarP1);
        menu.add(jogarP2);
        bar.add(menu);
    }

    private void aviso(String string) {
        JOptionPane.showMessageDialog(this, string, "Aviso",
            JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * 
     */
    public void fimJogo() {
        gameState = FIM_JOGO;
        
    }
}
