/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import DAO.Banco.LivroDAO;
import DTO.Livro;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 *
 * @author tads
 */
public class Utilitarios extends ConexaoDao {

    private final String tipoDAO;
    String path = System.getProperty("user.home");
    String barra = System.getProperty("file.separator");

    public Utilitarios(String tipoDAO) {
        this.tipoDAO = tipoDAO;
    }

    public void importar() {

        HashMap param = new HashMap();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path + barra + "NetBeansProjects" + barra
                    + "bibliotecaBla" + barra + "BibliotecaBla" + barra + "src" + barra
                    + "livroCSV" + barra + "04-final-livros.csv"));
            int i = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DAO.Binario.LivroDAO livroBinario = new DAO.Binario.LivroDAO();
            LivroDAO livroBanco = new LivroDAO();
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

                        if ("binario".equals(tipoDAO)) {
                            livroBinario.incluiLivro(livro);
                        } else if ("banco".equals(tipoDAO)) {
                            livroBanco.incluiLivro(livro);
                        }

                    } catch (Exception ex) {
                        System.out.println("erro na leitura");
                    }
                }
                i = 1;
            }
        } catch (Exception ex) {
            System.out.println("erro" + ex);
        }
    }
}
