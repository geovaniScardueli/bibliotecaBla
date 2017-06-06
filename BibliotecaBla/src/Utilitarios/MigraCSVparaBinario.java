/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import DAO.Banco.LivroDAO;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        LivroDAO bin = new LivroDAO();
        bin.importarCSV();
//        File fil = new File("/local/home/tads/NetBeansProjects/");
//        File afile[] = fil.listFiles();
//        System.out.println(afile[0].getName());
//        new LivroDAO().realizaEmprestimo("bla", 0);
    }
    
}
