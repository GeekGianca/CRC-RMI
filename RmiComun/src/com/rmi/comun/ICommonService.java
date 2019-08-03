/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmi.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author geekdeveloper
 */
public interface ICommonService extends Remote{
    String callRequest(String divisor, String data) throws RemoteException;
    String verifyCRC(String data, String divisor) throws RemoteException;
    String connect() throws RemoteException;
}
