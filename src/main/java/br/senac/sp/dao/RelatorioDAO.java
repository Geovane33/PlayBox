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
import br.senac.sp.entidade.Relatorio;
import br.senac.sp.entidade.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Geovane
 */
public class RelatorioDAO {

    public List vendasFilial(int idFilial) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;

        List<Venda> vendas = new ArrayList<>();
        double totalVenda = 0;
        try {
            conexao = ConexaoDB.getConexao();
            ps = conexao.prepareStatement("SELECT \n"
                    + "   *\n"
                    + "FROM\n"
                    + "    venda v\n"
                    + "    INNER JOIN filial f on f.id_filial = v.id_filial \n"
                    + "    AND f.id_filial = " + idFilial);
            if (idFilial == 0) {
                ps = conexao.prepareStatement("SELECT \n"
                        + "   *\n"
                        + "FROM\n"
                        + "    venda v\n"
                        + "    INNER JOIN filial f on f.id_filial = v.id_filial;");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Filial unidade = new Filial();
                unidade.setId(rs.getInt("id_filial"));
                unidade.setNome(rs.getString("nome_filial"));
                unidade.setEstado(rs.getString("estado_filial"));

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

                Venda venda = new Venda();
                venda.setId(rs.getInt("id_venda"));
                venda.setTotal(rs.getInt("total_venda"));
                venda.setDataVenda(rs.getTimestamp("data_venda"));
                venda.setFilial(unidade);
                venda.setCliente(cliente);
                vendas.add(venda);
            }
            Relatorio relatorio = new Relatorio();
            relatorio.setQtdVenda(vendas.size());


        } catch (SQLException ex) {
            System.out.println("Erro ao gerar relatório");
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
        return null;
    }

    public List consultar(int idFilial) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;

        List<Venda> vendas = new ArrayList<>();
        Map<String, List<Produto>> mapProdutos = produtosVendidos();
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

                Filial unidade = new Filial();
                unidade.setId(rs.getInt("id_filial"));
                unidade.setNome(rs.getString("nome_filial"));
                unidade.setEstado(rs.getString("estado_filial"));

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

                Venda venda = new Venda();
                venda.setId(rs.getInt("id_venda"));
                venda.setTotal(rs.getInt("total_venda"));
                venda.setDataVenda(rs.getTimestamp("data_venda"));
                venda.setFilial(unidade);
                venda.setCliente(cliente);
                venda.setProdutos(mapProdutos.get(venda.getId() + ""));
                vendas.add(venda);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao gerar relatório");
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

    private Map<String, List<Produto>> produtosVendidos() {
        Map<String, List<Produto>> mapProdutos = new HashMap();
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
            ps = conexao.prepareStatement("SELECT \n"
                    + "  p.id_produto,p.id_filial as \"prod_id_filial\",\n"
                    + "  p.nome_produto,p.marca_produto,vp.quantidade_produto,p.valor_produto,p.desc_produto,\n"
                    + "  data_entrada, v.id_venda\n"
                    + "FROM\n"
                    + "    venda v\n"
                    + "        INNER JOIN\n"
                    + "    venda_produto vp ON v.id_venda = vp.id_venda\n"
                    + "        INNER JOIN\n"
                    + "    produto p ON vp.id_produto = p.id_produto");

            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setIdFilial(rs.getInt("prod_id_filial"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setMarca(rs.getString("marca_produto"));
                produto.setQuantidade(rs.getInt("quantidade_produto"));
                produto.setValor(rs.getInt("valor_produto"));
                produto.setDescricao(rs.getString("desc_produto"));
                produto.setDataDeEntrada(rs.getTimestamp("data_entrada"));

                if (mapProdutos.get(rs.getInt("id_venda") + "") != null) {
                    List<Produto> produtosInMap = mapProdutos.get(rs.getInt("id_venda") + "");
                    produtosInMap.add(produto);
                } else {
                    List<Produto> produtosInMap = new ArrayList();
                    produtosInMap.add(produto);
                    mapProdutos.put(rs.getInt("id_venda") + "", produtosInMap);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produtos"
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
        return mapProdutos;
    }
}
