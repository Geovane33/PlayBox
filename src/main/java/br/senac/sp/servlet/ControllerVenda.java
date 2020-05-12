/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.VendaDAO;
import br.senac.sp.entidade.Venda;
import com.google.gson.Gson;
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
        Gson gson = new Gson();
        VendaDAO vendaDAO = new VendaDAO();
        Venda venda = gson.fromJson(request.getHeader("X-Venda"), Venda.class);
        venda.setDataVenda(new Date());
        vendaDAO.salvarVenda(venda);
    }

    @Override
    public void atualizar(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void excluir(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void consultar(HttpServletRequest request, HttpServletResponse response) {
    }

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
//    }
}
