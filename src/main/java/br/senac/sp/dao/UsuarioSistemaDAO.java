package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.UsuarioSistema;
import br.senac.sp.utils.PerfilEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tscarton
 */
public class UsuarioSistemaDAO implements DAO<UsuarioSistema> {

    @Override
    public boolean salvar(UsuarioSistema usuario) {
        boolean ok = false;
        Connection conexao = null;
        PreparedStatement ps = null;
        try {

            conexao = ConexaoDB.getConexao();
            String sql = "INSERT INTO usuariosistema VALUES (default,?,?,?,?)";
            ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.encodeSenha(usuario.getSenha()));
            ps.setString(3, usuario.getPerfilString());
            ps.setInt(4, usuario.getIdFilial());
            ps.execute();
            final ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
            } else {
                throw new SQLException("Falha ao obter o ID do usuario.");
            }
            ok = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erro ao salvar usuario");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ok = false;
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
        return ok;
    }

    @Override
    public boolean atualizar(UsuarioSistema usuario) {
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
            ps = conexao.prepareStatement("UPDATE usuariosistema SET usuario = ?, senha = ?, perfil = ?, id_filial = ? WHERE id_usuario = ?");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.encodeSenha(usuario.getSenha()));
            ps.setString(3, usuario.getPerfilString());
            ps.setInt(4, usuario.getIdFilial());
            ps.setInt(5, usuario.getIdUsuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erro ao atualizar usuario");
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

    }

    @Override
    public List consultar(String value, String usuario) {
        List<UsuarioSistema> usuarios = new ArrayList<>();
        Connection con;
        try {
            con = ConexaoDB.getConexao();
            String sql;
            //se value for diferente de: "" então e busca todos usuarios de id diferente do que vai ser atulizado isso é para atualizar e prevenir 2 usuarios iguais
            if (!value.equals("")) {
                sql = "select * from usuariosistema where usuario != " + usuario + "AND id_usuario !=" + Integer.valueOf(value);
            } else {
                sql = "select * from usuariosistema where usuario=?";
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UsuarioSistema usuarioSistema = null;
                usuarioSistema = new UsuarioSistema();
                String senha = rs.getString("senha");
                String perfil = rs.getString("perfil");
                int idFilial = rs.getInt("id_filial");
                usuarioSistema.setUsuario(usuario);
                usuarioSistema.setSenha(senha);
                usuarioSistema.setPerfil(PerfilEnum.valueOf(perfil));
                usuarioSistema.setIdFilial(idFilial);
                usuarios.add(usuarioSistema);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar usuario");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return usuarios;
    }

    /**
     * Excluir um determinado usuario
     *
     * @param id - recebe por parametro o id referente ao usuario que deseja
     * excluir
     * @return boolean - true: excluido com sucesso false: erro ao excluir
     */
    @Override
    public boolean excluir(int id) {
        boolean ok = false;
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
            ps = conexao.prepareStatement("DELETE FROM usuariosistema WHERE id_usuario = ?");
            ps.setInt(1, id);
            ok = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir usuario");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ok = false;
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
        return ok;
    }

}
