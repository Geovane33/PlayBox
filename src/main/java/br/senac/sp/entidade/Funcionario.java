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
public class Funcionario extends Pessoa{
   private String funcao;
   private double salario;

    public Funcionario(String funcao, long salario) {
        this.funcao = funcao;
        this.salario = salario;
    }

    public Funcionario(String funcao, long salario, int idFilial, String nome, String cpf, Date dataNascimento, String sexo, String telefone, String email, String uf, String cidade, String cep, String bairro, String numero) {
        super(idFilial, nome, cpf, dataNascimento, sexo, telefone, email, uf, cidade, cep, bairro, numero);
        this.funcao = funcao;
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(long salario) {
        this.salario = salario;
    }
   
}

