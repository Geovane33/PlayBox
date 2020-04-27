/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.Cliente;
import br.senac.sp.entidade.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Wellington
 */
public class VendaDAO {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    private void atualizarQuantidadeProdutos(Venda venda, Connection conexao){
        
        for(Produto produto : venda.getProdutos()){
            
            produto.setQuantidade(produto.getQuantidade() - produto.getQuantidadeNaVenda());
            produtoDAO.atualizar(produto, conexao);
            
        }
        
    }
    
    private void atualizarProdutoVenda(Venda venda, Connection conexao){
        
        PreparedStatement ps = null;
        
        try{
            
            for(Produto produto : venda.getProdutos()){
                
                ps = conexao.prepareStatement("INSERT INTO venda_produto (quantidade_produto, id_produto, id_venda) VALUES (?, ?, ?",
                        Statement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, produto.getQuantidadeNaVenda());
                ps.setInt(2, produto.getId());
                ps.setInt(3, venda.getId());
                
                ps.executeUpdate();
                
            }
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    private void adicionaClienteVenda(Venda venda, int clienteId, Connection conexao){
        
        Cliente cliente = clienteDAO.consultarCliente(clienteId, conexao);
        venda.setCliente(cliente);
    }
    
}
