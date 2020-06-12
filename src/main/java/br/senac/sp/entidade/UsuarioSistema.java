package br.senac.sp.entidade;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.senac.sp.utils.PerfilEnum;
import java.util.Date;

public class UsuarioSistema extends Pessoa {
    
    private int idUsuario;
    private String usuario;
    private String senha;
    private PerfilEnum perfil;;

    public UsuarioSistema() {
    }

    /**
     * Construtor usuarios sistema
     *
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
    public UsuarioSistema(int idFilial, String nome, String cpf, Date dataNascimento, String sexo, String telefone, String email, String uf, String cidade, String cep, String bairro, String numero) {
        super(idFilial, nome, cpf, dataNascimento, sexo, telefone, email, uf, cidade, cep, bairro, numero);
    }

    /**
     * Criptografar senha do usuario
     *
     * @param senhaAberta - senha para criptografas
     * @return
     */
    public String encodeSenha(String senhaAberta) {
        return BCrypt.withDefaults().hashToString(12, senhaAberta.toCharArray());
    }

    /**
     * validar a senha do usuario
     *
     * @param senhaAberta - senha do usuario
     * @return
     */
    public boolean validarSenha(String senhaAberta) {
        BCrypt.Result result = BCrypt.verifyer().verify(senhaAberta.toCharArray(), this.getSenha());
        return result.verified;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * usuario que acessa todas as telas de todas as filiais
     *
     * @return boolean
     */
    public boolean isAdmin() {
        return PerfilEnum.admin.equals(this.getPerfil());
    }

    /**
     * usuario que acessa todas as telas de apenas uma filial
     *
     * @return boolean
     */
    public boolean isGerente() {
        return PerfilEnum.gerente.equals(this.getPerfil());
    }

    /**
     * usuario que acessa todas a tela de clientes e vendas
     *
     * @return boolean
     */
    public boolean isVendedor() {
        return PerfilEnum.vendedor.equals(this.getPerfil());
    }

    /**
     * usuario que acessa apenas a tela de produtos
     *
     * @return boolean
     */
    public boolean isEstoquista() {
        return PerfilEnum.estoquista.equals(this.getPerfil());
    }
}
