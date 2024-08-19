package br.sowbreira.conecta.client;

import java.awt.Point;
import java.util.Map;

import br.sowbreira.conecta.commons.Command;
import br.sowbreira.conecta.commons.ConnectionManagerThread;
import br.sowbreira.conecta.commons.SystemTextOutputObserver;

/**
 * @author  Paulo Sobreira
 * Created on 9 de Outubro de 2003, 21:25
 * Esta classe representa um thread de Cliente
 */
public class ClientThread extends ConnectionManagerThread{
    private Map jogadores;
    private String ip;
    private Point ptOrigem,ptAtual;
    
    public ClientThread(java.net.Socket con,SystemTextOutputObserver buffer) {
        super(con);
        OutPutText=buffer;
    }
    
    public void enviarComando(Command c){
    	this.escreverObjeto(c);
    }
    
    public void setNome(java.lang.String nome) {    
        super.setNome(nome);
    }
    
    public void deconectar(){
    }
   
    public void gerarPainel() {
    }
    

    public void jogadaCliente(){
    }

    /** Getter for property ip.
     * @return Value of property ip.
     *
     */
    public java.lang.String getIp() {
        return ip;
    }
    
    /** Setter for property ip.
     * @param ip New value of property ip.
     *
     */
    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }
    
	/**
	 * @return Returns the ptOrigem.
	 */
	public final Point getPtOrigem() {
		return ptOrigem;
	}


	/**
	 * @return Returns the ptAtual.
	 */
	public final Point getPtAtual() {
		return ptAtual;
	}

	/**
	 * @param ptAtual The ptAtual to set.
	 */
	public final void setPtAtual(Point ptAtual) {
		this.ptAtual = ptAtual;
	}

}
