/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.entidade;

import java.util.Date;

/**
 *
 * @author Geovane
 */
public class Funcionario extends UsuarioSistema{
   private String funcao;
   private double salario;

    /**
     * Construtor funcionário
     * @param funcao
     * @param salario
     * @param idFilial
     * @param nome
     * @param cpf
     * @param dataNascimento
     * @param sexo
     * @param telefone
     * @param email
     * @param uf
     * @param cidade
     * @param cep
     * @param bairro
     * @param numero
     */
    public Funcionario(String funcao, long salario, int idFilial, String nome, String cpf, Date dataNascimento, String sexo, String telefone, String email, String uf, String cidade, String cep, String bairro, String numero) {
        super(idFilial, nome, cpf, dataNascimento, sexo, telefone, email, uf, cidade, cep, bairro, numero);
        this.funcao = funcao;
        this.salario = salario;
    }

    /**
     * Obter função
     * @return string - função do funcionario
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * setar função do funcionario
     * @param funcao
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    /**
     * Obter salario do funcionario
     * @return double salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * setar salario do funcionario
     * @param salario 
     */
    public void setSalario(long salario) {
        this.salario = salario;
    }
}

