package br.senac.sp.servlet;

import br.senac.sp.dao.ClienteDAO;
import br.senac.sp.entidade.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Empty
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        ClienteDAO clienteDAO = new ClienteDAO();
        if (acao.equals("salvar")) {
            String nome = request.getParameter("nome");
            Date dataNascimento = parseData(request.getParameter("dataNascimento"), "dd-MM-yyyy");
            String sexo = request.getParameter("sexo");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String cpf = request.getParameter("cpf");
            String cep = request.getParameter("cep");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            String bairro = request.getParameter("bairro");
            String numero = request.getParameter("numero");

            Cliente cliente = new Cliente(nome, dataNascimento, sexo, telefone, email, cpf, cep, cidade, uf, bairro, numero);
            boolean ok = clienteDAO.salvarCliente(cliente);
            PrintWriter out = response.getWriter();
            String url = "";
            if (ok) {
                url = "/sucesso.jsp";
            } else {
                url = "/erro.jsp";
            }

        } else if (acao.equals("excluir")) {
            String cpf = request.getParameter("cpf");

            
        }
        String url = null;

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

    private static Date parseData(String data, String fomato) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formato.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(CadastroClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
