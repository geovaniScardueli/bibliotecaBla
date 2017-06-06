/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Binario;

import DTO.Livro;
import Utilitarios.Utilitarios;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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

    public void realizaEmprestimo(String nome, int barraLivro) {
        
    }

    public void incluiLivro(Livro param) {
        try {
        String path = System.getProperty("user.home");
        String barra = System.getProperty("file.separator");
        HashMap gravar = new HashMap();
        gravar.put(param.getIdLivro(), param);
        ObjectOutputStream binar = new ObjectOutputStream(new FileOutputStream(path + barra + "NetBeansProjects"
                + barra + "bibliotecaBla" + barra + "BibliotecaBla" + barra + "src" + barra
                + "livroCSV" + barra + "binario" + barra + param.getIdLivro() + "-" + param.getCodigoDeBarras() + ".ser"));
        binar.writeObject(gravar);
        binar.flush();
        binar.close();
        } catch(Exception ex) {
            System.out.println("Erro ao adicionar livro binario");
        }
    }

}
