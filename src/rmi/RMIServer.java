/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Thắng Bùi
 */
public class RMIServer extends UnicastRemoteObject implements RMIInterface {

    //Khai bao thong tin
    int thisPort = 3232;
    String thisAddress;
    Registry registry;

    // Constructor
    public RMIServer() throws RemoteException {
        try {
            registry = LocateRegistry.createRegistry(thisPort);
            registry.rebind("rmiServer", this);
        } catch (RemoteException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws RemoteException, Exception {
        new RMIServer();
    }

    @Override
    public String sub(int x, int y) throws RemoteException {
        return x + " - " + y + " = " + (x - y);
    }

    @Override
    public void end() throws RemoteException {
        System.out.println("Stopping rmi Server!");
        UnicastRemoteObject.unexportObject(registry, true);
        System.exit(0);
    }

}
