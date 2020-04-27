/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.entidade;

/**
 *
 * @author Geovane
 */
public class Filiais {
    private static int qtdFiliais;
    
    private int id;
    private String nome; 

    public Filiais(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
    
}




