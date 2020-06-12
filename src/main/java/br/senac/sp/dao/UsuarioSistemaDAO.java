package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.UsuarioSistema;
import br.senac.sp.utils.PerfilEnum;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tscarton
 */
public class UsuarioSistemaDAO {

    public boolean salvar(UsuarioSistema usuario) {
        boolean ok = false;
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
            String sql = "INSERT INTO usuariosistema VALUES (default,?,?,?,?,?)";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getUsuario());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getPerfil().getPerfil());
            ps.setInt(2, usuario.getIdFIlial());
            ps.setDate(4, new Date(usuario.getDataNascimento().getTime()));
            ps.setString(5, usuario.getSexo());
            ps.execute();
            ok = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erro ao salvar cliente");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        } finally {
            //Libero os recursos da memória
            try {
                if (ps != null) {
                    ps.close();
                }
                ConexaoDB.fecharConexao(conexao);

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return true;
    }

    public static UsuarioSistema getUsuarioSistemaByUsuario(String usuario) {
        UsuarioSistema usuarioSistema = null;
        Connection con;
        try {
            con = ConexaoDB.getConexao();
            String sql = "select * from usuariosistema where usuario=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuarioSistema = new UsuarioSistema();
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                String perfil = rs.getString("perfil");
                int idFilial = rs.getInt("id_filial");
                usuarioSistema.setNome(nome);
                usuarioSistema.setUsuario(usuario);
                usuarioSistema.setSenha(senha);
                usuarioSistema.setPerfil(PerfilEnum.valueOf(perfil));
                usuarioSistema.setIdFilial(idFilial);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarioSistema;
    }

}
