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
     *
     * @param cliente recebe uma entidade cliente
     * @return true: salvo com sucesso false: erro ao salvar
     */
    public boolean salvarCliente(Cliente cliente) {
        boolean ok = false;
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
            String sql = "INSERT INTO cliente VALUES (default,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, cliente.getIdFilial());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getCpf());
            ps.setDate(4, new Date(cliente.getDataNascimento().getTime()));
            ps.setString(5, cliente.getSexo());
            ps.setString(6, cliente.getTelefone());
            ps.setString(7, cliente.getEmail());
            ps.setString(8, cliente.getUf());
            ps.setString(9, cliente.getCidade());
            ps.setString(10, cliente.getCep());
            ps.setString(11, cliente.getBairro());
            ps.setString(12, cliente.getNumero());
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
     *
     * @param values String - recebe por parametro o cpf ou nome do cliente a
     * ser consultado
     * @param tipo String - recebe por parametro o tipo de consulta a ser
     * realizada
     * @return listaClientes
     */
    public ArrayList<Cliente> consultarCliente(String values, String tipo) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            conexao = ConexaoDB.getConexao();
            if (!values.equals("") && tipo.equals("CPF")) {
                ps = conexao.prepareStatement("SELECT * FROM cliente where cpf_cliente like '" + values + "';");
            } else if (!values.equals("") && tipo.equals("nome")) {
                ps = conexao.prepareStatement("SELECT * FROM cliente where nome_cliente like '%" + values + "%';");
            } else if (!values.equals("") && tipo.equals("ID")) {
                ps = conexao.prepareStatement("SELECT * FROM cliente WHERE id_cliente = " + Integer.parseInt(values));
            } else if (tipo.equals("nome") || tipo.equals("CPF")) {
                ps = conexao.prepareStatement("SELECT * FROM cliente ");
            }
            rs = ps.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setIdFilial(rs.getInt("id_filial"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setCpf(rs.getString("cpf_cliente"));
                cliente.setDataNascimento((rs.getDate("nasc_cliente")));
                cliente.setSexo(rs.getString("sexo_cliente"));
                cliente.setTelefone(rs.getString("telefone_cliente"));
                cliente.setEmail(rs.getString("email_cliente"));
                cliente.setUf(rs.getString("uf_cliente"));
                cliente.setCidade(rs.getString("cidade_cliente"));
                cliente.setCep(rs.getString("cep_cliente"));
                cliente.setBairro(rs.getString("bairro_cliente"));
                cliente.setNumero(rs.getString("numero_cliente"));

                listaClientes.add(cliente);
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
     *
     * @param cliente - recebe por parametro um objeto cliente criado na classe
     * controle
     * @return true: Cliente atulizado com sucesso false: Erro ao atualizar o
     * Cliente
     */
    public boolean atualizarCliente(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement ps = null;

        try {

            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe ConexãoDB já carrega o Driver e define os parâmetros de conexão
            conexao = ConexaoDB.getConexao();

            ps = conexao.prepareStatement("UPDATE cliente SET nome_cliente = ?, cpf_cliente = ?, nasc_cliente = ?, sexo_cliente = ?,"
                    + " telefone_cliente = ?, email_cliente = ?, uf_cliente = ?, cidade_cliente =?, cep_cliente = ?,"
                    + "bairro_cliente = ?,  numero_cliente = ? WHERE id_cliente = ?",
                    Statement.RETURN_GENERATED_KEYS);  //Caso queira retornar o ID do cliente
            //Adiciono os parâmetros ao meu comando SQL
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setDate(3, new Date(cliente.getDataNascimento().getTime()));
            ps.setString(4, cliente.getSexo());
            ps.setString(5, cliente.getTelefone());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getUf());
            ps.setString(8, cliente.getCidade());
            ps.setString(9, cliente.getCep());
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
     *
     * @param id - recebe por parametro o id referente ao cliente que deseja
     * excluir
     * @return boolean - true: excluido com sucesso false: erro ao excluir
     */
    public boolean excluirCliente(int id) {
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
            ps = conexao.prepareStatement("DELETE FROM cliente WHERE id_cliente = ?");
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
