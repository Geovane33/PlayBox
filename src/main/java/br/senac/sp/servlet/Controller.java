package br.senac.sp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    void adicionar(HttpServletRequest request, HttpServletResponse response);
    void consultar(HttpServletRequest request, HttpServletResponse response);
    void atualizar(HttpServletRequest request, HttpServletResponse response);
    void excluir(HttpServletRequest request, HttpServletResponse response);
}
