/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.entidade.UsuarioSistema;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geovane
 */
@WebServlet(name = "telas", urlPatterns = {"/telas"})
public class ControllerTela extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession sessao = httpRequest.getSession();
        UsuarioSistema usuario = (UsuarioSistema) sessao.getAttribute("usuario");
        List<Boolean> telas = new ArrayList<>();
        Gson gson = new Gson();
        
        PrintWriter out = response.getWriter();
        if (usuario.isAdmin() || usuario.isGerente()) {
            //cliente
            telas.add(Boolean.TRUE);
            //produto
            telas.add(Boolean.TRUE);
            //vendas
            telas.add(Boolean.TRUE);
            //relatorios
            telas.add(Boolean.TRUE);
        }
        if (usuario.isVendedor()) {
            //cliente
            telas.add(Boolean.TRUE);
            //produto
            telas.add(Boolean.FALSE);
            //vendas
            telas.add(Boolean.TRUE);
            //relatorios
            telas.add(Boolean.FALSE);
        }
        out.print(gson.toJson(telas));
    }
}
