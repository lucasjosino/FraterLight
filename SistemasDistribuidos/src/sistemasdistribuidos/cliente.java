/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.aresta;
import thrift.grafoshandler;
import thrift.vertice;

/**
 *
 * @author lucas
 */
public class cliente {
  public static void main(String [] args) { 
    try 
    {
        TTransport transport;
        transport = new TSocket("localhost", 7911);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        grafoshandler.Client client =  new grafoshandler.Client(protocol);
        vertice v = new vertice(2,3,"primeiro grafo",0);
        vertice v2 = new vertice(3,4,"segundo",0);
        vertice v3 = new vertice(5,3,"primeiro grafo",0);
        aresta a = new aresta(2,3,0,"primeira aresta",true);
        aresta a2 = new aresta(2,5,0,"primeira aresta",true);
        client.addvertice(v);
        client.addvertice(v2);
        client.addvertice(v3);
        client.addaresta(a);
        client.addaresta(a2);
//        String resultado = client.listvertice();
//        String resultado2 = client.listaresta();
//        System.out.println(resultado);
//        System.out.println(resultado2);
        String resultadolist = client.listarestavertice(2);
        System.out.println("o resultado é "+resultadolist);
        transport.close();
    } 
    catch (Exception x) 
    {
        x.printStackTrace();
    } 
  }
}