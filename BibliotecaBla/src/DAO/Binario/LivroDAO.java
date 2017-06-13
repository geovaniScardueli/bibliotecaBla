/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Binario;

import DTO.Livro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
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

    private final String barra = System.getProperty("file.separator");
    private final String path = System.getProperty("user.home");

    public String incluiLivro(Livro param) throws Exception {
        HashMap gravar = new HashMap();
        gravar.put(param.getIdLivro(), param);
        gravarArquivo(getCaminhoLivros(param.getIdLivro() + "-" + param.getCodigoDeBarras() + ".ser"), gravar);
        return "Livro incluido com sucesso";
    }

    public String realizaEmprestimo(int codBarra, String usuario) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(sdf.format(new Date(System.currentTimeMillis())), formatter);
        LocalDate dataDevolucao = date.plusDays(15);
        Livro empresta = buscaLivro(codBarra);

        if (empresta == null) {
            return "Não existe este livro cadastrado";
        }

        HashMap gravar = new HashMap();
        gravar.put("USUARIO", usuario);
        gravar.put("LIVRO", empresta);
        gravar.put("DATA_DEVOLUCAO", dataDevolucao);
        gravar.put("DATA_EMPRESTIMO", date);

        gravarArquivo(getCaminhoEmprestimo(usuario + "-" + empresta.getCodigoDeBarras() + ".ser"), gravar);
        return "Realizado emprestimo com sucesso";
    }

    private String getCaminhoLivros(String nomeArquivo) {
        return path + barra + "informacoes" + barra + "arquivos" + barra + nomeArquivo;
    }

    private String getCaminhoEmprestimo(String nomeArquivo) {
        return path + barra + "informacoes" + barra + "emprestimo" + barra + nomeArquivo;
    }

    private Livro buscaLivro(int codBarras) throws Exception {
        Livro retorno = null;
        File fil = new File(getCaminhoLivros(""));
        File afile[] = fil.listFiles();
        for (File temp : afile) {
            String barras = temp.getName().split("-")[1].replaceAll(".ser", "");
            if (barras.equals(codBarras + "")) {
                ObjectInputStream imput = new ObjectInputStream(new FileInputStream(temp));
                HashMap livro = (HashMap) imput.readObject();
                retorno = (Livro) livro.get(Integer.parseInt(temp.getName().split("-")[0]));
                temp.delete();
                break;
            }
        }
        return retorno;
    }

    public String devolverLivro(int codBarra) throws Exception {
        Livro livro = buscaLivroEmprestimo(codBarra);
        if (livro == null) {
            return "Este livro não está emprestado";
        }

        HashMap gravar = new HashMap();
        gravar.put(livro.getIdLivro(), livro);
        gravarArquivo(getCaminhoLivros(livro.getIdLivro() + "-" + livro.getCodigoDeBarras() + ".ser"), gravar);
        return "Livro Devolvido com sucesso";
    }

    private Livro buscaLivroEmprestimo(int codBarras) throws Exception {
        Livro retorno = null;
        File fil = new File(getCaminhoEmprestimo(""));
        File afile[] = fil.listFiles();
        for (File temp : afile) {
            String barras = temp.getName().split("-")[1].replaceAll(".ser", "");
            if (barras.equals(codBarras + "")) {
                ObjectInputStream imput = new ObjectInputStream(new FileInputStream(temp));
                HashMap livro = (HashMap) imput.readObject();
                retorno = (Livro) livro.get("LIVRO");
                temp.delete();
                break;
            }
        }
        return retorno;
    }

    private void gravarArquivo(String caminho, HashMap gravar) throws Exception {
        ObjectOutputStream binar = new ObjectOutputStream(new FileOutputStream(caminho));
        binar.writeObject(gravar);
        binar.flush();
        binar.close();
    }
}
