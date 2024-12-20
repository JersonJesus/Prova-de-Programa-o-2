/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Jerson Vndr�
 */
public class ModuloConexao {
    //m�todo respons�vel pela conex�o com o BD
    public static Connection conector() {
        java.sql.Connection conexao = null;
        
        //a linha abaixo chama o driver
        String driver = "com.mysql.cj.jdbc.Driver";
        
        //armazenando informa�oes referente ao banco
        String url = "jdbc:mysql://localhost:3306/bdsistemacadastro";
        String user = "root"; 
        String password = "bebucho"; 
        
        //estable�endo a conexao com o bd
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //System.out.println(e);
            return null;
        }
    }
}
