
package br.senac.sp.entidade;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.senac.sp.utils.PerfilEnum;
import java.util.Date;

public class UsuarioSistema extends Pessoa{
    
    private String usuario;
    private String senha;
    private PerfilEnum perfil;
    private boolean isAdmin;

    public UsuarioSistema() {
    }
    
    public UsuarioSistema(int idFilial, String nome, String cpf, Date dataNascimento, String sexo, String telefone, String email, String uf, String cidade, String cep, String bairro, String numero) {
        super(idFilial, nome, cpf, dataNascimento, sexo, telefone, email, uf, cidade, cep, bairro, numero);
    }
    
    public String encodeSenha(String senhaAberta) {
        return BCrypt.withDefaults().hashToString(12, senhaAberta.toCharArray());
    }
    
    public boolean validarSenha(String senhaAberta) {
        BCrypt.Result result = BCrypt.verifyer().verify(senhaAberta.toCharArray(), this.getSenha());
        return result.verified;
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

    public boolean isAdmin() {
        return PerfilEnum.admin.equals(this.getPerfil());
    }
    //usuario que acessa todas as telas de apenas uma filial
    public boolean isGerente() {
        return PerfilEnum.gerente.equals(this.getPerfil());
    }
    
      public boolean isVendedor() {
        return PerfilEnum.vendedor.equals(this.getPerfil());
    }
 
    
}
