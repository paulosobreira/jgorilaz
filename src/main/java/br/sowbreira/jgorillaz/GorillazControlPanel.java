package br.sowbreira.jgorillaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * @author Paulo
 * Created on 25/12/2004
 */
public class GorillazControlPanel extends JPanel {
    private JSlider horiz;
    private JSlider vert;
    private JLabel potencia;
    private JLabel angulo;
    private Seta seta;
    private String player;

    public GorillazControlPanel(String p) {
        player = p;
        setLayout(new BorderLayout());
        horiz = new JSlider(SwingConstants.HORIZONTAL, 0, 200, 10);
        vert = new JSlider(SwingConstants.VERTICAL, 0, 90, 10);
        horiz.setPaintTicks(true);
        vert.setPaintTicks(true);
        add(horiz, BorderLayout.SOUTH);
        add(vert, BorderLayout.WEST);
        seta = new Seta();
        horiz.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    potencia.setText(String.valueOf(horiz.getValue()));
                    seta.repaint();
                }
            });
        vert.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    angulo.setText(String.valueOf(vert.getValue()));
                    seta.repaint();
                }
            });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Potencia"));
        potencia = new JLabel();
        panel.add(potencia);
        panel.add(new JLabel("Angulo"));
        angulo = new JLabel();
        panel.add(angulo);
        add(panel, BorderLayout.NORTH);
        add(seta, BorderLayout.CENTER);
    }

    public JSlider getHoriz() {
        return horiz;
    }

    public JSlider getVert() {
        return vert;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new GorillazControlPanel("P2"));
        frame.setSize(500,500);
        frame.show();
    }
    

    private class Seta extends JPanel {
        /* (non-Javadoc)
         * @see java.awt.Container#paintComponents(java.awt.Graphics)
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            			RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
            			RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_DITHERING,
            			RenderingHints.VALUE_DITHER_ENABLE);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION ,
            			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            if (GameManager.P1.equals(player)) {
                g.setColor(Color.RED);
                drawArrow(g,0, this.getHeight(), horiz.getValue(),
                        (getHeight() - vert.getValue()),7.0,8.0);
            } else if (GameManager.P2.equals(player)) {
                g.setColor(Color.BLUE);
                drawArrow(g,this.getWidth()-vert.getWidth(), this.getHeight(),
                    (this.getWidth() - (vert.getWidth()+horiz.getValue())),
                    (getHeight() - vert.getValue()),7.0,8.0);
            }
        }
    	/**
    	 * Title:        drawArrow
    	 * Description:  Draws an arrow
    	 * Company:      Transparent Tools (http://transparenttools.com)
    	 * @author       Jan Koeman (algorithm designer)
    	 */

    	// xy1 arrow base
    	// xy2 arrow point
    	// K   blade height
    	// N   blade length (hypotenuse)
    	// L   blade width (distance from arrow line)
    	void drawArrow(java.awt.Graphics g, int x1, int y1, int x2, int y2, double K, double N){

    		if( K >= N ) return; // draw nothing if height is greater than length
    		double L = Math.sqrt(N * N - K * K);
    		int x = x2 - x1;
    		int y = y2 - y1;
    		double A = Math.sqrt(x * x + y * y);

    		int x3 = (int)(( (A - K) / A) * x - (L / A) * y);
    		int y3 = (int)(( (A - K) / A) * y + (L / A) * x);
    		int x5 = (int)(( (A - K) / A) * x + (L / A) * y);
    		int y5 = (int)(( (A - K) / A) * y - (L / A) * x);

    		int x4 = x1 + x3;
    		int y4 = y1 + y3;
    		int x6 = x1 + x5;
    		int y6 = y1 + y5;

    		g.drawLine(x1,y1,x2,y2); // line
    		g.drawLine(x2,y2,x4,y4); // blade left
    		g.drawLine(x2,y2,x6,y6); // blade right

    	}
        /* (non-Javadoc)
         * @see javax.swing.JComponent#getPreferredSize()
         */
        public Dimension getPreferredSize() {
            return new Dimension(200, 90);
        }

        /* (non-Javadoc)
         * @see javax.swing.JComponent#getMinimumSize()
         */
        public Dimension getMinimumSize() {
            return getPreferredSize();
        }
    }
}
