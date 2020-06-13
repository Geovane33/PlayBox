package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements DAO<Funcionario> {

    /**
     * Salvar funcionario
     *
     * @param funcionario recebe um objeto funcionario
     * @return true: salvo com sucesso false: erro ao salvar
     */
    @Override
    public boolean salvar(Funcionario funcionario) {
        boolean ok = false;
        Connection conexao = null;
        PreparedStatement ps = null;
        UsuarioSistemaDAO usuarioDAO = new UsuarioSistemaDAO();
        if (usuarioDAO.salvar(funcionario)) {
            try {
                conexao = ConexaoDB.getConexao();
                String sql = "INSERT INTO funcionario VALUES (default,?,?,?,?,?)";
                ps = conexao.prepareStatement(sql);

                ps.setString(1, funcionario.getNome());
                ps.setString(2, funcionario.getPerfilString());
                ps.setDouble(3, funcionario.getSalario());
                ps.setInt(4, funcionario.getIdFilial());
                ps.setInt(5, funcionario.getIdUsuario());

                ps.execute();
                ok = true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Erro ao salvar cliente");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
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
        return ok;
    }

    /**
     * Retorna uma lista de clientes de acordo com os paramentro passados
     *
     * @param values String - recebe por parametro o cpf ou nome do cliente a
     * ser consultado
     * @param tipo String - recebe por parametro o tipo de consulta a ser
     * realizada
     * @return listaClientes
     */
    @Override
    public List consultar(String values, String tipo) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        try {
            conexao = ConexaoDB.getConexao();
            if (!values.equals("") && tipo.equals("ID")) {
                ps = conexao.prepareStatement("SELECT * FROM funcionario f inner join usuariosistema us on us.id_usuario = f.id_usuario AND f.id_filial = " + Integer.parseInt(values));
            } else if (values.equals("") || tipo.equals("TODOS")) {
                ps = conexao.prepareStatement("SELECT * FROM funcionario f inner join usuariosistema us on us.id_usuario = f.id_usuario");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id_funcionario"));
                funcionario.setIdFilial(rs.getInt("id_filial"));
                funcionario.setIdUsuario(rs.getInt("id_usuario"));
                funcionario.setUsuario(rs.getString("usuario"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setFuncao(rs.getString("perfil"));
                funcionario.setSalario(rs.getDouble("salario"));
                listaFuncionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar cliente");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            //Libero os recursos da memória
            try {
                if (rs != null) {
                    rs.close();
                }
                ConexaoDB.fecharConexao(conexao);

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        }
        return listaFuncionarios;
    }

    /**
     * Atualizar dados do Funcionario
     *
     * @param funcionario - recebe por parametro um objeto funcionario criado na
     * classe controle
     * @return true: Funcionario atulizado com sucesso false: Erro ao atualizar
     * o Funcionario
     */
    @Override
    public boolean atualizar(Funcionario funcionario) {
        Connection conexao = null;
        PreparedStatement ps = null;

        UsuarioSistemaDAO usuarioSistemaDAO = new UsuarioSistemaDAO();
        if (usuarioSistemaDAO.atualizar(funcionario)) {
            try {

                //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
                //Obs: A classe ConexãoDB já carrega o Driver e define os parâmetros de conexão
                conexao = ConexaoDB.getConexao();

                ps = conexao.prepareStatement("UPDATE funcionario SET nome = ?, funcao = ?, salario = ?, id_filial = ? WHERE id_funcionario = ?");
                //Adiciono os parâmetros ao meu comando SQL
                ps.setString(1, funcionario.getNome());
                ps.setString(2, funcionario.getFuncao());
                ps.setDouble(3, funcionario.getSalario());
                ps.setInt(4, funcionario.getIdFilial());
                ps.setInt(5, funcionario.getId());

                return ps.executeUpdate() > 0;                
            } catch (SQLException ex) {
                System.out.println("Erro ao atualizar cliente");
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
        } else {
            return false;
        }

    }

    /**
     * Excluir um determinado funcionario
     *
     * @param id - recebe por parametro o id referente ao funcionario que deseja
     * excluir
     * @return boolean - true: excluido com sucesso false: erro ao excluir
     */
    @Override
    public boolean excluir(int id) {
        Connection conexao = null;
        PreparedStatement ps = null;
        UsuarioSistemaDAO usuario = new UsuarioSistemaDAO();
        usuario.excluir(id);
        try {
            conexao = ConexaoDB.getConexao();
            ps = conexao.prepareStatement("DELETE FROM funcionario WHERE id_funcionario = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir funcionario");
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
}
