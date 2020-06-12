/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.ProdutoDAO;
import br.senac.sp.entidade.Produto;
import br.senac.sp.entidade.UsuarioSistema;
import br.senac.sp.utils.BuilderProduto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

            if (produtoDAO.salvar(produto)) {
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
            if (true) {
                List<Produto> produtos = produtoDAO.consultar(idFilial, "FILIAL");
                if (produtos.isEmpty()) {
                    out.write("");
                } else {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(gson.toJson(produtos));
                }
            } else {
                out.write("403");
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

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession sessao = httpRequest.getSession();
        UsuarioSistema usuario = (UsuarioSistema) sessao.getAttribute("usuario");

        try {
            BuilderProduto builderProduto = new BuilderProduto(request.getParameterMap());
            Produto produto = builderProduto.getObjProduto();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            PrintWriter out = response.getWriter();
            if (usuario.isAdmin()) {
                if (produtoDAO.atualizar(produto)) {
                    out.write("Produto atualizado com sucesso");
                } else {
                    out.write("erro ao atualizar produto");
                }
            } else {
                out.write("403");
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
    public void excluir(HttpServletRequest request, HttpServletResponse response) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            PrintWriter out = response.getWriter();
            List<Produto> produtos = produtoDAO.consultar(id + "", "venda");
            if (produtos.isEmpty()) {
                if (produtoDAO.excluir(id)) {
                    out.write("Produto excluido com sucesso");
                } else {
                    out.write("erro ao excluir produto");
                }
            } else {
                Produto p = produtos.get(0);
                p.setQuantidade(0);
                if (produtoDAO.atualizar(p)) {
                    out.write("Produto excluido com sucesso");
                } else {
                    out.write("Erro ao excluir produto, verifique se o produto está em venda pendente");
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
