package br.senac.sp.servlet;

import br.senac.sp.dao.FilialDAO;
import br.senac.sp.entidade.Filial;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerFiliais extends HttpServlet {
    //substituir por consulta ao banco de dados

    ArrayList<Filial> testeFiliais = new ArrayList();

    /**
     *
     * @param request - HttpServletRequest
     * @param response - HttpServletResponse
     * @throws ServletException - checar tratamento
     * @throws IOException - checar tratamento
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        testeFiliais = new FilialDAO().consultarFiliais("", "todos");
        String url = "filiais/filiais.jsp";
        //pesquisar filial no banco de dados correspondente ao parametro passado e devolver o id para o front
        try{
            request.setAttribute("filial", testeFiliais.get(Integer.parseInt(request.getParameter("filiais"))-1).getNome());
        }catch(NumberFormatException | IndexOutOfBoundsException e){
            request.setAttribute("filial", "filial não cadastrada");
            url = "index.html";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.include(request, response);
    }

    /**
     *
     * @param request - HttpServletRequest
     * @param response - HttpServletResponse
     * @throws ServletException - checar tratamento
     * @throws IOException - checar tratamento
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        testeFiliais = new FilialDAO().consultarFiliais("", "todos");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        out.write(gson.toJson(testeFiliais));
        out.flush();
        out.close();
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
