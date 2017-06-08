/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.io.File;

/**
 *
 * @author tads
 */
public class MigraCSVparaBinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //funcionando
//        Utilitarios utils = new Utilitarios("binario");
//        utils.importar();
        File fil = new File("/local/home/tads/informacoes/arquivos/");
        File afile[] = fil.listFiles();
        System.out.println(afile[0].getName());
    }
    
}
