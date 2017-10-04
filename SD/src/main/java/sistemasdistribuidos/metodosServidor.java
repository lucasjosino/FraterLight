/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
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
    
    public String buscaresta(int v1, int v2)
    {
        int index = g.procuraresta(v1, v2);
        if (index != -1)
        {
            aresta a = g.arestas.get(index);
            return (" nome 1: "+ a.v1 +" | "+ " nome 2: "+ a.v2 +" | " + " peso: "+ a.peso +" | " + " descrição: "+ a.desc +" | " + " direcionado: "+ a.direc);
        }
        else
            return "nada encontrado !";
    }
    
    @Override
    public void addvertice(vertice v) throws TException {
        g.vertices.putIfAbsent(v.nome,v);
    }
    
    @Override
    public String listvertice() throws TException {
        String saida = " ";
        synchronized (g.vertices)
        {
            Set<Integer> chaves = g.vertices.keySet();
            for (Integer chave : chaves)
            {
                if(chave != null)
                        saida += "\n nome: "+ g.vertices.get(chave).nome +"| descrição: "+ g.vertices.get(chave).desc +"| cor: "+g.vertices.get(chave).cor +"| peso: "+g.vertices.get(chave).peso;
            }          
        }
        return saida;
    }

    @Override
    public void addaresta(aresta a) throws TException { 
        if (!(g.arestas.contains(a)))
        {
            synchronized(g.arestas)
            {
                if (g.vertices.get(a.v1) != null && g.vertices.get(a.v2) != null)
                    g.arestas.add(a);
            }
        }
        else
            System.out.println("não foi possivel adicionar a aresta, confira os vertices !");   
    }

    @Override
    public String listaresta() throws TException {
        String saida = "";
        synchronized (g.arestas)
        {
            for( aresta a : g.arestas)
            {
                saida += "\n nome 1: "+ a.v1 +" | "+ " nome 2: "+ a.v2 +" | " + " peso: "+ a.peso +" | " + " descrição: "+ a.desc +" | " + " direcionado: "+ a.direc;
            }
        }
        return saida;
    }

    @Override
    public String listarestavertice(int nome) throws TException {
        return g.listavertice(nome);       
    }

    @Override
    public String listverticevizinho(int nome) throws TException {
        String saida = "";
        if (!(g.vertices.containsKey(nome)))
        {
            saida = "esse vertice não existe !";
            return saida;
        }
        vertice vaux = g.vertices.get(nome);
        synchronized (vaux)
        {
            synchronized (g.arestas)
            {
                for (aresta a : g.arestas)
                {
                    if (a.v1 == vaux.nome)
                    {
                        vertice vaux2 = g.vertices.get(a.v2);
                        synchronized (vaux2)
                        {
                            saida += "\n nome: "+ vaux2.nome +"| descrição: "+ vaux2.desc +"| cor: "+ vaux2.cor +"| peso: "+vaux2.peso;
                        }
                    }
                    else if (a.v2 == vaux.nome)
                    {
                        vertice vaux2 = g.vertices.get(a.v1);
                        synchronized (vaux2)
                        {
                            saida += "\n nome: "+ vaux2.nome +"| descrição: "+ vaux2.desc +"| cor: "+ vaux2.cor +"| peso: "+vaux2.peso;
                        }
                    }       
                }
            }
        }
        return saida;    
    }    

    @Override
    public void editvertice(int nome, int cor, String desc, double peso) throws TException {
        try 
        {
            vertice v = g.vertices.get(nome);
                if (v != null)
                {
                    synchronized(v)
                    {
                        v.setCor(cor);
                        v.desc = desc;
                        v.setPeso(peso);
                    }
                }
                else
                {
                    System.out.println("não foi possivel editar esse vertice");
                }
            
        }
        catch (Exception e)
        {
            System.out.println("não é possivel editar");
        }
    }

    @Override
    public void editaresta(int v1, int v2, double peso, String desc, boolean direc) throws TException {
            int index = g.procuraresta(v1, v2);
            aresta a = g.arestas.get(index);
            if (index != -1)
            {
                synchronized(a)
                {
                    a.peso = peso;
                    a.desc = desc;
                    a.direc = direc;
                }
            }
            else
            System.out.println("não é possivel editar !");
    }

    @Override
    public String buscavertice(int nome) throws TException {
        vertice v = g.vertices.get(nome);
        if (v != null)
            return ("nome: "+ v.nome +"| descrição: "+ v.desc +"| cor: "+ v.cor +"| peso: "+v.peso);
        else
            return "nada encontrado !";
    }

    @Override
    public void delaresta(int v1, int v2) throws TException {
        if (!g.arestas.isEmpty())
        {
            synchronized (g.arestas)
            {
                for( aresta a : g.arestas)
                { 
                    if (a.v1 == v1 && a.v2 == v2)
                    {
                      g.arestas.remove(a);
                    }    
                }
            }
        }
        else
            System.out.println("não foi possivel remover !");
    }

    @Override
    public void delvertice(int nome) throws TException {
        if (!g.vertices.isEmpty())
        {
            synchronized(g.vertices)
            {
               vertice v = g.vertices.get(nome);
               g.vertices.remove(nome);     
            }
            synchronized(g.arestas)
            {
                
                for(int i = 0; i < g.arestas.size(); i++)
                { 
                    if(g.arestas.get(i).v1 == nome || g.arestas.get(i).v2 == nome)
                    {   
                        g.arestas.remove(i); 
                        i--;
                    } 
                } 
            }    
        }
        else
            System.out.println("não foi possivel remover !");
    }


}
