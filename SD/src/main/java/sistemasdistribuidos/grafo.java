/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import thrift.aresta;
import thrift.vertice;

/**
 *
 * @author lucas
 */
public class grafo {
    Map<Integer,vertice> vertices = new HashMap<Integer,vertice>();
    // vector é thread-safe porem percorrer ele é uma bosta
    Vector<aresta> arestas = new Vector();
    
    public String listavertice (int nome)
    {
        // bem ruim isso aqui, arrumar quando possivel
        String saida = "";
        if (!(vertices.containsKey(nome)))
        {
            saida = "esse vertice não existe !";
            return saida;
        }
        synchronized (arestas)
        {
            for( aresta a : arestas)
            { 
                System.out.println(a.v1);
                if (a.v1 == nome || a.v2 == nome)
                {
                    System.out.println(a);
                    saida += "\n nome 1: "+ a.v1 +" | "+ " nome 2: "+ a.v2 +" | " + " peso: "+ a.peso +" | " + " descrição: "+ a.desc +" | " + " direcionado: "+ a.direc;
                }

            }
        }
        return saida;
    }
    
    public int procuraresta(int v1, int v2)
    {
        // bem ruim isso aqui, arrumar quando possivel
        synchronized (arestas){
            for (aresta a : arestas)
            {
                if (a.v1 == v1 && a.v2 == v2)
                    return arestas.indexOf(a);
            }
        }
        return -1;
    }
    
    
    

}
