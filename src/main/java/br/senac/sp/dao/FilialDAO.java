/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.Filial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geovane
 */
public class FilialDAO implements DAO {

    /**
     * Salvar filial
     *
     * @param object recebe um objeto filial
     * @return true: salvo com sucesso false: erro ao salvar
     */
    @Override
    public boolean salvar(Object object) {
        Filial filial = (Filial) object;
        boolean ok = false;
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
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
                ConexaoDB.fecharConexao(conexao);

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
    public List<Filial> consultarFilial(String values, String tipo) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;
        List<Filial> listFiliais = new ArrayList<>();
        try {
            conexao = ConexaoDB.getConexao();
            if (!values.equals("") && tipo.equals("nome")) {
                ps = conexao.prepareStatement("SELECT * FROM filial where nome_filial like '%" + values + "%';");
            } else if (!values.equals("") && tipo.equals("ID")) {
                ps = conexao.prepareStatement("SELECT * FROM filial WHERE id_filial = " + Integer.parseInt(values));
            } else if (tipo.equals("TODOS")) {
                ps = conexao.prepareStatement("SELECT * FROM filial ");
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Filial filial = new Filial();
                filial.setId(rs.getInt("id_filial"));
                filial.setNome(rs.getString("nome_filial"));
                filial.setEstado(rs.getString("estado_filial"));

                listFiliais.add(filial);
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
                ConexaoDB.fecharConexao(conexao);

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        }

        return listFiliais;
    }

    /**
     * Atualizar dados do Filial
     *
     * @param object recebe uma entidade filial
     * @return true: Filial atulizado com sucesso false: Erro ao atualizar o
     * Filial
     */
    @Override
    public boolean atualizar(Object object) {
        Filial filial = (Filial) object;
        Connection conexao = null;
        PreparedStatement ps = null;

        try {

            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe ConexãoDB já carrega o Driver e define os parâmetros de conexão
            conexao = ConexaoDB.getConexao();

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

                ConexaoDB.fecharConexao(conexao);

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
    @Override
    public boolean excluir(int id) {
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
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
                ConexaoDB.fecharConexao(conexao);

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());

            }
        }
    }

    @Override
    public List<Object> consultar(String values, String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
