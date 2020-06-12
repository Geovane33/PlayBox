/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.RelatorioDAO;
import br.senac.sp.entidade.UsuarioSistema;
import br.senac.sp.entidade.Venda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        Gson gson = new GsonBuilder()
                .setDateFormat("dd/MM/yyyy")
                .create();

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession sessao = httpRequest.getSession();
        UsuarioSistema usuario = (UsuarioSistema) sessao.getAttribute("usuario");

        try {
            PrintWriter out = response.getWriter();
            try {
                int idFilial = Integer.parseInt(request.getParameter("idFilial"));
                if (usuario.isAdmin() || usuario.isGerente()) {
                    relatorio = relatorioDAO.consultar(idFilial);
                } else {
                    //vendedor e estoquista não tem permissão para ver relatorio
                      out.write("403");
                }

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

    /**
     * Sem estrutura de codigos
     *
     * @param request
     * @param response
     */
    @Override
    public void adicionar(HttpServletRequest request, HttpServletResponse response) {
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

}
