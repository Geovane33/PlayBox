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
public class Filial {
    private int id;
    private String nome;
    private String estado;
    
    /**
     *Contrutor vazio
     */
    public Filial(){
        
    }

    /**
     * Construtor para salvar uma nova filial
     * @param id
     * @param nome
     * @param estado
     */
    public Filial(int id, String nome, String estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
    
    /**
     *Contrutor para atualizar os dados de uma determinada filial
     * @param nome
     * @param estado
     */
    public Filial(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param nome nome para set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param id id para set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado estado para set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
    
}
