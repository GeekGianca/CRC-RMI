/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmi.servidor;

import com.rmi.comun.ICommonService;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author geekdeveloper
 */
public class Principal {
    public static void main(String[] args) throws RemoteException {
        System.out.println("Starting Server...");
        Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        CommonImpl implemt = new CommonImpl();
        System.out.println("Making implementations...");
        ICommonService iComun = (ICommonService) UnicastRemoteObject.exportObject(implemt, 0);
        registry.rebind("Connect", iComun);
        System.out.println("Server started successfully ...");
    }
}
