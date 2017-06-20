/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Binario;

import DTO.Livro;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 *
 * @author tads
 */
public class RelatorioDAO {

    private final String barra = System.getProperty("file.separator");
    private final String path = System.getProperty("user.home");

    public String maiorNumPagina() throws Exception {
        int maior = 0;
        for (File temp : listaArquivos()) {
            Livro livro = getLivro(temp);
            if (livro.getPaginas() > maior) {
                maior = livro.getPaginas();
            }
        }
        return maior + " páginas";
    }
    
    public String quantAreaConhecimento(String area) throws Exception {
        int maior = 0;
        for (File temp : listaArquivos()) {
            Livro livro = getLivro(temp);
            if (livro.getAreaConhecimento().equals(area)) {
                maior++;
            }
        }
        return maior + " exemplares";
    }
    
    public String numeroEmprestados() throws Exception {
        int count = 0;
        for (File temp : listaArquivosEmprestimo()) {
            count++;
        }
        return count + "";
    }

    private File[] listaArquivos() {
        File fil = new File(getCaminhoLivros(""));
        return fil.listFiles();
    }
    
    private File[] listaArquivosEmprestimo() {
        File fil = new File(getCaminhoEmprestimo(""));
        return fil.listFiles();
    }

    private Livro getLivro(File temp) throws Exception {
        Livro retorno = null;
        String barras = temp.getName().split("-")[1].replaceAll(".ser", "");
        ObjectInputStream imput = new ObjectInputStream(new FileInputStream(temp));
        HashMap livro = (HashMap) imput.readObject();
        retorno = (Livro) livro.get(Integer.parseInt(temp.getName().split("-")[0]));
        return retorno;
    }

    private String getCaminhoLivros(String nomeArquivo) {
        return path + barra + "informacoes" + barra + "arquivos" + barra + nomeArquivo;
    }
    
    private String getCaminhoEmprestimo(String nomeArquivo) {
        return path + barra + "informacoes" + barra + "emprestimo" + barra + nomeArquivo;
    }
}
