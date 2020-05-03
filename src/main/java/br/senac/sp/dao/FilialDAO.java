/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB2;
import br.senac.sp.entidade.Filial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Geovane
 */
public class FilialDAO {

    /**
     * Salvar filial
     *
     * @param filial recebe uma entidade filial
     * @return true: salvo com sucesso false: erro ao salvar
     */
    public boolean salvarFilial(Filial filial) {
        boolean ok = false;
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB2.getConexao();
            String sql = "INSERT INTO filial VALUES (default,?,?,?)";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, filial.getId());
            ps.setString(2, filial.getNome());
            ps.setString(3, filial.getEstado());
            ps.execute();
            ok = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erro ao salvar filial");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        } finally {
            //Libero os recursos da memória
            try {
                if (ps != null) {
                    ps.close();
                }
                ConexaoDB2.fecharConexao();

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return ok;
    }

    /**
     * Retorna uma lista de filiais de acordo com os paramentro passados
     *
     * @param values String - recebe por parametro o cpf, nome ou id do cliente
     * a ser consultado
     * @param tipo String - recebe por parametro o tipo de consulta a ser
     * realizada --> nome, ID ou todos
     * @return listaFiliais
     */
    public ArrayList<Filial> consultarFiliais(String values, String tipo) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;
        ArrayList<Filial> listaClientes = new ArrayList<>();
        try {
            conexao = ConexaoDB2.getConexao();
            if (!values.equals("") && tipo.equals("nome")) {
                System.out.println("COnsultar NOME");
                ps = conexao.prepareStatement("SELECT * FROM filial where nome_filial like '%" + values + "%';");
            } else if (tipo.equals("ID")) {
                System.out.println("COnsultar ID");
                ps = conexao.prepareStatement("SELECT * FROM filial WHERE id_filial = " + Integer.parseInt(values));
            } else if (tipo.equals("todos")) {
                System.out.println("COnsultar TODOS");
                ps = conexao.prepareStatement("SELECT * FROM filial ");
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Filial filial = new Filial();
                filial.setId(rs.getInt("id_filial"));
                filial.setNome(rs.getString("nome_filial"));
                filial.setEstado(rs.getString("estado_filial"));

                listaClientes.add(filial);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar filial");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            //Libero os recursos da memória
            try {
                if (rs != null) {
                    rs.close();
                }
                ConexaoDB2.fecharConexao();

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        }
        return listaClientes;
    }

    /**
     * Atualizar dados do Filial
     *
     * @param filial recebe uma entidade filial
     * @return true: Filial atulizado com sucesso false: Erro ao atualizar o
     * Filial
     */
    public boolean atualizarFilial(Filial filial) {
        Connection conexao = null;
        PreparedStatement ps = null;

        try {

            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe ConexãoDB já carrega o Driver e define os parâmetros de conexão
            conexao = ConexaoDB2.getConexao();

            ps = conexao.prepareStatement("UPDATE cliente SET nome_filial = ?, estado_filial = ? WHERE id_filial = ?",
                    Statement.RETURN_GENERATED_KEYS);  //Caso queira retornar o ID
            //Adiciono os parâmetros ao meu comando SQL
            ps.setString(1, filial.getNome());
            ps.setString(2, filial.getEstado());
            ps.setInt(3, filial.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar filial");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        } finally {

            //Libero os recursos da memória
            try {
                if (ps != null) {
                    ps.close();
                }

                ConexaoDB2.fecharConexao();

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }

    /**
     * Excluir uma determinado filial
     *
     * @param id - recebe por parametro o id referente a filial que deseja
     * excluir
     * @return boolean - true: excluido com sucesso false: erro ao excluir
     */
    public boolean excluirCliente(int id) {
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB2.getConexao();
            ps = conexao.prepareStatement("DELETE FROM filial WHERE id_filial = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir filial");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        } finally {
            //Libero os recursos da memória
            try {
                if (ps != null) {
                    ps.close();
                }
                ConexaoDB2.fecharConexao();

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());

            }
        }
    }
}
