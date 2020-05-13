/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.RelatorioDAO;
import br.senac.sp.entidade.Venda;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geovane
 */
public class ControllerRelatorio implements Controller {

    /**
     * Gerar ralatório
     *
     * @param request
     * @param response
     */
    @Override
    public void consultar(HttpServletRequest request, HttpServletResponse response) {
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        List<Venda> relatorio = new ArrayList();
        Gson gson = new Gson();
        try {
            PrintWriter out = response.getWriter();
            try {
                int idFilial = Integer.parseInt(request.getParameter("idFilial"));
               relatorio = relatorioDAO.obterRalatorio(idFilial);
            } catch (NumberFormatException ex) {
                System.out.println("Erro em obter filial ao gerar relatorio");
                System.out.println("Message: " + ex.getMessage());
                System.out.println("Class: " + ex.getClass());

            }
            if (relatorio.isEmpty()) {
                out.write("");
            } else {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(gson.toJson(relatorio));
            }
        } catch (IOException ex) {
            System.out.println("Erro ao enviar relatorio");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());

        }
    }

    @Override
    public void adicionar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
