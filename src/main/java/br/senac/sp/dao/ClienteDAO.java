/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.db.ConexaoDB;
import br.senac.sp.entidade.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static javax.ws.rs.core.Response.ok;

public class ClienteDAO {

    public static boolean salvarCliente(Cliente cliente) {
        boolean ok = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConexaoDB.getConexao();
            String sql = "insert into cliente values (default,?,?,?,?,?,?,?,?,?,?,?) ";
            ps = con.prepareStatement(sql);
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
            return false;
        } finally {

            //Libero os recursos da memória
            try {
                if (ps != null) {
                    ps.close();
                }

                ConexaoDB.fecharConexao();

            } catch (SQLException ex) {
                System.out.println("erro:" + ex.getMessage());
            }
        }
        return ok;
    }
}
