package br.sowbreira.conecta.server;

import java.net.InetAddress;

import br.sowbreira.conecta.commons.MessageCommand;
import br.sowbreira.conecta.commons.SystemTextOutputObserver;


/**
 * @author  Paulo Sobreira
 * Created on 9 de Outubro de 2003, 20:39
 * 
 * com as threads do cliente. 
 */
public class ServerThread extends Thread {
    
    private SystemTextOutputObserver saidaTxt;
    private java.net.ServerSocket srv;
    private static java.util.Hashtable mapaDeJogadores;
    private boolean jogoIniciado;
    private boolean fimdoJogo;
    private boolean ativo;
    
    /** Creates a new instance of LinhaServidor */
    public ServerThread(int porta,SystemTextOutputObserver buffer) {
        super("LinhaServidor");
    	saidaTxt= buffer;
        ativo=true;
        mapaDeJogadores=new java.util.Hashtable();
          try {
             srv = new java.net.ServerSocket( porta );
             saidaTxt.print("Ok eu sou "+InetAddress.getLocalHost() +
             				 " ouvindo na porta "+srv.getLocalPort()+"\n");
          }
          catch( Exception e ) {
              e.printStackTrace();
          }
    }

    public void run(){ 
       try {
           sleep(20);
           while (ativo && !jogoIniciado && !interrupted()){
               ClientInServerThread jogador=new ClientInServerThread(
               		srv.accept());
               jogador.setId(String.valueOf(mapaDeJogadores.size()));
               mapaDeJogadores.put(jogador.getId(),jogador);
               jogador.start();
           }
           System.out.println("morreu servidor");
       }
        catch ( Exception e ) { e.printStackTrace(); }
 
   }
    
    /** Getter for property ativo.
     * @return Value of property ativo.
     *
     */
    public boolean isAtivo() {
        return ativo;
    }
    
    /** Setter for property ativo.
     * @param ativo New value of property ativo.
     *
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    /** Getter for property fimdoJogo.
     * @return Value of property fimdoJogo.
     *
     */
    public boolean isFimdoJogo() {
        return fimdoJogo;
    }
    
    /** Setter for property fimdoJogo.
     * @param fimdoJogo New value of property fimdoJogo.
     *
     */
    public void setFimdoJogo(boolean fimdoJogo) {
        this.fimdoJogo = fimdoJogo;
    }
    
    /** Getter for property jogoIniciado.
     * @return Value of property jogoIniciado.
     *
     */
    public boolean isJogoIniciado() {
        return jogoIniciado;
    }
    
    /** Setter for property jogoIniciado.
     * @param jogoIniciado New value of property jogoIniciado.
     *
     */
    public void setJogoIniciado(boolean jogoIniciado) {
        this.jogoIniciado = jogoIniciado;
    }
    public synchronized static void mostraPtsGeral() {
        java.util.Enumeration e = mapaDeJogadores.elements();
        while (e.hasMoreElements()){
            ClientInServerThread jog=(ClientInServerThread)e.nextElement();
            //enviaMsgGeral(jog.getNome()+" "+jog.getPontos()+"pts");
        }
    }
    public synchronized static void enviaMsgGeral(java.lang.String msg) {
        java.util.Enumeration e = mapaDeJogadores.elements();
        while (e.hasMoreElements()){
            ClientInServerThread jog=(ClientInServerThread)e.nextElement();
            jog.escreverObjeto(new MessageCommand(msg));
        }
    }

    public synchronized static void enviaListaJogadoresGeral() {

    }    
        
    public synchronized static void enviaCmdGeral(java.lang.String msg) {
        java.util.Enumeration e = mapaDeJogadores.elements();
        try {
            while (e.hasMoreElements()){
                ClientInServerThread jog=(ClientInServerThread)e.nextElement();
                
            }
        } catch ( Exception ex ) { ex.printStackTrace(); }              
    }
    
    /** Getter for property srv.
     * @return Value of property srv.
     *
     */
    public java.net.ServerSocket getSrv() {
        return srv;
    }
    
    /** Setter for property srv.
     * @param srv New value of property srv.
     *
     */
    public void setSrv(java.net.ServerSocket srv) {
        this.srv = srv;
    }
    
	/**
	 * @return Returns the listaJog.
	 */
	public static final java.util.Hashtable getListaJog() {
		return mapaDeJogadores;
	}

	/**
	 * @param listaJog The listaJog to set.
	 */
	public static final void setListaJog(java.util.Hashtable listaJog) {
		ServerThread.mapaDeJogadores = listaJog;
	}

}
