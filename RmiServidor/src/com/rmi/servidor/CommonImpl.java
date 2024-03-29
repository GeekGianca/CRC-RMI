/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmi.servidor;

import com.rmi.comun.ICommonService;
import java.rmi.RemoteException;

/**
 *
 * @author geekdeveloper
 */
public class CommonImpl implements  ICommonService{
    
    /**
     * Retorna el resultado de la division del divisor por el binario
     * @param divisor
     * @param data
     * @return
     * @throws RemoteException 
     */
    @Override
    public String callRequest(String divisor, String data) throws RemoteException {
        String code = data;
        while (code.length() < (data.length() + divisor.length() - 1)) {
            code = code + "0";
        }
        code = data + verifyCRC(code, divisor);
        System.out.println(code);
        return code;
    }

    @Override
    public String verifyCRC(String data, String divisor) throws RemoteException {
        //En este caso como el divisor tiene una longitud, entonces lo que hace es tomar la misma longitud y validar
        //si el numero en la posicion de esa longitud es igual al numero de la posicion del divisor,
        //entonces agrega uno o cero respectivamente, como en esta division no hay acarreo entonces es una operacion
        //binaria de 1 y 0
        int pointer = divisor.length();
        String result = data.substring(0, pointer);
        String remainder = "";
        for (int i = 0; i < divisor.length(); i++) {
            if (result.charAt(i) == divisor.charAt(i)) {
                remainder += "0";
            } else {
                remainder += "1";
            }
        }
        while (pointer < data.length()) {
            if (remainder.charAt(0) == '0') {
                remainder = remainder.substring(1, remainder.length());
                remainder = remainder + String.valueOf(data.charAt(pointer));
                pointer++;
            }
            result = remainder;
            remainder = "";
            for (int i = 0; i < divisor.length(); i++) {
                if (result.charAt(i) == divisor.charAt(i)) {
                    remainder += "0";
                } else {
                    remainder += "1";
                }
            }
        }
        return remainder.substring(1, remainder.length());
    }

    @Override
    public String connect() throws RemoteException {
        return "A successful connection has been made!";
    }
    
}
