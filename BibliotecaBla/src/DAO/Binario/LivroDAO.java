/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Binario;

import Utilitarios.Utilitarios;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author tads
 */
public class LivroDAO {

    /**
     * Importa o CSV e já salva em arquivos separadamente
     *
     */
    public void importarCSV() {
        new Utilitarios("binario").importar();
    }

    /**
     * Procura um livro especifico
     * @param barras código de barras do livro
     * @return HashMap contendo o livro
     */
    public HashMap buscarLivro(int barras) {
        
        return null;
    }
    
    public void realizaEmprestimo(String nome, int barraLivro) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(sdf.format(new Date(System.currentTimeMillis())),formatter);
        LocalDate dataDevolucao = date.plusDays(15);
        System.out.println(dataDevolucao.format(formatter));
    }

}
