package br.senac.sp.entidade;

import java.util.Date;

public class Produto {

    //só para a aplicação
    public int quantidadeNaVenda;

    //encapsulamento dos atributos
    private int id;
    private int idFilial;
    private String nome;
    private String tipo;
    private String marca;
    private String descricao;
    private int quantidade;
    private double preco;
    private Date dataDeEntrada;
    

    /**
     * construtor vazio
     */
    public Produto() {
    }
    
    

    /**
     * construtor para cadastrar produto
     *
     * @param nome String
     * @param tipo String
     * @param marca String
     * @param descricao String
     * @param quantidade int
     * @param preco double
     * @param dataDeEntrada Date
     * @param idFilial int
     */
    public Produto(String nome, String tipo, String marca, String descricao, int quantidade, double preco, Date dataDeEntrada, int idFilial) {
        this.nome = nome;
        this.tipo = tipo;
        this.marca = marca;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.dataDeEntrada = dataDeEntrada;
        this.idFilial = idFilial;
    }

    /**
     * construtor para atualizar dados do produto
     *
     * @param id int
     * @param nome String
     * @param tipo String
     * @param marca String
     * @param descricao String
     * @param quantidade int
     * @param preco double
     * @param dataDeEntrada Date
     * @param idFilial int
     */
    public Produto(int id, String nome, String tipo, String marca, String descricao, int quantidade, double preco, Date dataDeEntrada, int idFilial) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.marca = marca;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.dataDeEntrada = dataDeEntrada;
        this.idFilial = idFilial;
    }
    
    /**
     * contrutos para adicionar produto ao carrinho
     * @param copia
     */
    public Produto(Produto copia) {
        this.id = copia.id;
        this.nome = copia.nome;
        this.preco = copia.preco;
        this.quantidade = copia.quantidade;
        this.quantidadeNaVenda = copia.quantidadeNaVenda;
    }

    /**
     * @return a quantidadeNaVenda
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
     * @return o id int
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
     * @return o nome String
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
     * @return o tipo String
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo String tipo para set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return a marca String
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
     * @return a descricao String
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
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco double preco para set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return a dataDeEntrada Date
     */
    public Date getDataDeEntrada() {
        return dataDeEntrada;
    }

    /**
     * @param dataDeEntrada Date a dataDeEntrada para set
     */
    public void setDataDeEntrada(Date dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }

    /**
     * @return o idFilial int
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

}
