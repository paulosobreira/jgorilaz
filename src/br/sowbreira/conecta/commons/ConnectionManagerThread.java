package br.sowbreira.conecta.commons;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Paulo Sobreira Created on 9 de Outubro de 2003, 19:25 Essa classe
 *         representa os Threads tanto elementos no servidor como no cliente
 */
public abstract class ConnectionManagerThread extends Thread {

	private String nome;
	private String id;
	private boolean vez, ativo;
	private Socket con;
	private ObjectInputStream input;
	private ObjectOutputStream output;

	/** Creates a new instance of LinhaJogadorCliente */
	protected SystemTextOutputObserver OutPutText;

	/** Creates a new instance of LinhaJogador */
	public ConnectionManagerThread(Socket con) {
		try {
			this.setCon(con);
			DataInputStream _in;
			DataOutputStream _out;
			output = new ObjectOutputStream(con.getOutputStream());
			output.flush();
			input = new ObjectInputStream(con.getInputStream());
			OutPutText = new SystemTextOutputObserver() {
				public void print(String text) {
				};
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Escuta a entrada e converte os bytes em objeto
	 */
	public void run() {
		try {
			while (ativo && !interrupted() && getCon().isConnected()) {
				precessaCmd(lerObjeto());
			}
			getOutput().close();
			getInput().close();
			System.out.println("morreu");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converte o objeto em comando e executa-o.
	 * 
	 * @param object
	 */
	public void precessaCmd(Object object) {
		((Command) object).executarComando(this);
	}

	/**
	 * Escreve um objeto na saida
	 * 
	 * @param o
	 */
	public void escreverObjeto(Object o) {
		try {
			output.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ler um objeto da entrada
	 * 
	 * @return
	 */
	public Object lerObjeto() {
		try {
			return input.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Getter for property con.
	 * 
	 * @return Value of property con.
	 * 
	 */
	public java.net.Socket getCon() {
		return con;
	}

	/**
	 * Setter for property con.
	 * 
	 * @param con
	 *            New value of property con.
	 * 
	 */
	public void setCon(java.net.Socket con) {
		this.con = con;
	}

	/**
	 * Getter for property vez.
	 * 
	 * @return Value of property vez.
	 * 
	 */
	public boolean isVez() {
		return vez;
	}

	/**
	 * Setter for property vez.
	 * 
	 * @param vez
	 *            New value of property vez.
	 * 
	 */
	public void setVez(boolean vez) {
		this.vez = vez;
	}

	/**
	 * Getter for property id.
	 * 
	 * @return Value of property id.
	 * 
	 */
	public long getId() {
		return Integer.parseInt(id);
	}

	/**
	 * Setter for property id.
	 * 
	 * @param id
	 *            New value of property id.
	 * 
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	public void precessaCmd(java.lang.String cmd) {
	}

	/**
	 * Getter for property nome.
	 * 
	 * @return Value of property nome.
	 * 
	 */
	public java.lang.String getNome() {
		return nome;
	}

	/**
	 * Setter for property nome.
	 * 
	 * @param nome
	 *            New value of property nome.
	 * 
	 */
	public void setNome(java.lang.String nome) {
		this.nome = nome;
		this.setName(nome);
	}

	public void addText(java.lang.String txt) {
		OutPutText.print(txt + "\n");
	}

	/**
	 * Getter for property saidaTxt.
	 * 
	 * @return Value of property saidaTxt.
	 * 
	 */
	public SystemTextOutputObserver getOutPutText() {
		return OutPutText;
	}

	/**
	 * Setter for property saidaTxt.
	 * 
	 * @param saidaTxt
	 *            New value of property saidaTxt.
	 * 
	 */
	public void setOutPutText(SystemTextOutputObserver saidaTxt) {
		this.OutPutText = saidaTxt;
	}

	/**
	 * @return Returns the input.
	 */
	public final ObjectInputStream getInput() {
		return input;
	}

	/**
	 * @return Returns the output.
	 */
	public final ObjectOutputStream getOutput() {
		return output;
	}

	/**
	 * @return Returns the ativo.
	 */
	public final boolean isAtivo() {
		return ativo;
	}

	/**
	 * @param ativo
	 *            The ativo to set.
	 */
	public final void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * @see java.lang.Thread#start()
	 */
	public synchronized void start() {
		super.start();
		setAtivo(true);
	}
}
