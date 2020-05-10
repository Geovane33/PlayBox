package br.senac.sp.entidade;

import java.util.Date;

/**
 * Entidade Cliente
 */
public class Cliente extends Pessoa {

    /**
     * construtor vazio
     */
    public Cliente() {
    }

    /**
     * Construtor que recebe por paramentro todos os atributos de cliente
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
    public Cliente(int idFilial, String nome, String cpf, Date dataNascimento, String sexo, String telefone, String email, String uf, String cidade, String cep, String bairro, String numero) {
        super(idFilial, nome, cpf, dataNascimento, sexo, telefone, email, uf, cidade, cep, bairro, numero);
    }

//    /**
//     * Construtor para atualizar dados do cliente
//     *
//     * @param id int
//     * @param nome String
//     * @param cpf String
//     * @param dataNascimento Date
//     * @param sexo String
//     * @param telefone String
//     * @param email String
//     * @param uf String
//     * @param cep String
//     * @param cidade String
//     * @param bairro String
//     * @param numero String
//     */
// public Cliente(String nome, String cpf, Date dataNascimento, String sexo, String telefone, String email, String uf, String cidade, String cep, String bairro, String numero, int id) {
//        this.nome = nome;
//        this.cpf = cpf;
//        this.dataNascimento = dataNascimento;
//        this.sexo = sexo;
//        this.telefone = telefone;
//        this.email = email;
//        this.uf = uf;
//        this.cidade = cidade;
//        this.cep = cep;
//        this.bairro = bairro;
//        this.numero = numero;
//        this.id = id;
// }


}
