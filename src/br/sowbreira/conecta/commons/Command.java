package br.sowbreira.conecta.commons;

import java.io.Serializable;

/**
 * @author sobreira
 * Created on 18/06/2004
 */
public interface Command extends Serializable {
	public void executarComando(ConnectionManagerThread linhaJogador);
}
