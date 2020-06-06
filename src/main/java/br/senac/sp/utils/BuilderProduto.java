/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.utils;

import br.senac.sp.entidade.Produto;
import java.util.Map;

/**
 *
 * @author Wellington
 */
public class BuilderProduto {

    private final Map<String, String[]> mapProdutos;

    /**
     * Pegar atributos cliente e construir um onbjeto
     *
     * @param mapCliente
     */
    public BuilderProduto(Map<String, String[]> mapCliente) {
        this.mapProdutos = mapCliente;
    }

    /**
     * Obeter um objeto produto
     * @return Produto
     */
    public Produto getObjProduto() {
        Conversor data = new Conversor();
        Produto produto = new Produto();
        
        //constroi um objeto produto com as informações obtidas no request
        produto.setId(Integer.valueOf(this.mapProdutos.get("id")[0]));
        produto.setIdFilial(Integer.valueOf(this.mapProdutos.get("idFilial")[0]));
        produto.setNome(this.mapProdutos.get("nome")[0]);
        produto.setMarca(this.mapProdutos.get("marca")[0]);
        produto.setQuantidade(Integer.valueOf(this.mapProdutos.get("qtd")[0]));
        produto.setValor(Double.parseDouble(this.mapProdutos.get("valor")[0]));
        produto.setDescricao(this.mapProdutos.get("desc")[0]);
        produto.setDataDeEntrada(data.parseData(this.mapProdutos.get("dataEnt")[0], "dd/MM/yyyy"));

        return produto;

    }

}
