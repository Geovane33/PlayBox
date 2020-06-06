/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import java.util.List;

/**
 *
 * @author Geovane
 */
public interface DAO {

    /**
     * Salvar
     * @param object objeto para salvar
     * @return boolean
     */
    boolean salvar(Object object);

    /**
     * Consultar
     *@param values
     * @param tipo
     * @return List<Object>
     */
    List<Object> consultar(String values, String tipo);

    /**
     * Atualizar
     *
     * @return boolean
     */
    boolean atualizar(Object o);

    /**
     * Excluir
     *@param id identificação 
     * @return boolean
     */
    boolean excluir(int id);

}
