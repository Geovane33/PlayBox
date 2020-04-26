/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.ProdutoDAO;
import br.senac.sp.entidade.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;



/**
 *
 * @author Jonathan
 */
public class CadastroProdutoServlet {
   // @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Empty
    }
    
   // @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if (acao.equals("salvar")) {
            String nome = request.getParameter("nome");
            String tipo = request.getParameter("tipo");
            String marca = request.getParameter("marca");
            String descricao = request.getParameter("descricao");
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            double preco = Double.parseDouble(request.getParameter("preco"));
            Date dataDeEntrada = parseData(request.getParameter("dataDeEntrada"), "dd-MM-yyyy");
            

            Produto produto = new Produto(nome, tipo, marca, descricao, quantidade, preco, dataDeEntrada);
            boolean ok = produtoDAO.salvarProduto(produto);
            PrintWriter out = response.getWriter();
            String url = "";
            if (ok) {
                url = "/sucesso.jsp";
            } else {
                url = "/erro.jsp";
            }

        } else if (acao.equals("excluir")) {
            String cpf = request.getParameter("cpf");

            
        }
        String url = null;

        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
       // dispatcher.forward(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   // @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static Date parseData(String data, String fomato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formato.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(CadastroClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
