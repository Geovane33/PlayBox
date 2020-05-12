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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
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
            BuilderProduto builderProduto = new BuilderProduto();
            Produto produto = builderProduto.getObjProduto(request);
            PrintWriter out = response.getWriter();
            if (produtoDAO.salvarProduto(produto)) {
                out.write("produto cadastrado com sucesso");
                request.getRequestDispatcher("/sucesso.jsp")
                        .forward(request, response);
            } else {
                out.write("erro ao cadastrar produto");
            }

        } catch (IOException | ServletException ex) {
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
            BuilderProduto builderProduto = new BuilderProduto();
            Produto produto = builderProduto.getObjProduto(request);

            ProdutoDAO produtoDAO = new ProdutoDAO();

            PrintWriter out = response.getWriter();
            if (produtoDAO.atualizarProduto(produto)) {
                out.write("produto cadastrado com sucesso");
                request.getRequestDispatcher("sucesso.jsp")
                    .forward(request, response);
            } else {
                out.write("erro ao cadastrar produto");
            }

            

        } catch (IOException | NumberFormatException | ServletException ex) {
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
    public void excluir(HttpServletRequest request, HttpServletResponse response) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            PrintWriter out = response.getWriter();
            if (produtoDAO.excluirProduto(id)) {
                out.write("produto cadastrado com sucesso");
            } else {
                out.write("erro ao cadastrar produto");
            }

        } catch (IOException | NumberFormatException ex) {
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
    public void consultar(HttpServletRequest request, HttpServletResponse response) {

        try {
            PrintWriter out = response.getWriter();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Gson gson = new Gson();

            //String idFilial = request.getParameter("idFilial"); //trocar o 1 pelo id
            List<Produto> produtos = produtoDAO.consultarProduto("1", "FILIAL");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.write(gson.toJson(produtos));
            out.flush();
            out.close();

        } catch (IOException ex) {
            System.out.println("Erro ao consultar produtos");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());

        }
    }
}
