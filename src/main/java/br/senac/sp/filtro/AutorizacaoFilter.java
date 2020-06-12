/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.filtro;

import br.senac.sp.entidade.UsuarioSistema;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutorizacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 1) Verifica se usuario já está logado
        HttpSession sessao = httpRequest.getSession();
        if (sessao.getAttribute("usuario") == null) {
            // Usuario nao esta logado -> redirecionar para tela de login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        // 2) Usuario esta logado -> Verifica se tem papel necessario para acesso
        UsuarioSistema usuario = (UsuarioSistema) sessao.getAttribute("usuario");
        if (verificarAcesso(usuario, httpRequest)) {
            // Usuario tem permissao de acesso -> Requisição pode seguir para servlet
            chain.doFilter(request, response);

        } else {
            // Mostra erro de acesso não autorizado
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/NaoAutorizado.jsp");
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    private boolean verificarAcesso(UsuarioSistema usuario, HttpServletRequest httpRequest) {
        String urlAcessada = httpRequest.getRequestURI();
        if (urlAcessada.contains("/protegido/")) {
            if (usuario.isAdmin()  || usuario.isGerente()) {
                //caso o usuario for gerente ele terá acesso a todas as tela de apenas uma filial, caso seja admin terá acesso a tudo
                return true;
            } else if (usuario.isVendedor()) {

                //caso o usuario for vendedor ele terá acesso a tela de cliente e vendas apenas se ele tentar acessa uma das telas abaixo será negado

               

                return !(urlAcessada.contains("/protegido/CadastraProduto/") || urlAcessada.contains("/protegido/relatorios/"));
            }
        }

        return true;
    }

}
