/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.FuncionarioDAO;
import br.senac.sp.dao.UsuarioSistemaDAO;
import br.senac.sp.entidade.Funcionario;
import br.senac.sp.entidade.UsuarioSistema;
import br.senac.sp.utils.PerfilEnum;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            funcionario.setPerfil(PerfilEnum.valueOf(funcao));
            funcionario.setSenha(senha);
            funcionario.setSalario(salario);

            FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();

            UsuarioSistemaDAO usuarioSistemaDAO = new UsuarioSistemaDAO();
            List<UsuarioSistema> usuarios = usuarioSistemaDAO.consultar("", usuario);

            if (usuarios.isEmpty() && usuarios.size() < 2) {

                if (FuncionarioDAO.salvar(funcionario)) {
                    out.write("200");
                } else {
                    out.write("500");
                }

                out.flush();
                out.close();
            } else {
                out.write("422");
            }

        } catch (IOException ex) {
            System.out.println("Erro ao adicionar funcionario");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
        }
    }

    @Override
    public void consultar(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession sessao = httpRequest.getSession();
            UsuarioSistema usuario = (UsuarioSistema) sessao.getAttribute("usuario");

            List<Funcionario> funcionarios = new ArrayList<>();

            Gson gson = new Gson();

            PrintWriter out;

            out = response.getWriter();

            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            if (usuario.isAdmin()) {
                funcionarios = funcionarioDAO.consultar("", "TODOS");
            } else if (usuario.isGerente()) {
                funcionarios = funcionarioDAO.consultar(usuario.getIdFilial() + "", "ID");
            }
            if (funcionarios.isEmpty()) {
                out.write("");
            } else {
                response.setContentType("application/json");
                out.write(gson.toJson(funcionarios));
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void atualizar(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            int idFuncionario = Integer.parseInt(request.getParameter("idFuncionario"));
            int idFilial = Integer.parseInt(request.getParameter("idFilial"));
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            String funcao = request.getParameter("funcao");
            double salario = Double.parseDouble(request.getParameter("salario"));
            String nome = request.getParameter("nome");
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");

            Funcionario funcionario = new Funcionario();
            funcionario.setId(idFuncionario);
            funcionario.setIdFilial(idFilial);
            funcionario.setNome(nome);
            funcionario.setUsuario(usuario);
            funcionario.setPerfil(PerfilEnum.valueOf(funcao));
            funcionario.setSenha(senha);
            funcionario.setSalario(salario);
            funcionario.setIdUsuario(idUsuario);

            FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();

            UsuarioSistemaDAO usuarioSistemaDAO = new UsuarioSistemaDAO();
            List<UsuarioSistema> usuarios = usuarioSistemaDAO.consultar(funcionario.getId() + "", funcionario.getUsuario());

            if (usuarios.isEmpty() && usuarios.size() < 2) {
                System.out.println("entrou");
                if (FuncionarioDAO.atualizar(funcionario)) {
                    out.write("200");
                } else {
                    out.write("500");
                }

                out.flush();
                out.close();
            } else {
                out.write("422");
            }

        } catch (IOException ex) {
            System.out.println("Erro ao atualiazr funcionario");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
        }

    }

    @Override
    public void excluir(HttpServletRequest request, HttpServletResponse response) {
        try {
            UsuarioSistemaDAO usuarioSistemaDAO = new UsuarioSistemaDAO();
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            PrintWriter out = response.getWriter();
            //apenas deleto o usuario ligado ao funcionario pois esta tabela faz delete em forma de castata assim remove os usuario e o funcionario do sistema
            if (usuarioSistemaDAO.excluir(idUsuario)) {
                out.write("200");
            } else {
                out.write("Erro ao excluir funcionario");
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
