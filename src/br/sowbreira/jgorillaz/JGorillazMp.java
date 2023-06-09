package br.sowbreira.jgorillaz;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.sowbreira.conecta.client.ClientThread;
import br.sowbreira.conecta.commons.BroadCastMessageCommand;
import br.sowbreira.conecta.server.ServerThread;

/**
 * @author sobreira
 * Created on 18/06/2004
 * 
 */
public class JGorillazMp extends JFrame{
	    
	    /** Creates new form Memoria */
	    public JGorillazMp() {
	        initComponents();
	    }
	    public boolean isFocusable() { return true; }
	    /** This method is called from within the constructor to
	     * initialize the form.
	     * WARNING: Do NOT modify this code. The content of this method is
	     * always regenerated by the Form Editor.
	     */
	    private void initComponents() {//GEN-BEGIN:initComponents
	        jPanel1 = new javax.swing.JPanel();
	        gamePanel = new javax.swing.JPanel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTextArea1 = new javax.swing.JTextArea();
	        jPanel2 = new javax.swing.JPanel();
	        jTextField1 = new javax.swing.JTextField();
	        jButton1 = new javax.swing.JButton();
	        jMenuBar1 = new javax.swing.JMenuBar();
	        jMenu1 = new javax.swing.JMenu();
	        jMenuItem3 = new javax.swing.JMenuItem();
	        jMenu2 = new javax.swing.JMenu();
	        jMenuItem2 = new javax.swing.JMenuItem();
	        jMenuItem4 = new javax.swing.JMenuItem();
	        jMenu3 = new javax.swing.JMenu();
	        jMenuItem1 = new javax.swing.JMenuItem();
	        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
	        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
	        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
		    buttonGroup2 = new javax.swing.ButtonGroup();
			jSeparator1 = new javax.swing.JSeparator();    
			setTitle("Maze Game");
	        
	        addKeyListener(new KeyListener() {
	        	public void keyPressed(KeyEvent e) {
	        		int keyCode = e.getKeyCode();
	        		if (keyCode == KeyEvent.VK_0)
	        			System.out.println("teste");
	        	}
	        	public void keyReleased(KeyEvent e) {
	        	}
	        	public void keyTyped(KeyEvent e) {
	        	}
	        });
	        
	        
	        jPanel1.setLayout(new java.awt.BorderLayout());

	        gamePanel.setLayout(null);
	        
	        gamePanel.addMouseListener( new MouseAdapter () {
				public void mouseClicked(MouseEvent e) {
					JGorillazMp.this.requestFocus();
				}
			});
	        
	        jPanel1.add(gamePanel, java.awt.BorderLayout.CENTER);

	        jTextArea1.setColumns(15);
	        jTextArea1.setEditable(false);
	        jTextArea1.setRows(6);
	        jScrollPane1.setViewportView(jTextArea1);

	        jPanel1.add(jScrollPane1, java.awt.BorderLayout.SOUTH);

	        jTextField1.setColumns(20);
	        jPanel2.add(jTextField1);

	        jButton1.setText("Enviar Msg");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt);
	            }
	        });

	        jPanel2.add(jButton1);

	        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

	        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

	        jMenu1.setText("Menu");
	        jMenuItem3.setText("Conectar");
	        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jMenuItem3ActionPerformed(evt);
	            }
	        });

	        jMenu1.add(jMenuItem3);

	        jMenuBar1.add(jMenu1);

	        jMenu2.setText("Servidor");
	        jMenu2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jMenu2ActionPerformed(evt);
	            }
	        });

	        jMenuItem2.setText("Iniciar Servidor");
	        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jMenuItem2ActionPerformed(evt);
	            }
	        });

	        jMenu2.add(jMenuItem2);

	        jMenuItem4.setText("Iniciar Jogo");
	        jMenuItem4.setEnabled(false);
	        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jMenuItem4ActionPerformed(evt);
	            }
	        });

	        jMenu2.add(jMenuItem4);

	        jMenuBar1.add(jMenu2);

	        jMenu3.add(jMenuItem1);

	        jMenu2.add(jSeparator1);

	        jMenu3.setText("Algoritimo");
	        jRadioButtonMenuItem1.setText("DFS");
	        buttonGroup2.add(jRadioButtonMenuItem1);
	        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
	        	public void actionPerformed(java.awt.event.ActionEvent evt) {
	        		//LinhaServidor.setMazeALGORTHIM(MazeGen.MazeDFS_ALGORTHIM);
	        	}
	        });

	        jMenu3.add(jRadioButtonMenuItem1);

	        jRadioButtonMenuItem2.setText("MazeKruskal");
	        buttonGroup2.add(jRadioButtonMenuItem2);
	        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
	        	public void actionPerformed(java.awt.event.ActionEvent evt) {
	        		//LinhaServidor.setMazeALGORTHIM(MazeGen.MazeKruskal_ALGORTHIM);
	        	}
	        });

	        jMenu3.add(jRadioButtonMenuItem2);

	        jRadioButtonMenuItem3.setText("MazePrim");
	        buttonGroup2.add(jRadioButtonMenuItem3);
	        jRadioButtonMenuItem3.addActionListener(new java.awt.event.ActionListener() {
	        	public void actionPerformed(java.awt.event.ActionEvent evt) {
	        		//LinhaServidor.setMazeALGORTHIM(MazeGen.MazePrim_ALGORTHIM);
	        	}
	        });

	        jMenu3.add(jRadioButtonMenuItem3);
			
			jMenuBar1.add(jMenu3);
	        setJMenuBar(jMenuBar1);

	        pack();
	    }//GEN-END:initComponents

	    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
	        // Add your handling code here:
	        servidor.setJogoIniciado(true);
	    }//GEN-LAST:event_jMenuItem4ActionPerformed

	    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	        // Add your handling code here:
	        jog.escreverObjeto(new BroadCastMessageCommand(jTextField1.getText()));
	    }//GEN-LAST:event_jButton1ActionPerformed

	    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
	        // Add your handling code here:
	        try{
	        String ip=javax.swing.JOptionPane.showInputDialog(this,"Entre com IP do Servidor");
	        String porta=javax.swing.JOptionPane.showInputDialog(this,"Entre com o Número da Porta do Servidor");
	        jog= new ClientThread(
	        		new java.net.Socket(java.net.InetAddress.getByName(ip),
	        				Integer.parseInt(porta)),null);
	        jog.setNome(JOptionPane.showInputDialog(this,"Entre com Seu Nome"));
	        jog.start();
	        }
	        catch ( Exception e ) { System.out.println(e); }
	        
	    }//GEN-LAST:event_jMenuItem3ActionPerformed

	    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
	        // Add your handling code here:
	        jMenuItem4.setEnabled(true);
	        servidor = new ServerThread(Integer.parseInt(
	        		JOptionPane.showInputDialog(this,
	        				"Entre com o Número da Porta do Servidor")),null);
	        servidor.start();
	        jMenuItem2.setEnabled(false);
	    }//GEN-LAST:event_jMenuItem2ActionPerformed

	    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
	        // Add your handling code here:
	    }//GEN-LAST:event_jMenu2ActionPerformed


	    
	    public void iniciarJogo(){
	    	mover = new Thread(new Runnable() {
				public void run() {
					while (!fimJogo){
					  try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				  }
			   }
			});
	    	mover.start();
	    	this.requestFocus();
	    }
	    
	    /** Exit the Application */
	    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
	        System.exit(0);
	    }//GEN-LAST:event_exitForm
	    
	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String args[]) {
	    	JGorillazMp jg= new JGorillazMp();
	    	jg.show();
	    }
	    private boolean fimJogo;
	    private Thread mover;
	    private ServerThread servidor;
	    private ClientThread jog;
	    // Variables declaration - do not modify//GEN-BEGIN:variables
		private javax.swing.JSeparator jSeparator1;
	    private javax.swing.ButtonGroup buttonGroup2;
		private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
	    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
	    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
	    private javax.swing.JButton jButton1;
	    private javax.swing.JMenu jMenu1;
	    private javax.swing.JMenu jMenu2;
	    private javax.swing.JMenu jMenu3;
	    private javax.swing.JMenuBar jMenuBar1;
	    private javax.swing.JMenuItem jMenuItem1;
	    private javax.swing.JMenuItem jMenuItem2;
	    private javax.swing.JMenuItem jMenuItem3;
	    private javax.swing.JMenuItem jMenuItem4;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JPanel jPanel2;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTextArea jTextArea1;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JPanel gamePanel;
	    // End of variables declaration//GEN-END:variables
	    
		/**
		 * @return Returns the mover.
		 */
		public final Thread getMover() {
			return mover;
		}

		/**
		 * @return Returns the fimJogo.
		 */
		public boolean isFimJogo() {
			return fimJogo;
		}

		/**
		 * @param fimJogo The fimJogo to set.
		 */
		public void setFimJogo(boolean fimJogo) {
			this.fimJogo = fimJogo;
		}

}
