/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import sistemasdistribuidos.metodosServidor;
import thrift.grafoshandler;

/**
 *
 * @author lucas
 */
public class servidor implements Runnable{
    grafo g = new grafo();
    public void run()
    {
        while (true) 
        {
            try 
            {
                //criando o socket do server na porta 7911
                TServerSocket serverTransport = new TServerSocket(7911);
                //criando o fluxo de dados 
                grafoshandler.Processor fluxo = new grafoshandler.Processor(new metodosServidor(g));
                // criando o servidor
                TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(fluxo));
                // startando
                System.out.println("servidor executando na porta 7911");
                server.serve();

            } 
            catch (Exception ex) 
            {
                System.out.println("deu ruim");
                break;
            }   
        }
    }
    public static void main(String[] args) {

        servidor server = new servidor();
        Thread t = new Thread(server);
        // executando servidor paralelamente com a aplicação
        t.start();
    }
    
}
