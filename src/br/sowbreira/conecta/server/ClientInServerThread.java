package br.sowbreira.conecta.server;

import br.sowbreira.conecta.commons.ConnectionManagerThread;

/**
 * @author  Paulo Sobreira
 * Created on 9 de Outubro de 2003, 20:36
 * Esta classe representa um thread de servidor
 */
public class ClientInServerThread extends ConnectionManagerThread {
    

    /** Creates a new instance of LinhaJogadorSrv */
    public ClientInServerThread(java.net.Socket con) {
        super(con);
    }
    
}
