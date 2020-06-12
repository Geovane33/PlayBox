package br.senac.sp.servlet;

import br.senac.sp.dao.FilialDAO;
import br.senac.sp.entidade.Filial;
import br.senac.sp.entidade.UsuarioSistema;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControllerFilial implements Controller {

    /**
     * Realiza consultas
     *
     * @param request
     * @param response
     */
    @Override
    public void consultar(HttpServletRequest request, HttpServletResponse response) {
        List<Filial> filiais;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession sessao = httpRequest.getSession();
        UsuarioSistema usuario = (UsuarioSistema) sessao.getAttribute("usuario");
        try {

            if (request.getMethod().equalsIgnoreCase("GET")) {
                if (usuario.isAdmin()) {
                    filiais = new FilialDAO().consultar("", "TODOS");
                    filiais.add(new Filial(0, "RELATÓRIO GERAL", ""));
                } else{
                     filiais = new FilialDAO().consultar(usuario.getIdFIlial()+"","ID");
                }
                PrintWriter out = response.getWriter();
                Gson gson = new Gson();
                response.setContentType("application/json");
                out.write(gson.toJson(filiais));
                out.flush();
                out.close();
            } else {
                //redireciona para a pagina filial
                request.getRequestDispatcher("protegido/filiais/filiais.jsp")
                        .forward(request, response);
            }

        } catch (IOException | ServletException ex) {
            System.out.println("Erro ao consultar filiais");
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
