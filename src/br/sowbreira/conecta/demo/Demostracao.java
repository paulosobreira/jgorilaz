package br.sowbreira.conecta.demo;

import java.net.InetAddress;
import java.net.Socket;

import br.sowbreira.conecta.client.ClientThread;
import br.sowbreira.conecta.commons.BroadCastMessageCommand;
import br.sowbreira.conecta.commons.SystemTextOutputObserver;
import br.sowbreira.conecta.server.ServerThread;

/**
 * @author sobreira
 * Created on 18/06/2004
 */
public class Demostracao {
	public static void main(String[] args) throws Exception {
		SystemTextOutputObserver txtSrv=  new SystemTextOutputObserver(){
			public void print(String text){};
		};
		SystemTextOutputObserver txtCli1=  new SystemTextOutputObserver(){
			public void print(String text){};
		};
		SystemTextOutputObserver txtCli2=  new SystemTextOutputObserver(){
			public void print(String text){};
		};
		ServerThread servidor = new ServerThread(5001,txtSrv);
		servidor.setAtivo(true);
		servidor.setJogoIniciado(false);
		servidor.start();
		Thread.sleep(100);
		ClientThread cliente1 = null,cliente2 = null; 
		try {
			cliente1 = new ClientThread(
					new Socket(InetAddress.getByName("localhost"),5001),txtCli1);
			cliente1.start();
		} catch (Exception e) {
			System.out.println("erro no cli1");
			e.printStackTrace();
		}
		try {
			cliente2 = new ClientThread(
					new Socket(InetAddress.getByName("localhost"),5001),txtCli2);
			cliente2.start();
		} catch (Exception e) {
			System.out.println("erro no cli2");
			e.printStackTrace();
		}
		servidor.setJogoIniciado(true);
		cliente1.escreverObjeto(new BroadCastMessageCommand("teste"));
		Thread.sleep(200);
		System.out.println("Servidor :"+ txtSrv.toString());
		System.out.println("Cliente1 :"+ txtCli1.toString());
		System.out.println("Cliente2 :"+ txtCli2.toString());
		Thread.sleep(100);
		cliente1.setAtivo(false);
		cliente2.setAtivo(false);
	}
}
