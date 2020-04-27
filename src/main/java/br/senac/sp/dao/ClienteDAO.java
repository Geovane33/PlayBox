package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO {

    /**
     * Salvar clientes
     * @param cliente recebe uma entidade cliente
     * @return true: salvo com sucesso false: erro ao salvar
     */
    public boolean salvarCliente(Cliente cliente) {
        boolean ok = false;
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
            String sql = "INSERT INTO cliente VALUES (default,?,?,?,?,?,?,?,?,?,?,?)";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setDate(2, new Date(cliente.getDataNascimento().getTime()));
            ps.setString(3, cliente.getSexo());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getCpf());
            ps.setString(7, cliente.getCep());
            ps.setString(8, cliente.getCidade());
            ps.setString(9, cliente.getUf());
            ps.setString(10, cliente.getBairro());
            ps.setString(11, cliente.getNumero());
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
                ConexaoDB.fecharConexao();

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return ok;
    }

    /**
     * Retorna uma lista de clientes de acordo com os paramentro passados
     * @param values String - recebe por parametro o cpf ou nome do cliente a ser consultado
     * @param tipo String - recebe por parametro o tipo de consulta a ser realizada
     * @return listaClientes
     */
    public ArrayList<Cliente> consultarCliente(String values, String tipo) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            conexao = ConexaoDB.getConexao();
            ps = conexao.prepareStatement("SELECT * FROM cliente "); //where " + tipo + " like ?;
           // ps.setString(1, "%" + values + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
              
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setDataNascimento(rs.getDate("data_nascimento"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setNumero(rs.getString("numero"));

                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consulta cliente"
                    + "");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            //Libero os recursos da memória
            try {
                if (rs != null) {
                    rs.close();
                }
                ConexaoDB.fecharConexao();

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        }
        return listaClientes;
    }

    /**
     * Atualizar dados do Cliente
     * @param cliente - recebe por parametro um objeto cliente criado na classe
     * controle
     * @return true: Cliente atulizado com sucesso false: Erro ao atualizar o Cliente
     */
    public boolean atualizarCliente(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement ps = null;

        try {

            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe ConexãoDB já carrega o Driver e define os parâmetros de conexão
            conexao = ConexaoDB.getConexao();

            ps = conexao.prepareStatement("UPDATE cliente SET nome = ?, data_nascimento = ?, sexo = ?,"
                    + " telefone = ?, email = ?, cpf = ?, cep = ?,"
                    + "cidade = ?, uf = ?, bairro = ?,  numero = ? WHERE id = ?",
                    Statement.RETURN_GENERATED_KEYS);  //Caso queira retornar o ID do cliente
            //Adiciono os parâmetros ao meu comando SQL
            ps.setString(1, cliente.getNome());
            ps.setDate(2, new Date(cliente.getDataNascimento().getTime()));
            ps.setString(3, cliente.getSexo());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getCpf());
            ps.setString(7, cliente.getCep());
            ps.setString(8, cliente.getCidade());
            ps.setString(9, cliente.getUf());
            ps.setString(10, cliente.getBairro());
            ps.setString(11, cliente.getNumero());
            ps.setInt(12, cliente.getId());
          
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

                ConexaoDB.fecharConexao();

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }

    /**
     * Excluir um determinado cliente
     * @param id - recebe por parametro o id referente ao cliente que deseja excluir
     * @return boolean - true: excluido com sucesso
     * false: erro ao excluir  
     */
    public boolean excluirCliente(int id) {
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
            ps = conexao.prepareStatement("DELETE FROM cliente WHERE ID = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir cliente");
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
                ConexaoDB.fecharConexao();

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexãoDB");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
           
            }
        }
    }
}