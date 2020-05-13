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
    private int id;
    private Date dataVenda;
    private double total;
    private Cliente cliente;
    private Filial filial;
    private List<Produto> produtos = new ArrayList<>();
    private double totalDaVenda;
    
    
    public double calculaTotal() {
        return 0;
    }
}
