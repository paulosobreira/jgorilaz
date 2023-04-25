package br.sowbreira.conecta.commons;

import br.sowbreira.conecta.client.ClientThread;

/**
 * @author sobreira
 * Created on 18/06/2004
 * Executado no cliente
 */
public class MessageCommand implements Command{
	
	public String mensagem = new String();
	
	public MessageCommand(String msg){
		this.mensagem = msg;
	}
	/**
	 * @see br.sowbreira.conecta.commons.Command#executarComando(br.nnpe.mplayercore.LinhaJogador)
	 */
	public void executarComando(ConnectionManagerThread linhaJogador) {
		((ClientThread)linhaJogador).addText(getMensagem()); 
		
	}
	/**
	 * @return Returns the mensagem.
	 */
	public final String getMensagem() {
		return mensagem;
	}
	/**
	 * @param mensagem The mensagem to set.
	 */
	public final void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
