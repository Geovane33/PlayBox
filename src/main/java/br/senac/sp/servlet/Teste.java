/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.ClienteDAO;
import br.senac.sp.dao.ProdutoDAO;
import br.senac.sp.dao.VendaDAO;
import br.senac.sp.entidade.Cliente;
import br.senac.sp.entidade.Filial;
import br.senac.sp.entidade.Produto;
import br.senac.sp.entidade.Venda;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Geovane
 */
public class Teste {

    public static void TesteUpdateMysql() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

        Filial filial = new Filial(1, "SÃO PAULO", "SP");
        Produto produto = new Produto("Notebook1atualizado", "Acer", "Notebook", 100, 2000, new Date(), 1);
        Cliente cliente = new Cliente("clienteAtualizado", "43234432", new Date(), "Masculino", "23432432", "fdsdsf@dsd", "SP", "dasdasda", "rerw", "dasdas", "fdsfsdds", 1);
        Venda venda = new Venda();
        venda.setId(1);
        venda.setDataVenda(new Date());
        cliente.setId(1);
        venda.setCliente(cliente);
        venda.setFilial(filial);
        produto.setId(1);
        venda.addProduto(produto);

        if (produtoDAO.atualizarProduto(produto)) {
            System.out.println("PRODUTO salvo");
        }
        if (clienteDAO.atualizarCliente(cliente)) {
            System.out.println("CLIENTE salvo");
        }

    }

    public static void TesteInsertMysql() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        VendaDAO vendaDAO = new VendaDAO();

        Filial filial = new Filial(1, "SÃO PAULO", "SP");
        Produto produto = new Produto(1, "Notebook1", "Acer", "Notebook", 100, 2000, new Date());
        Cliente cliente = new Cliente(1, "Cliente1-atuuuu", "43234432", new Date(), "Masculino", "23432432", "fdsdsf@dsd", "SP", "dasdasda", "rerw", "dasdas", "fdsfsdds");
        Venda venda = new Venda();
        venda.setId(1);
        venda.setDataVenda(new Date());
        cliente.setId(1);
        venda.setCliente(cliente);
        venda.setFilial(filial);
        produto.setId(1);
        venda.addProduto(produto);

        if (produtoDAO.salvarProduto(produto)) {
            System.out.println("PRODUTO salvo");
        }
        if (clienteDAO.salvarCliente(cliente)) {
            System.out.println("CLIENTE salvo");
        }
        if (vendaDAO.salvarVenda(venda)) {
            System.out.println("VENDA salva");
        }

    }

    public static void TesteConsulta() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

        ArrayList<Cliente> clientes = clienteDAO.consultarCliente("", "TODOS");

        ArrayList<Produto> produtos = produtoDAO.consultarProduto("note");
//        
//        if(produtoDAO.salvarProduto(produto)){
//            System.out.println("PRODUTO salvo");
//        }
//        if(clienteDAO.salvarCliente(cliente)){
//            System.out.println("CLIENTE salvo");
//        }
//        if(vendaDAO.salvarVenda(venda)){
//            System.out.println("VENDA salva");
//        }
    }

}
