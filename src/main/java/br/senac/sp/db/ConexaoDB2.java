package br.senac.sp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoDB2 {
    private static int n = 1;
    private static String STATUS = "Não conectado";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";  //A partir da versao 8.0, mudou para com.mysql.cj.jdbc.Driver (Connector/J)                   

    private static String DB_ADDRESS = "jdbc:mysql://localhost:3305/notestore";
    private static String USER = "root";                     //nome de um usuário de seu BD      
    private static String PASSWORD = "";                     //sua senha de acesso
    private static Connection CONEXAO = null;

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoDB2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Verificar se existe uma conexão com o banco de dados
     */
    public static void getStatus(){
        System.out.println(STATUS);
    }

    /**
     * Obter conexão com o banco de dados
     * @return Connection - se for possivel realizar a conexão com o banco de dados 
     * é retornado uma Connection
     */

    public static Connection getConexao() {
        if(n==0){
         while(n<1 && n>2){
             System.out.println("Digite apenas 1 ou 2");
            Scanner teste = new Scanner(System.in);
        System.out.println("Escolha uma conexão\n 1 - conexão local\n 2 - conexão remota");
        try{
             n = teste.nextInt();
        }catch(NumberFormatException ex){
            System.out.println("Digite apenas numero");
        }
         }
        }
        if(n==2){
            return ConexaoDB2.getConexao();
        }
        
        String dbURL = DB_ADDRESS + "?useTimezone=true&serverTimezone=UTC&useSSL=false";

        try {
            CONEXAO = DriverManager.getConnection(dbURL, USER, PASSWORD);
            STATUS = "conectado";
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar no banco");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return CONEXAO;
    }

    /**
     * fechar conexão do banco de dados
     * @return boolean - true: conexão fechada com sucesso false: erro ao fechar conexão
     */
    public static boolean fecharConexao(){
        try {
            if (CONEXAO != null) {
                if (!CONEXAO.isClosed()) {
                    CONEXAO.close();
                }
            }
            STATUS = "Não conectado";
            return true;

        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexao do banco de dados");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
    }
}
