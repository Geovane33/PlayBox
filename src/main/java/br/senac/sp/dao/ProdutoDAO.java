package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class ProdutoDAO {

    /**
     * Metodo para salvar clientes
     *
     * @param produto
     * @return true: salvo com sucesso e false: erro ao salvar
     */
    public boolean salvarProduto(Produto produto) {
        boolean ok = false;
        Connection conexao = null;
        PreparedStatement ps = null;
        try {
            conexao = ConexaoDB.getConexao();
            String sql = "Insert INTO produto VALUES (default, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, produto.getIdFilial());
            ps.setString(2, produto.getNome());
            ps.setString(3, produto.getMarca());
            ps.setInt(4, produto.getQuantidade());
            ps.setDouble(5, produto.getValor());
            ps.setString(6, produto.getDescricao());
            ps.setDate(7, new Date(produto.getDataDeEntrada().getTime()));
            ps.execute();
            ok = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erro ao salvar produto");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        } finally {
            //Libera os recursos da memória
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
     * metodo que realiza pesquisa de produto por nome
     *
     * @param nome recebe o nome do produto como parâmetro
     * @return listaProdutos
     */
    public ArrayList<Produto> consultarProduto(String nome) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement ps = null;

        ArrayList<Produto> listaProdutos = new ArrayList<>();

        try {
            conexao = ConexaoDB.getConexao();

            ps = conexao.prepareStatement("SELECT * FROM produto where nome_produto like ?;");
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setIdFilial(rs.getInt("id_filial"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setMarca(rs.getString("marca_produto"));
                produto.setQuantidade(rs.getInt("quantidade_produto"));
                produto.setQuantidade(rs.getInt("valor_produto"));
                produto.setDescricao(rs.getString("desc_produto"));
                produto.setDataDeEntrada(rs.getDate("data_entrada"));
                listaProdutos.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produto"
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
        return listaProdutos;
    }

    /**
     * Atualizar produto
     *
     * @param produto recebe por parametro um objeto produto com os dados a
     * serem atualizados
     * @return true caso o produto seja atulizado com sucesso e false caso de
     * erro ao atualizar o produto
     */
    public boolean atualizarProduto(Produto produto) {
        Connection conexao = null;
        PreparedStatement ps = null;

        try {

            //Tenta estabeler a conexão com o SGBD e cria comando a ser executado conexão
            //Obs: A classe ConexãoDB já carrega o Driver e define os parâmetros de conexão
            conexao = ConexaoDB.getConexao();

            ps = conexao.prepareStatement("UPDATE produto SET nome_produto = ?,"

                    + "marca_produto = ?, quantidade_produto = ?, valor_produto = ?, desc_produto = ?, data_entrada = ? WHERE id_produto = ?;",
                    Statement.RETURN_GENERATED_KEYS);  //Caso queira retornar o ID do cliente
            //Adiciono os parâmetros ao meu comando SQL
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getMarca());
            ps.setInt(3, produto.getQuantidade());
            ps.setDouble(4, produto.getValor());
            ps.setString(5, produto.getDescricao());
            ps.setDate(6, new Date(produto.getDataDeEntrada().getTime()));
            ps.setInt(7, produto.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar produto");
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
