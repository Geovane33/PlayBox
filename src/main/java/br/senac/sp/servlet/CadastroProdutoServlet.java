/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.ProdutoDAO;
import br.senac.sp.entidade.Produto;
import br.senac.sp.utils.Conversor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonathan
 */
public class CadastroProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Empty
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Conversor data = new Conversor();
        String acao = request.getParameter("acao");
        String url = "";
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if (acao.equals("Cadastrar")) {
            String nome = request.getParameter("nome");
            String tipo = request.getParameter("tipo");
            String marca = request.getParameter("marca");
            String descricao = request.getParameter("desc");
            int quantidade = Integer.parseInt(request.getParameter("qtd"));
            double preco = Double.parseDouble(request.getParameter("preco"));
            Date dataDeEntrada = data.parseData(request.getParameter("dataEnt"), "dd-MM-yyyy");
            
             int idFilial = Integer.parseInt(request.getParameter("idFilial"));

            Produto produto = new Produto(nome, tipo, marca, descricao, quantidade, preco, dataDeEntrada, idFilial);
            boolean ok = produtoDAO.salvarProduto(produto);
            PrintWriter out = response.getWriter();

            if (ok) {
                url = "/sucesso.jsp";
            } else {
                url = "/erro.jsp";
            }

        } else if (acao.equals("excluir")) {
            int id = Integer.parseInt(request.getParameter("id"));
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
