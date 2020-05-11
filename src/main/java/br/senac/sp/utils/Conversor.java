/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Geovane
 */
public class Conversor {
    
    
    
     public Date parseData(String data, String formato) {
        SimpleDateFormat formatar = new SimpleDateFormat(formato);
        try {
            return formatar.parse(data);
        } catch (ParseException ex) {
            System.out.println("Erro ao converte data - data:\"" + data + "\" formato: \"" + formato+"\"");
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
