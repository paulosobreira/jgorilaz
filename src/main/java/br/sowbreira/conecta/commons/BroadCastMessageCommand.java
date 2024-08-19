package br.sowbreira.conecta.commons;

import br.sowbreira.conecta.server.ServerThread;

/**
 * @author sobreira
 * Created on 18/06/2004
 * executado no servidor.
 */
public class BroadCastMessageCommand implements Command{

	public String menssagemGeral = new String();
	
	public BroadCastMessageCommand(String cmd){
		this.menssagemGeral = cmd; 
	}
	
	/**
	 * @see br.sowbreira.conecta.commons.Command#executarComando()
	 */
	public void executarComando(ConnectionManagerThread linhaJogador) {
		ServerThread.enviaMsgGeral(getMenssagemGeral());
	}
	/**
	 * @return Returns the menssagemGeral.
	 */
	public final String getMenssagemGeral() {
		return menssagemGeral;
	}
	/**
	 * @param menssagemGeral The menssagemGeral to set.
	 */
	public final void setMenssagemGeral(String menssagemGeral) {
		this.menssagemGeral = menssagemGeral;
	}
}
