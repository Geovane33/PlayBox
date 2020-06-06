/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.Cliente;
import br.senac.sp.entidade.Venda;
import br.senac.sp.entidade.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington
 */
public class VendaDAO {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    private void atualizarQuantidadeProdutos(Venda venda) {

        for (Produto produto : venda.getProdutos()) {

            produtoDAO.atualizar(produto);

        }

    }

    private void atualizarProdutoVenda(Venda venda) {

        PreparedStatement ps = null;

        Connection conexao = ConexaoDB.getConexao();

        try {

            for (Produto produto : venda.getProdutos()) {

                ps = conexao.prepareStatement("INSERT INTO venda_produto VALUES (default, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1, produto.getQuantidadeNaVenda());
                ps.setInt(2, produto.getId());
                ps.setInt(3, venda.getId());

                ps.executeUpdate();

            }

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar produto venda");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     *
     * @param venda
     * @return true: venda realizar com sucesso, false: erro ao realivar venda
     */
    public boolean salvar(Venda venda) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        try {

            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
            conexao = ConexaoDB.getConexao();

            instrucaoSQL = conexao.prepareStatement("INSERT INTO venda VALUES(default, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);  //Caso queira retornar o ID do cliente

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setDate(1, new java.sql.Date(venda.getDataVenda().getTime()));
            instrucaoSQL.setDouble(2, venda.getTotal());
            instrucaoSQL.setInt(3, venda.getCliente().getId());
            instrucaoSQL.setInt(4, venda.getFilial().getId());

            int linhasAfetadas = instrucaoSQL.executeUpdate(); //sintetico executeQuery

            if (linhasAfetadas > 0) {

                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys(); //Recupero o ID
                if (generatedKeys.next()) {
                    venda.setId(generatedKeys.getInt(1));
                    atualizarQuantidadeProdutos(venda);
                    atualizarProdutoVenda(venda);
                    return true;
                } else {
                    throw new SQLException("Falha ao obter o ID.");
                }
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar venda");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        } finally {

            //Libero os recursos da memória
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

                ConexaoDB.fecharConexao(conexao);
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }

    private void adicionaClienteVenda(Venda venda, int clienteId) {

        List<Cliente> cliente = clienteDAO.consultar("ID", clienteId + "");

        venda.setCliente(cliente.get(0));
    }

    /**
     * Busca uma List do tipo Venda obtida do banco de dados
     *
     * @param inicio
     * @param fim
     * @return
     */
    public List<Venda> consultar(java.util.Date inicio, java.util.Date fim) {
        ResultSet rs = null;
        Connection conexao;
        PreparedStatement instrucaoSQL = null;

        ArrayList<Venda> listaVenda = new ArrayList<>();

        try {

            conexao = ConexaoDB.getConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM venda WHERE data_venda BETWEEN ? and ?;");
            instrucaoSQL.setDate(1, new Date(inicio.getTime()));
            instrucaoSQL.setDate(2, new Date(fim.getTime()));
            System.out.println("teste de data, remover print depois");

            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setTotal(rs.getInt("total_venda"));
                venda.setId(rs.getInt("id_venda"));
                adicionaClienteVenda(venda, rs.getInt("id_cliente"));
                adicionaProdutosNaVenda(venda, conexao);

                listaVenda.add(venda);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            //Libero os recursos da memória
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        }

        return listaVenda;
    }

    private void adicionaProdutosNaVenda(Venda venda, Connection conexao) {

        ResultSet rs = null;
        PreparedStatement instrucaoSQL = null;

        try {
            instrucaoSQL = conexao.prepareStatement(
                    "SELECT"
                    + "    venda_produto.quantidade_produto AS quantidade_produto_na_venda,"
                    + "    produto.ID_produto  AS id_produto,"
                    + "    produto.nome_produto  AS nome_produto,"
                    + "    produto.quantidade_produto AS quantidade_produto,"
                    + "    produto.valor_produto  AS valor_produto"
                    + "FROM"
                    + "    venda_produto"
                    + "        INNER JOIN"
                    + "    produto ON produto.ID_produto = venda_produto.id_produto"
                    + "WHERE"
                    + "    id_venda = ?;");

            instrucaoSQL.setInt(1, venda.getId());

            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setQuantidade(rs.getInt("quantidade_produto"));
                produto.setQuantidadeNaVenda(rs.getInt("quantidade_produto_na_venda"));
                produto.setValor(rs.getDouble("valor_produto"));
                venda.addProduto(produto);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar produto na venda");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     * Busca uma List do tipo Venda obtida do banco de dados
     *
     * @param idCliente
     * @return
     */
    public List<Venda> clientePossuiVenda(int idCliente) {
        ResultSet rs = null;
        Connection conexao;
        PreparedStatement instrucaoSQL = null;

        ArrayList<Venda> listaVenda = new ArrayList<>();

        try {

            conexao = ConexaoDB.getConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM venda WHERE id_cliente = " + idCliente);
            rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id_venda"));
                listaVenda.add(venda);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            //Libero os recursos da memória
            try {
                if (rs != null) {
                    rs.close();
                }
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }

        return listaVenda;
    }

}
