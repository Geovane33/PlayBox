/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.Cliente;
import br.senac.sp.entidade.Filial;
import br.senac.sp.entidade.Produto;
import br.senac.sp.entidade.Venda;
import br.senac.sp.utils.Conversor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geovane
 */
public class RelatorioDAO {

    private final Conversor data = new Conversor();

    public List<Venda> obterRalatorio(int idFilial) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;

        ArrayList<Venda> vendas = new ArrayList<>();

        try {
            conexao = ConexaoDB.getConexao();
            ps = conexao.prepareStatement("SELECT \n"
                    + "    *\n"
                    + "FROM\n"
                    + "    filial AS f\n"
                    + "        INNER JOIN\n"
                    + "    venda AS v ON f.id_filial = v.id_filial\n"
                    + "		INNER JOIN\n"
                    + "	cliente AS c on v.id_cliente = c.id_cliente\n"
                    + "        AND f.id_filial =" + idFilial);
            if (idFilial == 0) {
                ps = conexao.prepareStatement("SELECT \n"
                        + "    *\n"
                        + "FROM\n"
                        + "    filial AS f\n"
                        + "        INNER JOIN\n"
                        + "    venda AS v ON f.id_filial = v.id_filial\n"
                        + "		INNER JOIN\n"
                        + "	cliente AS c on v.id_cliente = c.id_cliente\n");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id_venda"));
                venda.setTotal(rs.getInt("total_venda"));
                venda.setDataVenda(rs.getTimestamp("data_venda"));
                venda.setFilial(obterFilial(rs));
                venda.setCliente(obterCliente(rs));
                venda.setProdutos(produtosDaVenda(venda.getId()));
                vendas.add(venda);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produto"
                    + "");
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
        return vendas;
    }

    /**
     * metodo que realiza pesquisa de produto por nome
     *
     * @param idVenda
     * @return listaProdutos
     */
    private List<Produto> produtosDaVenda(int idVenda) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            conexao = ConexaoDB.getConexao();
            ps = conexao.prepareStatement("SELECT \n"
                    + "*"
                    + "FROM\n"
                    + "    venda v\n"
                    + "        INNER JOIN\n"
                    + "    venda_produto vp ON v.id_venda = vp.id_venda\n"
                    + "        INNER JOIN\n"
                    + "    produto p ON vp.id_produto = p.id_produto\n"
                    + "        AND v.id_venda =" + idVenda);

            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setIdFilial(rs.getInt("id_filial"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setMarca(rs.getString("marca_produto"));
                produto.setQuantidade(rs.getInt("quantidade_produto"));
                produto.setValor(rs.getInt("valor_produto"));
                produto.setDescricao(rs.getString("desc_produto"));
                produto.setDataDeEntrada(rs.getTimestamp("data_entrada"));
                listaProdutos.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produto"
                    + "");
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
        return listaProdutos;
    }

    private Cliente obterCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id_cliente"));
        cliente.setIdFilial(rs.getInt("id_filial"));
        cliente.setNome(rs.getString("nome_cliente"));
        cliente.setCpf(rs.getString("cpf_cliente"));
        cliente.setDataNascimento(rs.getTimestamp("nasc_cliente"));
        cliente.setSexo(rs.getString("sexo_cliente"));
        cliente.setTelefone(rs.getString("telefone_cliente"));
        cliente.setEmail(rs.getString("email_cliente"));
        cliente.setUf(rs.getString("uf_cliente"));
        cliente.setCidade(rs.getString("cidade_cliente"));
        cliente.setCep(rs.getString("cep_cliente"));
        cliente.setBairro(rs.getString("bairro_cliente"));
        cliente.setNumero(rs.getString("numero_cliente"));
        return cliente;
    }

    private Filial obterFilial(ResultSet rs) throws SQLException {
        Filial filial = new Filial();
        filial.setId(rs.getInt("id_filial"));
        filial.setNome(rs.getString("nome_filial"));
        filial.setEstado(rs.getString("estado_filial"));
        return filial;
    }

}
