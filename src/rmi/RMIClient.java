/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author Thắng Bùi
 */
public class RMIClient {
    RMIInterface rmiServer;
    Registry registry;
    
    public RMIClient(String address, int port) {
        try {
            registry = LocateRegistry.getRegistry(address, port);
            rmiServer = (RMIInterface) (registry.lookup("rmiServer"));
        } catch(RemoteException e) {
            System.out.println(e);
        } catch (NotBoundException e) {
            System.out.println(e);
        }
    }
    
    public String sub(int a, int b) {
        try {
            return rmiServer.sub(a, b);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public void theEnd() {
        try {
            rmiServer.end();
        } catch (RemoteException e) {
            //System.out.println(e);
        }
    }
    
    
    public static void main(String[] args) {
        RMIClient client = new RMIClient("localhost", 3232);
        Scanner scan = new Scanner(System.in);
        String str = "";
        
        try {
            System.out.println("Nhập vào số a: ");
            int a = scan.nextInt();
            System.out.println("Nhập vào số b: ");
            int b = scan.nextInt();
            
            System.out.println(client.sub(a, b));
            
            client.theEnd();         
        } catch (Exception e) {
            //System.out.println(e);
        }
        
    }
}
