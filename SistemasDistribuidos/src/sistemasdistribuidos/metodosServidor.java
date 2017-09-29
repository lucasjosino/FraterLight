/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import org.apache.thrift.TException;
import thrift.grafoshandler;
import sistemasdistribuidos.grafo;

/**
 *
 * @author lucas
 */
public class metodosServidor implements grafoshandler.Iface {
    grafo g;

    public metodosServidor(grafo g) {
        this.g = g;
    }

    @Override
    public String addvertice(int nome) throws TException {
        g.arestas.add(""+nome);
        System.out.println("adicionando aresta !");
        return "";
    }
    
}
