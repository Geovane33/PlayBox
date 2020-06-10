package br.senac.sp.servlet;

import br.senac.sp.dao.ClienteDAO;
import br.senac.sp.dao.VendaDAO;
import br.senac.sp.entidade.Cliente;
import br.senac.sp.utils.BuilderCliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerCliente implements Controller {

    /**
     * Salva clientes
     *
     * @param request
     * @param response
     */
    @Override
    public void adicionar(HttpServletRequest request, HttpServletResponse response) {
        try {
            BuilderCliente builderCliente = new BuilderCliente(request.getParameterMap());
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = builderCliente.getObjCliente();
            PrintWriter out = response.getWriter();
            if (clienteDAO.consultar(cliente.getCpf(), "CPF").isEmpty()) {
                if (clienteDAO.salvar(cliente)) {
                    out.write("200");
                } else {
                    out.write("500");
                }

            } else {
                out.write("2");
            }
            out.flush();
            out.close();
        } catch (IOException ex) {
            System.out.println("Erro ao adicionar cliente");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
        }
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
            String consulta = request.getHeader("X-Consulta");
            String consultaTipo = request.getHeader("X-ConsultaTipo");
            PrintWriter out = response.getWriter();
            ClienteDAO clienteDAO = new ClienteDAO();
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create();

            List<Cliente> clientes = clienteDAO.consultar(consulta, consultaTipo);
            if (clientes.isEmpty()) {
                out.write("");
            } else {
                response.setContentType("application/json");
                out.write(gson.toJson(clientes));
            }

            out.flush();
            out.close();
        } catch (IOException ex) {
            System.out.println("Erro ao consultar clientes");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
        }

    }

    /**
     * atualiza clientes
     *
     * @param request
     * @param response
     */
    @Override
    public void atualizar(HttpServletRequest request, HttpServletResponse response) {

        try {
            BuilderCliente builderCliente = new BuilderCliente(request.getParameterMap());
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = builderCliente.getObjCliente();
            PrintWriter out = response.getWriter();
            if (clienteDAO.consultar(cliente.getId() + "", cliente.getCpf()).isEmpty()) {
                if (clienteDAO.atualizar(cliente)) {
                    out.write("200-2");
                } else {
                    out.write("500-2");
                }
            } else {
                out.write("2");
            }

            out.flush();
            out.close();
        } catch (IOException ex) {
            System.out.println("Erro ao atualizar cliente");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
        }

    }

    /**
     * Exclui clientes
     *
     * @param request
     * @param response
     */
    @Override
    public void excluir(HttpServletRequest request, HttpServletResponse response) {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            VendaDAO vendaDAO = new VendaDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            PrintWriter out = response.getWriter();
            if (vendaDAO.clientePossuiVenda(id).isEmpty()) {
                if (clienteDAO.excluir(id)) {
                    out.write("200");
                } else {
                    out.write("Erro ao excluir cliente");
                }
            } else {
                out.write("Erro ao excluir!\nCliente possui vendas");
            }

            out.flush();
            out.close();
        } catch (IOException ex) {
            System.out.println("Erro ao excluir cliente");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
        }
    }

}
