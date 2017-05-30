/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Banco;

import Utilitarios.ConexaoDao;
import Utilitarios.Utilitarios;

/**
 *
 * @author tads
 */
public class LivroDAO extends ConexaoDao{
    
    
    public void importarCSV() {
        new Utilitarios("banco").importar();
    }
}
