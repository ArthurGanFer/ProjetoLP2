/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.exceptions;

/**
 *
 * @author 31338283
 */
public class UserException extends RuntimeException {

    public static String USER_NOT_FOUND = "UserException: Usuario nao encontrado";
    public static String USER_WRONG_PASSWORD = "UserException: Senha invalida";

    public UserException(String msg) {
        super(msg);
    }

}
