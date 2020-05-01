package br.senac.sp.entidade;

import java.util.Date;

public class Produto {

    //só para a aplicação
    public int quantidadeNaVenda;

    //encapsulamento dos atributos
    private int id;
    private int idFilial;
    private String nome;
    private String marca;
    private int quantidade;
    private double valor;
    private String descricao;
    private Date dataDeEntrada;

    /**
     * construtor vazio
     */
    public Produto() {
    }

    /**
     * construtor para cadastrar produto
     *
     * @param idFilial int
     * @param nome String
     * @param marca String
     * @param descricao String
     * @param quantidade int
     * @param preco double
     * @param dataDeEntrada Date
     *
     */
    public Produto(int idFilial, String nome, String marca, String descricao, int quantidade, double preco, Date dataDeEntrada) {
        this.idFilial = idFilial;
        this.nome = nome;
        this.marca = marca;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = preco;
        this.dataDeEntrada = dataDeEntrada;
    }

    /**
     * construtor para atualizar dados do produto
     *
     * @param nome String
     * @param marca String
     * @param descricao String
     * @param quantidade int
     * @param preco double
     * @param dataDeEntrada Date
     * @param id int
     */
    public Produto(String nome, String marca, String descricao, int quantidade, double preco, Date dataDeEntrada, int id) {
        this.nome = nome;
        this.marca = marca;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = preco;
        this.dataDeEntrada = dataDeEntrada;
        this.id = id;
    }

    /**
     * Construtor para adicionar produto na lista de venda
     * 
     * @param produto
     */
    public Produto(Produto produto) {
        this.nome = produto.nome;
        this.marca = produto.marca;
        this.descricao = produto.descricao;
        this.quantidade = produto.quantidade;
        this.valor = produto.valor;
        this.dataDeEntrada = produto.dataDeEntrada;
        this.id = produto.id;
    }

    /**
     * @return quantidadeNaVenda
     */
    public int getQuantidadeNaVenda() {
        return quantidadeNaVenda;
    }

    /**
     * @param quantidadeNaVenda int quantidadeNaVenda para set
     */
    public void setQuantidadeNaVenda(int quantidadeNaVenda) {
        this.quantidadeNaVenda = quantidadeNaVenda;
    }

    /**
     * @return id int
     */
    public int getId() {
        return id;
    }

    /**
     * @param id int id para set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return idFilial int
     */
    public int getIdFilial() {
        return idFilial;
    }

    /**
     * @param idFilial int idFilial para set
     */
    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    /**
     * @return nome String
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome String nome para set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return marca String
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca String marca para set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return a quantidade int
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade int quantidade para set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return o preco double
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor double preco para set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return descricao String
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao String descricao para set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return dataDeEntrada Date
     */
    public Date getDataDeEntrada() {
        return dataDeEntrada;
    }

    /**
     * @param dataDeEntrada Date dataDeEntrada para set
     */
    public void setDataDeEntrada(Date dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }

}
