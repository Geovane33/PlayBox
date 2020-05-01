package br.senac.sp.servlet;

import br.senac.sp.dao.ClienteDAO;
import br.senac.sp.dao.ProdutoDAO;
import br.senac.sp.dao.VendaDAO;
import br.senac.sp.entidade.Cliente;
import br.senac.sp.entidade.Filial;
import br.senac.sp.entidade.Produto;
import br.senac.sp.entidade.Venda;
import br.senac.sp.utils.Conversor;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        System.out.println("entrou");
        testeFiliais = new ArrayList<>();
        testeFiliais.add(new Filial(0,"São Paulo"));
        testeFiliais.add(new Filial(1,"Minas Gerais"));
        testeFiliais.add(new Filial(2,"Rio de Janeiro"));
        testeFiliais.add(new Filial(3,"Brasilia"));
        String url = "/filiais.jsp";
        //pesquisar filial no banco de dados correspondente ao parametro passado e devolver o id para o front
        if (!request.getParameter("filiais").equals("")) {
            request.setAttribute("filial", testeFiliais.get(Integer.parseInt(request.getParameter("filiais"))).getNome());
        } else {
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
         testeFiliais = new ArrayList<>();
        testeFiliais.add(new Filial(0,"São Paulo"));
        testeFiliais.add(new Filial(1,"Minas Gerais"));
        testeFiliais.add(new Filial(2,"Rio de Janeiro"));
        testeFiliais.add(new Filial(3,"Brasilia"));  
        
            ProdutoDAO produtoDAO = new ProdutoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        VendaDAO vendaDAO = new VendaDAO();
                
        Filial filial = new Filial(1, "SÃO PAULO");
        Filial filial2 = new Filial(2, "RIO DE JANEIRO");
        Produto produto = new Produto(1,"Notebook1", "Acer", "Notebook", 100, 2000, new Date());
        Cliente cliente = new Cliente(1, "Cliente1", "43234432", new Date(), "Masculino", "23432432", "fdsdsf@dsd", "SP", "dasdasda", "rerw", "dasdas", "fdsfsdds");
        Venda venda = new Venda();
        venda.setDataVenda(new Date());
        cliente.setId(4);
        venda.setCliente(cliente);
        venda.setFilial(filial);
        
        venda.addProduto(produto);
        
        if(!produtoDAO.salvarProduto(produto)){
            System.out.println("erro ao salvar PRODUTO");
        }
        if(!clienteDAO.salvarCliente(cliente)){
            System.out.println("erro ao salvar CLIENTE");
        }
        if(!vendaDAO.salvarVenda(venda)){
            System.out.println("erro ao salvar VENDA");
        }
        
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json;charset=UTF-8");
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
