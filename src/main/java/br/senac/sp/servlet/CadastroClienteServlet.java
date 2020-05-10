package br.senac.sp.servlet;

import br.senac.sp.dao.ClienteDAO;
import br.senac.sp.entidade.Cliente;
import br.senac.sp.utils.Conversor;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
        String consulta = request.getHeader("X-Consulta");
        String consultaTipo = request.getHeader("X-ConsultaTipo");
        //apenas para testes
//        Enumeration headerNames = request.getHeaderNames();
//	List<String> lHeaderN = new ArrayList<String>(); 
//	String headerNamesForHtml= new String();
//	
//	while (headerNames.hasMoreElements()) {
//		String HeaderName = (String)headerNames.nextElement();
//		lHeaderN.add(HeaderName+"="+request.getHeader(HeaderName));
//		headerNamesForHtml += (String)headerNames.nextElement() + "=" + request.getHeader(HeaderName)+"<br/>";
//	}
//	System.out.println(lHeaderN.toString());
        PrintWriter out = response.getWriter();
        ClienteDAO clienteDAO = new ClienteDAO();
        Gson gson = new Gson();
        if (request.getParameter("acao").equals("excluir")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                clienteDAO.excluirCliente(id);
            } catch (Exception e) {
                e.getMessage();
            }

        } else {
            List<Cliente> clientes = clienteDAO.consultarCliente(consulta, consultaTipo);
            response.setContentType("application/json");
            out.write(gson.toJson(clientes));
            out.flush();
            out.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroCliente/cadastroCliente.jsp");
            dispatcher.include(request, response);
        }
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
        request.setCharacterEncoding("UTF-8");
        String url = "";
        String acao = request.getParameter("acao");
        ClienteDAO clienteDAO = new ClienteDAO();
        Conversor data = new Conversor();
        if (acao.equals("salvar") || acao.equals("atualizar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int idFilial = Integer.parseInt(request.getParameter("idFilial"));
            String nome = request.getParameter("nome");
            Date dataNascimento = data.parseData(request.getParameter("nascimento"), "dd/MM/yyyy");
            String sexo = request.getParameter("sexo");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String cpf = request.getParameter("CPF");
            String cep = request.getParameter("CEP");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            String bairro = request.getParameter("bairro");
            String numero = request.getParameter("numero");
            boolean ok;
            Cliente cliente = new Cliente(idFilial, nome, cpf, dataNascimento, sexo, telefone, email, uf, cidade, cep, bairro, numero);
            if (acao.equals("salvar")) {
                ok = clienteDAO.salvarCliente(cliente);
            } else {
                cliente.setId(id);
                ok = clienteDAO.atualizarCliente(cliente);
            }
            PrintWriter out = response.getWriter();
            if (ok) {
                url = "/sucesso.jsp";
            } else {
                url = "/erro.jsp";
            }

        } else if (acao.equals("excluir")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                clienteDAO.excluirCliente(id);
            } catch (NumberFormatException e) {
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
