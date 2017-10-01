/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.util.Set;
import org.apache.thrift.TException;
import thrift.grafoshandler;
import sistemasdistribuidos.grafo;
import thrift.aresta;
import thrift.vertice;

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
    public void addvertice(vertice v) throws TException {
        g.vertices.putIfAbsent(v.nome, v);
    }
    
    @Override
    public String listvertice() throws TException {
        String saida = "";
        Set<Integer> chaves = g.vertices.keySet();
        for (Integer chave : chaves)
        {
            if(chave != null)
                    saida += "\n nome: "+ g.vertices.get(chave).nome +"| descrição: "+ g.vertices.get(chave).desc +"| cor: "+g.vertices.get(chave).cor +"| peso: "+g.vertices.get(chave).peso;
        }
        return saida;
    }

    @Override
    public void addaresta(aresta a) throws TException { 
        if (!(g.arestas.contains(a)))
        {
            if (g.vertices.get(a.v1) != null && g.vertices.get(a.v2) != null)
                g.arestas.add(a);
        }
        else
            System.out.println("não foi possivel adicionar !");
        
    }

    @Override
    public String listaresta() throws TException {
        String saida = "";
        for( aresta a : g.arestas)
        {
            saida += "\n nome 1: "+ a.v1 +" | "+ " nome 2: "+ a.v2 +" | " + " peso: "+ a.peso +" | " + " descrição: "+ a.desc +" | " + " direcionado: "+ a.direc;
        }
        return saida;
    }

    @Override
    public String listarestavertice(int nome) throws TException {
        
        
    }

    @Override
    public String listverticevizinho(int nome) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
