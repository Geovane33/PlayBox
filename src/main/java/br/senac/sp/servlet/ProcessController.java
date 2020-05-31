/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geovane
 */
@WebServlet(name = "notestore", urlPatterns = {"/notestore"})
public class ProcessController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {

        String acao = request.getParameter("acao").toLowerCase();
        String controller = request.getParameter("controller");

        String nomeClasse = "br.senac.sp.servlet.Controller" + controller;
        try {
            // Cria uma instância(classe) com o nome da classe passada no request
            Class<?> classe = Class.forName(nomeClasse);

            //instancia a classe implementando a interface ControllerLogica
            Controller controlador = (Controller) classe.newInstance();

            //parametros para o metodo a ser chamado
            Class<?> paramTypes[] = {
                HttpServletRequest.class, HttpServletResponse.class};

            // chama o método de forma dinamica
            Method metodo = controlador.getClass().getMethod(acao, paramTypes);

            //executa o metodo da classe correspondente ao recebido do front
            metodo.invoke(controlador, request, response);

        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | InvocationTargetException ex) {
            System.out.println("Erro no ProcessCotroller");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
        }
    }
}
