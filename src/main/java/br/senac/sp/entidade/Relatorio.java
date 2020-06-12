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
 * @author Geovane
 */
public class Relatorio {

    //atributos privados
    private int qtdVenda;
    private double total;
    private Filial filial;

    public int getQtdVenda() {
        return qtdVenda;
    }

    public void setQtdVenda(int qtdVenda) {
        this.qtdVenda = qtdVenda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(List<Venda> vendas) {
        List<Double> teste;
        for (Venda venda : vendas) {
            venda.getId();
        }
        this.total = total;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
    
}
