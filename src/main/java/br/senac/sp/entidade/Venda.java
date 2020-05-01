/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Wellington
 */
public class Venda {
    private int id;
    private Date dataVenda;
    private double total;
    private Cliente cliente;
    private Filial filial;
    private List<Produto> produtos = new ArrayList<>();
    
 
    public void addProduto(Produto produto){
        int index = produtos.indexOf(produto);
        if(index == -1){ //Produto ainda não está na lista, então adiciona.
            produtos.add(new Produto(produto));
        }else{ //Produto já está na lista, só atualiza a quantidade.
            Produto encontrado = produtos.get(index);
            int quantidadeAtual = encontrado.getQuantidadeNaVenda();
            int quantidadeAtualizada = quantidadeAtual + produto.getQuantidadeNaVenda();
            encontrado.setQuantidadeNaVenda(quantidadeAtualizada);
        }
        total = calculaTotal();
    }
    
    private double calculaTotal(){
        double soma = 0;
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            soma = soma + (produto.getValor() * produto.getQuantidadeNaVenda());
        }
        return soma;
    }
    
    public void removerProduto(Produto produto){
        produtos.remove(produto);
        total = calculaTotal();
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id para set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return dataVenda
     */
    public Date getDataVenda() {
        return dataVenda;
    }

    /**
     * @param dataVenda dataVenda para set
     */
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    /**
     * @return total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total total para set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente cliente para set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return filial
     */
    public Filial getFilial() {
        return filial;
    }

    /**
     * @param filial filial para set
     */
    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    /**
     * @return produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos produtos para set
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
