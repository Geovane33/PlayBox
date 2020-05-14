/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.ProdutoDAO;
import br.senac.sp.dao.VendaDAO;
import br.senac.sp.entidade.Produto;
import br.senac.sp.entidade.Venda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wellington
 */
public class ControllerVenda implements Controller {

    @Override
    public void adicionar(HttpServletRequest request, HttpServletResponse response) {
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create();
            VendaDAO vendaDAO = new VendaDAO();
            Venda venda = gson.fromJson(request.getHeader("X-Venda"), Venda.class);
            String msg = validarEstoque(venda);

            PrintWriter out = response.getWriter();
            if (msg.equals("")) {
                if (vendaDAO.salvarVenda(venda)) {
                    out.write("200");
                } else {
                    out.write("500");
                }
            } else {
                out.write("Erro ao finalizar Venda, produtos sem estoque suficiente");
                out.write(msg);
            }
            out.flush();
            out.close();
        } catch (IOException ex) {
            System.out.println("Erro ao consultar filiais");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());

        }
    }
    
      /**
     * Sem estrutura de codigos
     *
     * @param request
     * @param response
     */
    @Override
    public void consultar(HttpServletRequest request, HttpServletResponse response) {
    }

    /**
     * Sem estrutura de codigos
     *
     * @param request
     * @param response
     */
    @Override
    public void atualizar(HttpServletRequest request, HttpServletResponse response) {
    }

    /**
     * Sem estrutura de codigos
     *
     * @param request
     * @param response
     */
    @Override
    public void excluir(HttpServletRequest request, HttpServletResponse response) {
    }

    private String validarEstoque(Venda venda) {
        String msg = "";
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produto> produtos = produtoDAO.consultarProduto(venda.getFilial().getId() + "", "FILIAL");
        for (Produto produto : produtos) {
            for (Produto produto2 : venda.getProdutos()) {
                if (produto.getId() == produto2.getId()) {
                    if (produto2.getQuantidadeNaVenda() > produto.getQuantidade()) {
                        msg += "\n" + produto2.getNome();
                    }
                }
            }
        }
        return msg;
    }
}
