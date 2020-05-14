/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.ProdutoDAO;
import br.senac.sp.entidade.Produto;
import br.senac.sp.utils.BuilderProduto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonathan
 */
public class ControllerProduto implements Controller {

    /**
     * Sem estrutura de codigos
     *
     * @param request
     * @param response
     */
    @Override
    public void adicionar(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            BuilderProduto builderProduto = new BuilderProduto(request.getParameterMap());
            Produto produto = builderProduto.getObjProduto();

            PrintWriter out = response.getWriter();

            if (produtoDAO.salvarProduto(produto)) {
                out.write("produto cadastrado com sucesso");
            } else {
                out.write("erro no servidor ao cadastrar produto");
            }
            out.flush();
            out.close();

        } catch (IOException ex) {
            System.out.println("Erro ao consultar produtos");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
        }
    }

    /**
     * Realiza consultas
     *
     * @param request
     * @param response
     */
    @Override
    public void consultar(HttpServletRequest request, HttpServletResponse response
    ) {

        try {
            PrintWriter out = response.getWriter();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create();
            String idFilial = request.getParameter("idFilial");
            List<Produto> produtos = produtoDAO.consultarProduto(idFilial, "FILIAL");
            if (produtos.isEmpty()) {
                out.write("");
            } else {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(gson.toJson(produtos));
            }
            out.flush();
            out.close();

        } catch (IOException ex) {
            System.out.println("Erro ao consultar produtos");
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
    public void atualizar(HttpServletRequest request, HttpServletResponse response) {
        try {
            BuilderProduto builderProduto = new BuilderProduto(request.getParameterMap());
            Produto produto = builderProduto.getObjProduto();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            PrintWriter out = response.getWriter();

            if (produtoDAO.atualizarProduto(produto)) {
                out.write("Produto atualizado com sucesso");
            } else {
                out.write("erro ao atualizar produto");
            }
            out.flush();
            out.close();
        } catch (IOException ex) {
            System.out.println("Erro ao atualizar produtos");
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
    public void excluir(HttpServletRequest request, HttpServletResponse response
    ) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            PrintWriter out = response.getWriter();
            List<Produto> produtos = produtoDAO.consultarProduto(id + "", "venda");
            if (produtos.isEmpty()) {
                if (produtoDAO.excluirProduto(id)) {
                    out.write("Produto excluido com sucesso");
                } else {
                    out.write("erro ao excluir produto");
                }
            } else {
                Produto p = produtos.get(0);
                p.setQuantidade(0);
                if (produtoDAO.atualizarProduto(p)) {
                    out.write("Produto excluido com sucesso");
                } else {
                    out.write("Erro ao excluir produto, verifique se esta em venda ativa");
                }
            }
            out.flush();
            out.close();

        } catch (IOException | NumberFormatException ex) {
            System.out.println("Erro ao excluir produtos");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
        }
    }

}
