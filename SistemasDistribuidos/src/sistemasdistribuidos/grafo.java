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
    ArrayList<aresta> arestas = new ArrayList();
    
    public String listavertice (int nome)
    {
        String saida = "";
        for( aresta a : arestas)
        { 
            System.out.println(a.v1);
            if (a.v1 == nome || a.v2 == nome)
            {
                System.out.println(a);
                saida += "\n nome 1: "+ a.v1 +" | "+ " nome 2: "+ a.v2 +" | " + " peso: "+ a.peso +" | " + " descrição: "+ a.desc +" | " + " direcionado: "+ a.direc;
            }
                
        }
        return saida;
    }
    
    
    

}
