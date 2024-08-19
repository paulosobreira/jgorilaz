package br.sowbreira.jgorillaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;


/**
 * @author Paulo
 * Created on 24/12/2004
 */
public class GorillazPanel extends JPanel {
    private Set graund ;
    private Set p1 ;
    private Set p2 ;
    private BufferedImage image;
    private GameManager playerManager;
    public static final Color P1COLOR = Color.RED;
    public static final Color P2COLOR = Color.BLUE;
    public final static Color GORUND = new Color(0, 180, 0);
    public final static Color BALL = Color.ORANGE;// =  new Color(0, 180, 0);

    public BufferedImage getImage() {
        return image;
    }
    public void reset(Dimension size,GameManager playerManager) {
        image = new BufferedImage(size.width, size.height,
                BufferedImage.TYPE_INT_ARGB);
        this.playerManager = playerManager;
        graund = new HashSet();;
        p1 = new HashSet();
        p2 = new HashSet();
        paint(getGraphics());
    }
    /**
    *
    */
    public GorillazPanel(Dimension size,GameManager playerManager) {
        image = new BufferedImage(size.width, size.height,
                BufferedImage.TYPE_INT_ARGB);
        this.playerManager = playerManager;
        graund = new HashSet();;
        p1 = new HashSet();
        p2 = new HashSet();
    }

    public void paint(Graphics g) {
        super.paint(g);
        qualyGraphics(g);
        g.drawImage(image,0,0,null);
    }
    
    public void qualyGraphics(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        			RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
        			RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING,
        			RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION ,
        			RenderingHints.VALUE_INTERPOLATION_BILINEAR);        
    }
    
    public void addpoints(List list) {
        graund.addAll(list);
        doLoop(list,GORUND,5);
    }
    public void addPlayers(List list,Color color) {
        Set players = new HashSet();
        if (P1COLOR.equals(color))
            players = p1;
        if (P2COLOR.equals(color))
            players = p2;
        players.addAll(list);
        doLoop(players,color,20);
    }
    public void click(Point p){
        Graphics graphics = this.getGraphics();
        qualyGraphics(graphics);
        for (int i = 0; i < 15; i++) {
            sleep(10);
            graphics.setColor(Color.YELLOW);
            graphics.drawOval(p.x-15,p.y-15,30,30);
            sleep(10);
            graphics.setColor(Color.WHITE);
            graphics.drawOval(p.x-15,p.y-15,30,30);
        }
        graphics.dispose();
        repaint();
    }
    public void drawAttack(List list,RasterManager manager, String player){
        paint(getGraphics());
        Graphics graphics2 = this.getGraphics();
        manager.setImage(image);
        Color rastro = new Color(255, 0, 0, 50);
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Point p = (Point) iter.next();
            int rand = (int)((Math.random()*10)+1);
            graphics2.setColor(BALL);
            graphics2.drawOval(p.x,p.y,2,2);
            graphics2.setColor(rastro);
            graphics2.drawOval(p.x,p.y,1,1);
            if (rand < 4){
                graphics2.setColor(Color.WHITE);
                graphics2.drawOval(p.x,p.y,rand,rand);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!playerManager.continueDrawing(manager.moveAt(p),p,player)){
                return;
            }
            	
            
        }            
    }
    private void doLoop(Collection collection,Color c,int i){
        Graphics graphics = image.getGraphics();
        Graphics graphics2 = this.getGraphics();
        qualyGraphics(graphics2);
        qualyGraphics(graphics);
        for (Iterator iter = collection.iterator(); iter.hasNext();) {
            Point p = (Point) iter.next();
            graphics.setColor(c);
            graphics2.setColor(c);
            graphics.drawOval(p.x,p.y,2,2);
            graphics2.drawOval(p.x,p.y,2,2);
            sleep(i);
        }
        
    }
    /**
     * 
     */
    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param p
     */
    public void doExplosion(Point p) {
        Graphics graphics = image.getGraphics();
        qualyGraphics(graphics);
        graphics.setColor(getBackground());
        graphics.fillOval(p.x-25,p.y-25,50,50);
        doExplosionAnim(p);
        graphics.dispose();
        repaint();
    }
    /**
     * @param p
     */
    private void doExplosionAnim(Point p) {
        Graphics graphics = getGraphics();
        qualyGraphics(graphics);
        for (int i = 0; i < 20; i++) {
            sleep(15);
            graphics.setColor(Color.WHITE);
            graphics.fillOval(p.x-25,p.y-25,50,50);
            sleep(15);
            graphics.setColor(Color.YELLOW);
            graphics.fillOval(p.x-25,p.y-25,50,50);
            sleep(15);
            graphics.setColor(Color.RED);
            graphics.fillOval(p.x-25,p.y-25,50,50);
            sleep(15);
            graphics.setColor(BALL);
            graphics.fillOval(p.x-25,p.y-25,50,50);
        }
        if (!playerManager.isFimJogo()){
            doLoop(p1,P1COLOR,1);
            doLoop(p2,P2COLOR,1);
        }
    }
}
