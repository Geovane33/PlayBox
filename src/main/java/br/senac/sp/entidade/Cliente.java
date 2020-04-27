package br.senac.sp.entidade;

import java.util.Date;

/**
 *Entidade Cliente
 */
public class Cliente {
    //atributos privados(encapsulados)
    private int id;
    private String nome;
    private Date dataNascimento;
    private String sexo;
    private String telefone;
    private String email;
    private String cpf;
    private String cep;
    private String cidade;
    private String uf;
    private String bairro;
    private String numero;


    /**
     *construtor vazio
     */
    public Cliente(){
        
    }
    /**
     * Construtor que recebe por paramentro todos os atributos
     * @param nome String
     * @param dataNascimento Date
     * @param sexo String
     * @param telefone String
     * @param email String
     * @param cpf String
     * @param cep String
     * @param cidade String
     * @param uf String
     * @param bairro String
     * @param numero String
     */
    public Cliente(String nome, Date dataNascimento, String sexo, String telefone, String email, String cpf, String cep, String cidade, String uf, String bairro, String numero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.bairro = bairro;
        this.numero = numero;
    }

    /**
     *Construtor para atualizar dados do cliente
     * @param id int
     * @param nome String
     * @param dataNascimento Date
     * @param sexo String
     * @param telefone String
     * @param email String
     * @param cpf String
     * @param cep String
     * @param cidade String
     * @param uf String
     * @param bairro String
     * @param numero String
     */
    public Cliente(int id, String nome, Date dataNascimento, String sexo, String telefone, String email, String cpf, String cep, String cidade, String uf, String bairro, String numero) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.bairro = bairro;
        this.numero = numero;
    }
    
    
    
    //Abaixo metodos getters e setters
    public void setId(int Id){
        this.id = Id;
    }
    
    public int getId(){
        return this.id;
    }
        
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setDataNascimento (Date dataNascimento){
        this.dataNascimento = dataNascimento;   
    }
    
    public Date getDataNascimento(){
        return this.dataNascimento;
    }
    
    
    public void setSexo (String sexo){
        this.sexo = sexo;
        
    }
    
    public String getSexo(){
        return this.sexo;
    }
    
    public void setBairro(String bairro){
        this.bairro = bairro;
    }
    
    public String getBairro(){
        return this.bairro;
    }
    
    public void setNumero(String numero){
        this.numero = numero;
          
    }
    
    public String getNumero(){
        return this.numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
