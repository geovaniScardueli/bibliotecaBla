/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

/**
 *
 * @author tads
 */
public class MigraCSVparaBinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // importar a tabela csv para binário
        Utilitarios utils = new Utilitarios("binario");
        utils.importar();
    }
    
}
