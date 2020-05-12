/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.utils;

import br.senac.sp.entidade.Produto;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Wellington
 */
public class BuilderProduto {

    public BuilderProduto() {
    }

    /**
     *
     * @param request
     * @return
     */
    public Produto getObjProduto(HttpServletRequest request) {

        Conversor data = new Conversor();
        int id = Integer.parseInt(request.getParameter("id"));
        int idFilial = Integer.parseInt(request.getParameter("idFilial"));
        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        String descricao = request.getParameter("desc");
        int quantidade = Integer.parseInt(request.getParameter("qtd"));
        double valor = Double.parseDouble(request.getParameter("valor"));
        Date dataDeEntrada = data.parseData(request.getParameter("dataEnt"), "dd/MM/yyyy");
        
        //constroi um objeto produto com as informações obtidas no request
        Produto produto = new Produto(id, idFilial, nome, marca, descricao, quantidade, valor, dataDeEntrada);

        return produto;

    }

}
