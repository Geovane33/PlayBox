/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.FuncionarioDAO;
import br.senac.sp.entidade.Funcionario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geovane
 */
public class ControllerFuncionario implements Controller {

    @Override
    public void adicionar(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            int idFilial = Integer.parseInt(request.getParameter("idFilial"));
            String funcao = request.getParameter("funcao");
            double salario = Double.parseDouble(request.getParameter("salario"));
            String nome = request.getParameter("nome");
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFilial(idFilial);
            funcionario.setNome(nome);
            funcionario.setUsuario(usuario);
            funcionario.setFuncao(funcao);
            funcionario.setSenha(senha);
            funcionario.setSalario(salario);
            FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();
            if (FuncionarioDAO.salvar(funcionario)) {
                out.write("200");
            } else {
                out.write("500");
            }

            out.flush();
            out.close();
        } catch (IOException ex) {
            System.out.println("Erro ao adicionar cliente");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
        }
    }

    @Override
    public void consultar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void excluir(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
