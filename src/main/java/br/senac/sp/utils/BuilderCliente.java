/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.utils;

import br.senac.sp.entidade.Cliente;
import java.util.Map;


/**
 *
 * @author Geovane
 */
public class BuilderCliente {

    private final Map<String, String[]> mapCliente;

    /**
     * Pegar atributos cliente e construir um onbjeto
     * @param mapCliente
     */
    public BuilderCliente(Map<String, String[]> mapCliente) {
        this.mapCliente = mapCliente;
    }

    public Cliente getObjCliente() {

        Cliente cliente = new Cliente();
        Conversor data = new Conversor();

        try {

            cliente.setId(Integer.valueOf(this.mapCliente.get("id")[0]));
            cliente.setIdFilial(Integer.valueOf(this.mapCliente.get("idFilial")[0]));
            cliente.setNome(this.mapCliente.get("nome")[0]);
            cliente.setCpf(this.mapCliente.get("CPF")[0]);
            cliente.setDataNascimento(data.parseData(this.mapCliente.get("nascimento")[0], "dd/MM/yyyy"));
            cliente.setSexo(this.mapCliente.get("sexo")[0]);
            cliente.setTelefone(this.mapCliente.get("telefone")[0]);
            cliente.setEmail(this.mapCliente.get("email")[0]);
            cliente.setCep(this.mapCliente.get("CEP")[0].trim());
            cliente.setUf(this.mapCliente.get("uf")[0]);
            System.out.println("map:" + this.mapCliente.get("cidade")[0]);
            cliente.setCidade(this.mapCliente.get("cidade")[0]);
            cliente.setBairro(this.mapCliente.get("bairro")[0]);
            cliente.setNumero(this.mapCliente.get("numero")[0]);

        } catch (NumberFormatException ex) {
            System.out.println("Erro ao obter objeto Cliente");
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Class: " + ex.getClass());
            cliente = null;
        }

        return cliente;
    }
}
