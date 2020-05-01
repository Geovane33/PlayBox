package br.senac.sp.servlet;

import br.senac.sp.dao.ClienteDAO;
import br.senac.sp.entidade.Cliente;
import br.senac.sp.utils.Conversor;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroClienteServlet extends HttpServlet {

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

        PrintWriter out = response.getWriter();
        ClienteDAO clienteDAO = new ClienteDAO();
        Gson gson = new Gson();
        try{
        int id = Integer.parseInt(request.getParameter("id"));
        clienteDAO.excluirCliente(id);
        }catch(Exception e){
            e.getMessage();
        }
        
        List<Cliente> clientes = clienteDAO.consultarCliente("", "TODOS");
        request.setAttribute("cliente", gson.toJson(clientes));
        response.setContentType("application/json");
        out.write(gson.toJson(clientes));
        out.flush();
        out.close();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/consultarClientes2.jsp");
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
        String url = "";
        String acao = request.getParameter("acao");
        ClienteDAO clienteDAO = new ClienteDAO();
         Conversor data = new Conversor();
        if (acao.equals("salvar")) {
            String nome = request.getParameter("nome");
            Date dataNascimento = data.parseData(request.getParameter("dataNascimento"), "dd-MM-yyyy");
            String sexo = request.getParameter("sexo");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String cpf = request.getParameter("cpf");
            String cep = request.getParameter("cep");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            String bairro = request.getParameter("bairro");
            String numero = request.getParameter("numero");
            int idFilial =  Integer.parseInt(request.getParameter("idFilial"));

            Cliente cliente = new Cliente(idFilial, nome, cpf, dataNascimento, sexo, telefone, email, uf, cidade, cep, bairro, numero);
            boolean ok = clienteDAO.salvarCliente(cliente);
            PrintWriter out = response.getWriter();
            if (ok) {
                url = "/sucesso.jsp";
            } else {
                url = "/erro.jsp";
            }

        } else if (acao.equals("excluir")) {
            try{
        int id = Integer.parseInt(request.getParameter("id"));
        clienteDAO.excluirCliente(id);
        }catch(NumberFormatException e){
            e.getMessage();
        }
        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
