/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

import java.util.InputMismatchException;
import java.util.Scanner;
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
        transport = new TSocket(args[0], Integer.parseInt(args[1]));
        Scanner sc = new Scanner(System.in); 
        while (true)
        {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            grafoshandler.Client client =  new grafoshandler.Client(protocol);
            System.out.println("---- MENU ----");
            System.out.println("1:adicionar vertice | 2:adicionar aresta");
            System.out.println("3:editar vertice | 4:editar aresta  ");
            System.out.println("5:remover vertice | 6:remover aresta ");
            System.out.println("7:lista vertices | 8:lista arestas");
            System.out.println("9:lista arestas do vertice | 10:lista vizinhos do vertice");
            int x;
            x = sc.nextInt();
            switch (x)
            {
                case 1:
                {
                    System.out.println("digite os dados do vertice na ordem \n nome,cor,desc,peso");
                    try 
                    {
                        int nome = sc.nextInt();
                        int cor = sc.nextInt();
                        String desc = sc.next();
                        double peso = sc.nextDouble();
                        sc.nextLine();
                        vertice v = new vertice(nome,cor,desc,peso);
                        client.addvertice(v);
                    }
                    catch (Exception e)
                    {
                        System.out.println("não foi possivel salvar o vertice");
                    } 
                }
                break;
                case 2:
                {
                    try 
                    {
                        System.out.println("digite os dados da aresta na ordem \n vertice 1,vertice 2, cor,desc,direc true/false");
                        int v1 = sc.nextInt();
                        int v2 = sc.nextInt();
                        int cor = sc.nextInt();
                        sc.nextLine();
                        String desc = sc.nextLine();
                        String direc = sc.nextLine();
                        boolean bool;
                        if (direc.equals("true"))
                            bool = true;
                        else
                            bool = false;     
                        aresta a = new aresta(v1,v2,cor,desc,bool);
                        client.addaresta(a);
                    }
                    catch (Exception e)
                    {
                        System.out.println("não foi possivel salvar a aresta");
                    }
                }
                break;
                case 3:
                {
                    try
                    {
                        System.out.println("digite os dados do vertice na ordem \n nome,cor,desc,peso");
                        int nome = sc.nextInt();
                        int cor = sc.nextInt();
                        sc.nextLine();
                        String desc = sc.nextLine();
                        double peso = sc.nextDouble();
                        System.out.println("o valor de desc é: "+desc);
                        client.editvertice(nome, cor, desc, peso);
                    }
                    catch (Exception e)
                    {
                        System.out.println("não foi possivel salvar o vertice");
                        System.out.println(e);
                    }
                }
                break;
                case 4:
                {
                    try
                    {
                    System.out.println("digite os dados da aresta na ordem \n vertice 1,vertice 2, cor,desc,direc true/false");
                    int v1 = sc.nextInt();
                    int v2 = sc.nextInt();
                    int cor = sc.nextInt();
                    sc.nextLine();
                    String desc = sc.nextLine();
                    String direc = sc.nextLine();
                    boolean bool;
                    if (direc.equals("true"))
                        bool = true;
                    else
                        bool = false;  
                    client.editaresta(v1, v2, cor, desc, bool);
                    }
                    catch (Exception e)
                    {
                        System.out.println("não foi possivel salvar a aresta");
                    }
                }
                break;
                case 5:
                {
                    try
                    {
                        System.out.println("digite o nome do vertice");
                        int nome = sc.nextInt();
                        client.delvertice(nome);
                    }
                    catch (Exception e)
                    {
                        System.out.println("não foi possivel remover"); 
                    }
                }
                break;
                case 6:
                {
                    try
                    {
                        System.out.println("digite as ligações da aresta");
                        int v1 = sc.nextInt();
                        int v2 = sc.nextInt();
                        client.delaresta(v1, v2);
                    }
                    catch (Exception e)
                    {
                        System.out.println("não foi possivel remover, aconteceu algum erro na entrada !");
                    }
                    

                    
                }
                break;
                case 7:
                {
                    System.out.println("--- LISTA DE VERTICES EXISTENTES ---");
                    String resultado = client.listvertice();
                    System.out.println(resultado);
                }
                break;
                case 8:
                {
                   System.out.println("--- LISTA DE ARESTAS EXISTENTES ---");
                   String resultado = client.listaresta();
                   System.out.println(resultado); 
                }
                break;
                case 9:
                {
                    try
                    {
                        System.out.println("digite o nome do vertice");
                        int nome = sc.nextInt();
                        System.out.println("--- LISTA DE ARESTAS DO VERTICE ---");
                        String resultado = client.listarestavertice(nome);
                        System.out.println(resultado);
                    }
                    catch (Exception e)
                    {
                        System.out.println("não foi possivel recuperar a lista");
                    }
                }
                break;
                case 10:
                {
                    try
                    {
                        System.out.println("digite o nome do vertice");
                        int nome = sc.nextInt();
                        System.out.println("--- LISTA DE VERTICES VIZINHOS ---");
                        String resultado = client.listverticevizinho(nome);
                        System.out.println(resultado);
                    }
                    catch (Exception e)
                    {
                        System.out.println("não foi possivel recuperar os vizinhos");
                    }
                }
                break;
                default :
                    System.out.println("você digitou algo errado !");
                break;
                    
            }
            transport.close();
        }
        
    } 
    catch (Exception x) 
    {
        x.printStackTrace();
    } 
  }
}