///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package br.senac.sp.servlet;
//
//import br.senac.sp.dao.VendaDAO;
//import br.senac.sp.entidade.Produto;
//import br.senac.sp.entidade.Venda;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Date;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author Wellington
// */
//public class VendaServlet implements ControllerLogica {
//
//    @Override
//    public String adicionar(HttpServletRequest request, HttpServletResponse response) {
//        return "adicionar";
//    }
//
//    @Override
//    public String atualizar(HttpServletRequest request, HttpServletResponse response) {
//        return "atualizar";
//    }
//
//    @Override
//    public String excluir(HttpServletRequest request, HttpServletResponse response) {
//        return "excluir";
//    }
//
//    @Override
//    public String consultar(HttpServletRequest request, HttpServletResponse response) {
//        return "consultar";
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String acao = request.getParameter("acao");
//        VendaDAO vendaDAO = new VendaDAO();
//        if (acao.equals("salvar")) {
//            String cliente = request.getParameter("cliente");
//            String produto = request.getParameter("produtos");
//            String quantidade = request.getParameter("quantidadeNaVenda");
//
//            Venda venda = new Venda();
//
//            //venda.addProduto(new Produto("notebook", "produto", "acer", "notebook gamer", 10, 5000, new Date(), 0));
//            boolean ok = true;
//            PrintWriter out = response.getWriter();
//            String url = "";
//            if (ok) {
//                url = "/sucesso.jsp";
//            } else {
//                url = "/erro.jsp";
//            }
//
////        } else if (acao.equals("excluir")) {
////            String cpf = request.getParameter("cpf");
////
////            
////        }
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//            dispatcher.forward(request, response);
//        }
//
//    }
//}
