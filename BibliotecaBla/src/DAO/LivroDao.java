/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Livro;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 *
 * @author tads
 */
public class LivroDao {

    /**
     * Importa o CSV e já salva em arquivos separadamente
     * 
     */
    public void importarCSV() {
        String path = System.getProperty("user.home");
        String barra = System.getProperty("file.separator");

        HashMap param = new HashMap();

        try {
            BufferedReader br = new BufferedReader(new FileReader("/local/home/tads/NetBeansProjects/"
                    + "bibliotecaBla/BibliotecaBla/src/livroCSV/04-final-livros.csv"));
            int i = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while (br.ready()) {
                String linha = br.readLine();
                if (i > 0) {
                    try {
                        String[] listaLivro = linha.split("\\|");
                        Livro livro = new Livro();
                        livro.setCodigoDeBarras(Integer.parseInt(listaLivro[0]));
                        livro.setIdLivro(Integer.parseInt(listaLivro[1]));
                        livro.setExemplar(Integer.parseInt(listaLivro[2]));
                        livro.setDataAquisicaoExemplar(LocalDate.parse(listaLivro[3], formatter));
                        livro.setDataCadastroSistema(LocalDate.parse(listaLivro[4], formatter));
                        livro.setClassificacao(listaLivro[5]);
                        livro.setAreaConhecimento(listaLivro[6]);
                        livro.setAutor(listaLivro[7]);
                        livro.setTitulo(listaLivro[8]);
                        livro.setAno(listaLivro[9]);
                        livro.setIsbn(Long.parseLong(listaLivro[10]));
                        livro.setEditora(listaLivro[11]);
                        livro.setPaginas(Integer.parseInt(listaLivro[12]));

                        HashMap gravar = new HashMap();
                        param.put(listaLivro[1], livro);

                        ObjectOutputStream binar = new ObjectOutputStream(new FileOutputStream("/local/home/tads/NetBeansProjects/"
                                + "bibliotecaBla/BibliotecaBla/src/livroCSV/binario" + listaLivro[1] + "-" + listaLivro[0] + ".ser"));
                        binar.writeObject(param);
                        binar.flush();
                        binar.close();
                    } catch (Exception ex) {
                        System.out.println("errrou");

                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("erro" + ex);
        }
    }
    
    /**
     * Procura um livro especifico
     * @return HashMap contendo o livro
     */
    public HashMap buscarLivro(int id, int barras) {
        
        return null;
    }

}
