package br.senac.sp.entidade;

import java.util.Date;

public class Produto {

    //só para a aplicação
    private int quantidadeNaVenda;

    //encapsulamento dos atributos
    private int id;
    private String nome;
    private String tipo;
    private String marca;
    private String descricao;
    private int quantidade;
    private double preco;
    private Date dataDeEntrada;

    //construtor vazio
    public Produto() {
    }

    //construtor sem ID como parâmentro
    public Produto(String nome, String tipo, String marca, String descricao, int quantidade, double preco, Date dataDeEntrada) {
        this.nome = nome;
        this.tipo = tipo;
        this.marca = marca;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.dataDeEntrada = dataDeEntrada;
    }

    //construtor com ID como parâmentro
    public Produto(int id, String nome, String tipo, String marca, String descricao, int quantidade, double preco, Date dataDeEntrada) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.marca = marca;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.dataDeEntrada = dataDeEntrada;
    }

    //Métodos acessores
    public int getQuantidadeNaVenda() {
        return quantidadeNaVenda;
    }

    public void setQuantidadeNaVenda(int quantidadeNaVenda) {
        this.quantidadeNaVenda = quantidadeNaVenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataDeEntrada() {
        return dataDeEntrada;
    }

    public void setDataDeEntrada(Date dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }

}
