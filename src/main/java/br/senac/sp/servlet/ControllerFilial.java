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

    @Override
    public String adicionar(HttpServletRequest request, HttpServletResponse response) {

        return "adicionar";
    }

    @Override
    public String atualizar(HttpServletRequest request, HttpServletResponse response) {
        return "atualizar";
    }

    @Override
    public String excluir(HttpServletRequest request, HttpServletResponse response) {
        return "excluir";
    }

    @Override
    public String consultar(HttpServletRequest request, HttpServletResponse response) {
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
                testeFiliais = new FilialDAO().consultarFiliais("", "TODOS");
                String url = "filiais/filiais.jsp";
                //pesquisar filial no banco de dados correspondente ao parametro passado e devolver o id para o front
                try {
//                    PrintWriter out = response.getWriter();
           
                    request.setAttribute("filial", testeFiliais.get(Integer.parseInt(request.getParameter("filiais")) - 1).getNome());
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    request.setAttribute("filial", "filial não cadastrada");

//                    request.getRequestDispatcher("index.html")
//                            .forward(request, response);
                }
            }
            request.getRequestDispatcher("filiais/filiais.jsp")
                    .forward(request, response);

        } catch (IOException | ServletException ex) {
            System.out.println("Erro ao consultar filiais");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());

        }
        return "consultar";
    }
}
