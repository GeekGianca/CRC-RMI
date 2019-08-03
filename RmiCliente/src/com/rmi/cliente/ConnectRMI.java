/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmi.cliente;

import com.rmi.comun.ICommonService;
import java.awt.TextField;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JTextField;

/**
 *
 * @author geekdeveloper
 */
public class ConnectRMI {

    private Registry registry;
    private ICommonService icomun;

    public ConnectRMI() throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
        this.icomun = (ICommonService) this.registry.lookup("Connect");
    }

    public String connect(String text) throws RemoteException {
        StringBuilder commandd = new StringBuilder();
        String respuesta = icomun.connect();
        commandd.append("client#: ").append(respuesta).append("\n");
        return commandd.toString();
    }

    public String request(String requested, String text, String command, JTextField crctxt) throws RemoteException {
        StringBuilder commandd = new StringBuilder();
        commandd.append(command).append("\n");
        commandd.append("client#: Divisor received - ").append(requested).append(" Data - ").append(text).append("\n");
        String response = icomun.callRequest(requested, text);
        crctxt.setText(response);
        commandd.append("client#: Response: ").append(response).append("\n");
        return commandd.toString();
    }

    public String verifyCRC(String divisor, String data, String command) throws RemoteException {
        StringBuilder commande = new StringBuilder();
        commande.append(command).append("\n");
        String response = icomun.verifyCRC(divisor, data);
        String resp = "";
        if (Integer.parseInt(response) == 0) {
            resp = "No errors were obtained in the code.";
        } else {
            resp = "Errors were obtained in the code.";
        }
        commande.append("client#: Response: ").append(response).append("\n");
        commande.append("client#: ").append(resp).append("\n");
        return commande.toString();
    }

}
