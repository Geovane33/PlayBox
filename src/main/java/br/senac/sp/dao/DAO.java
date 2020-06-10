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
public interface DAO<T> {
    boolean salvar(T object);
    List<T> consultar(String values, String tipo);
    boolean atualizar(T object);
    boolean excluir(int id);
}
