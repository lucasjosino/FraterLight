/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdistribuidos;

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
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        grafoshandler.Client client =  new grafoshandler.Client(protocol);
        Scanner sc = new Scanner(System.in); 
        int x;
        while (true)
        {
            System.out.println("---- MENU ----");
            System.out.println("1:adicionar vertice");
            System.out.println("2:adicionar aresta ");
            System.out.println("3:editar vertice ");
            System.out.println("4:editar aresta ");
            System.out.println("5:remover vertice ");
            System.out.println("6:remover aresta");
            System.out.println("7:lista vertices");
            System.out.println("8:lista arestas");
            System.out.println("9:lista arestas do vertice");
            System.out.println("10:lista vizinhos do verice");
            x = sc.nextInt();
            switch (x)
            {
                case 1:
                {
                    System.out.println("digite os dados do vertice na ordem \n nome,cor,desc,peso");
                    int nome = sc.nextInt();
                    int cor = sc.nextInt();
                    String desc = sc.next();
                    double peso = sc.nextDouble();
                    vertice v = new vertice(nome,cor,desc,peso);
                    client.addvertice(v);
                }
                case 2:
                {
                    System.out.println("digite os dados da aresta na ordem \n vertice 1,vertice 2, cor,desc,direc true/false");
                    int v1 = sc.nextInt();
                    int v2 = sc.nextInt();
                    int cor = sc.nextInt();
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
                case 3:
                {
                    System.out.println("digite os dados do vertice na ordem \n nome,cor,desc,peso");
                    int nome = sc.nextInt();
                    int cor = sc.nextInt();
                    String desc = sc.nextLine();
                    double peso = sc.nextDouble();
                    client.editvertice(nome, cor, desc, peso);
                }
                case 4:
                {
                    System.out.println("digite os dados da aresta na ordem \n vertice 1,vertice 2, cor,desc,direc true/false");
                    int v1 = sc.nextInt();
                    int v2 = sc.nextInt();
                    int cor = sc.nextInt();
                    String desc = sc.nextLine();
                    String direc = sc.nextLine();
                    boolean bool;
                    if (direc.equals("true"))
                        bool = true;
                    else
                        bool = false;     
                    client.editaresta(v1, v2, cor, desc, bool);
                }
                case 5:
                {
                    System.out.println("digite o nome do vertice");
                    int nome = sc.nextInt();
                    client.delvertice(nome);
                }
                case 6:
                {
                    System.out.println("digite as ligações da aresta");
                    int v1 = sc.nextInt();
                    int v2 = sc.nextInt();
                    client.delaresta(v1, v2);
                }
                case 7:
                {
                    System.out.println("--- LISTA DE VERTICES EXISTENTES ---");
                    client.listvertice();
                }
                case 8:
                {
                    System.out.println("--- LISTA DE ARESTAS EXISTENTES ---");
                    client.listaresta();
                }
                case 9:
                {
                    System.out.println("digite o nome do vertice");
                    int nome = sc.nextInt();
                    System.out.println("--- LISTA DE ARESTAS DO VERTICE ---");
                    client.listarestavertice(nome);
                }
                case 10:
                {
                    System.out.println("digite o nome do vertice");
                    int nome = sc.nextInt();
                    System.out.println("--- LISTA DE VERTICES VIZINHOS ---");
                    client.listverticevizinho(nome);
                }
                default :
                    System.out.println("você digitou algo errado !");
                    
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