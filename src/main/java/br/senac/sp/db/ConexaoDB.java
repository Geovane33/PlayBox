/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoDB {

    private static String STATUS = "Não conectado";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";  //A partir da versao 8.0, mudou para com.mysql.cj.jdbc.Driver (Connector/J)                   

    private static String SERVER = "localhost";
    private static String DATABASE = "notestore";              //nome do seu banco de dados
    private static String PORT = "3305";

    private static String USER = "root";                     //nome de um usuário de seu BD      
    private static String PASSWORD = "";                     //sua senha de acesso

    private static String URL = "";

    private static Connection CONEXAO = null;

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConexao() {
        String dbURL = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE + "?useTimezone=true&serverTimezone=UTC&useSSL=false";

        try {
            CONEXAO = DriverManager.getConnection(dbURL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return CONEXAO;
    }

}
