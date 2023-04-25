package br.sowbreira.jgorillaz;

import br.sowbreira.geometry.GeoUtil;

import java.awt.Point;

import java.util.List;

import javax.swing.JOptionPane;


/**
 * @author Paulo
 * Created on 24/12/2004
 */
public class GameManager {
    protected static final String P1 = "P1";
    protected static final String P2 = "P2";
    private GorillazPanel gorillazPanel;
    private RasterManager rasterManager;
    private GorillazFrame gorillazFrame;
    private Point p1Cannon;
    private Point p2Cannon;
    private boolean fimJogo;

    /**
     * @param gorillazFrame
     */
    public GameManager(GorillazFrame gorillazFrame) {
        super();
        this.gorillazFrame = gorillazFrame;
    }

    private void aviso(String string) {
        JOptionPane.showMessageDialog(
            gorillazPanel, string, "Aviso", JOptionPane.INFORMATION_MESSAGE
        );
    }

    public boolean isFimJogo() {
        return fimJogo;
    }

    public void setFimJogo(boolean fimJogo) {
        this.fimJogo = fimJogo;
    }

    public Point getP1Cannon() {
        return p1Cannon;
    }

    public void setP1Cannon(Point cannon) {
        p1Cannon = new Point(cannon.x + 32, cannon.y);
    }

    public Point getP2Cannon() {
        return p2Cannon;
    }

    public void setP2Cannon(Point cannon) {
        p2Cannon = cannon;
    }

    public GorillazPanel getGorillazPanel() {
        return gorillazPanel;
    }

    public void setGorillazPanel(GorillazPanel gorillazPanel) {
        this.gorillazPanel = gorillazPanel;
        rasterManager = new RasterManager();
        rasterManager.setImage(gorillazPanel.getImage());
    }

    public RasterManager getRasterManager() {
        return rasterManager;
    }

    public void setRasterManager(RasterManager rasterManager) {
        this.rasterManager = rasterManager;
    }

    /**
     * @param i
     * @param player
     * @return
     */
    public boolean continueDrawing(int i, Point p, String player) {
        if (RasterManager.OUT == i) {
            gorillazPanel.repaint();
            return false;
        } else if (RasterManager.TERRAIN == i) {
            gorillazPanel.doExplosion(p);
            return false;
        } else if ((RasterManager.P1 == i) && !GameManager.P1.equals(player)) {
            fimJogo = true;
            gorillazPanel.doExplosion(p);
            aviso("Jogador 2 Ganhou");
            gorillazFrame.fimJogo();

            return false;
        } else if ((RasterManager.P2 == i) && !GameManager.P2.equals(player)) {
            fimJogo = true;
            gorillazPanel.doExplosion(p);
            aviso("Jogador 1 Ganhou");
            gorillazFrame.fimJogo();

            return false;
        }

        return true;
    }

    /**
     * @param p12
     */
    public void jogar(String p) {
        GorillazControlPanel controlPanel = new GorillazControlPanel(p);
        JOptionPane.showMessageDialog(gorillazPanel, controlPanel);

        int velocidade = controlPanel.getHoriz()
                                     .getValue();
        int ang = controlPanel.getVert()
                              .getValue();

        if (P1.equals(p)) {
            List list =
                GeoUtil.drawParabola(
                    velocidade, ang, getP1Cannon(), GeoUtil.LEFT_TO_RIGHT, null
                );
            gorillazPanel.drawAttack(list, getRasterManager(), GameManager.P1);
        }

        if (P2.equals(p)) {
            List list =
                GeoUtil.drawParabola(
                    velocidade, ang, getP2Cannon(), GeoUtil.RIGHT_TO_LEFT, null
                );
            gorillazPanel.drawAttack(list, getRasterManager(), GameManager.P2);
        }
    }
}
