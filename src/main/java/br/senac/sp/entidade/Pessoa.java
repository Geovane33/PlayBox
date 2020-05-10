/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.entidade;

import java.util.Date;

/**
 * pessoa
 */
public abstract class Pessoa {

    //atributos privados(encapsulados)
    private int id;
    private int idFilial;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String sexo;
    private String telefone;
    private String email;
    private String uf;
    private String cidade;
    private String cep;
    private String bairro;
    private String numero;

    /**
     * Contrutos vazio
     */
    public Pessoa() {
    }

    /**
     * Construtor que recebe por paramentro todos os atributos de pessoa
     *
     * @param idFilial int
     * @param nome String
     * @param cpf String
     * @param dataNascimento Date
     * @param sexo String
     * @param telefone String
     * @param email String
     * @param uf String
     * @param cep String
     * @param cidade String
     * @param bairro String
     * @param numero String
     */
    public Pessoa(int idFilial, String nome, String cpf, Date dataNascimento, String sexo, String telefone, String email, String uf, String cidade, String cep, String bairro, String numero) {
        this.idFilial = idFilial;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.uf = uf;
        this.cidade = cidade;
        this.cep = cep;
        this.bairro = bairro;
        this.numero = numero;
    }

    //Abaixo metodos getters e setters
    /**
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param id id para set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return idFilial
     */
    public int getIdFilial() {
        return this.idFilial;
    }

    /**
     * @param idFilial idFilial para set
     */
    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    /**
     * @return nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @param nome nome para set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return cpf
     */
    public String getCpf() {
        return this.cpf;
    }

    /**
     * @param cpf cpf para set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return dataNascimento
     */
    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    /**
     * @param dataNascimento dataNascimento para set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return sexo
     */
    public String getSexo() {
        return this.sexo;
    }

    /**
     * @param sexo sexo para set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return telefone
     */
    public String getTelefone() {
        return this.telefone;
    }

    /**
     * @param telefone telefone para set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email email para set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return uf
     */
    public String getUf() {
        return this.uf;
    }

    /**
     * @param uf uf para set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return cidade
     */
    public String getCidade() {
        return this.cidade;
    }

    /**
     * @param cidade cidade para set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return cep
     */
    public String getCep() {
        return this.cep;
    }

    /**
     * @param cep cep para set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return bairro
     */
    public String getBairro() {
        return this.bairro;
    }

    /**
     * @param bairro bairro para set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return numero
     */
    public String getNumero() {
        return this.numero;
    }

    /**
     * @param numero numero para set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

}
