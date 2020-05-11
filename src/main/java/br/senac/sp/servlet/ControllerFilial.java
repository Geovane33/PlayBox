package br.senac.sp.servlet;

import br.senac.sp.dao.FilialDAO;
import br.senac.sp.entidade.Filial;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerFilial implements Controller {

    ArrayList<Filial> testeFiliais = new ArrayList();
     /**
     * Sem estrutura de codigos
     * @param request
     * @param response
     */
    @Override
    public void adicionar(HttpServletRequest request, HttpServletResponse response) {
    }
    
    /**
     * Sem estrutura de codigos
     * @param request
     * @param response
     */
    @Override
    public void atualizar(HttpServletRequest request, HttpServletResponse response) {
    }
       /**
     * Sem estrutura de codigos
     * @param request
     * @param response
     */
    @Override
    public void excluir(HttpServletRequest request, HttpServletResponse response) {
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
            if (request.getMethod().equalsIgnoreCase("GET")) {
                testeFiliais = new FilialDAO().consultarFiliais("", "TODOS");
                PrintWriter out = response.getWriter();
                Gson gson = new Gson();
                response.addHeader("teste", "teste");
                response.setContentType("application/json");
                out.write(gson.toJson(testeFiliais));
                out.flush();
                out.close();
            } else {
                  //redireciona para a pagina filial
                 request.getRequestDispatcher("filiais/filiais.jsp")
                    .forward(request, response);
            }
        } catch (IOException | ServletException ex) {
            System.out.println("Erro ao consultar filiais");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());

        }
    }
}
