package br.senac.sp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoDB {

    private static String STATUS = "Não conectado";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";                
    private static String DB_ADDRESS = "jdbc:mysql://localhost:3307/notestore";
    private static String USER = "root";
    private static String PASSWORD = "";
    private static Connection CONEXAO = null;

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoDB.class.getName()).log(Level.SEVERE, null, ex);
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
        String dbURL = DB_ADDRESS + "?useTimezone=true&serverTimezone=UTC";

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
     * @param CONEXAO 
     * @return boolean - true: conexão fechada com sucesso false: erro ao fechar conexão
     */
    public static boolean fecharConexao(Connection CONEXAO){
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
