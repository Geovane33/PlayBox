package br.senac.sp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

    /**
     * Adicionar um novo objeto
     * @param request
     * @param response
     */
    void adicionar(HttpServletRequest request, HttpServletResponse response);

    /**
     * Atualiza informações
     * @param request
     * @param response
     */
    void atualizar(HttpServletRequest request, HttpServletResponse response);

    /**
     * Exclui um objeto
     * @param request
     * @param response
     */
    void excluir(HttpServletRequest request, HttpServletResponse response);

    /**
     * Realiza consultas
     * @param request
     * @param response
     */
    void consultar(HttpServletRequest request, HttpServletResponse response);
}
