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
import thrift.grafoshandler;

/**
 *
 * @author lucas
 */
public class cliente {
  public static void main(String [] args) { 
    try {
      TTransport transport;
      transport = new TSocket("localhost", 7911);
      transport.open();
      TProtocol protocol = new TBinaryProtocol(transport);
      grafoshandler.Client client =  new grafoshandler.Client(protocol);
      client.send_addvertice(2);
      transport.close();
    } catch (Exception x) {
      x.printStackTrace();
    } 
  }
}