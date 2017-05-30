/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import DTO.Livro;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
                            salvarBinario(livro);
                        } else if ("banco".equals(tipoDAO)) {
                            salvarBanco(livro);
                        }

                    } catch (Exception ex) {
                        System.out.println("errrou");

                    }
                }
                i = 1;
            }
        } catch (Exception ex) {
            System.out.println("erro" + ex);
        }
    }

    private void salvarBinario(Livro param) throws Exception {
        HashMap gravar = new HashMap();
        gravar.put(param.getIdLivro(), param);
        ObjectOutputStream binar = new ObjectOutputStream(new FileOutputStream(path + barra + "NetBeansProjects"
                + barra + "bibliotecaBla" + barra + "BibliotecaBla" + barra + "src" + barra 
                + "livroCSV" + barra + "binario" + barra + param.getIdLivro() + "-" + param.getCodigoDeBarras() + ".ser"));
        binar.writeObject(gravar);
        binar.flush();
        binar.close();
    }

    private void salvarBanco(Livro livro) throws Exception {
        Connection conn = novaConexao();
        String sql = "insert into livro(CODIGO_BARRAS,ID_LIVRO,EXEMPLAR,DATA_AQUISICAO_EXEMPLAR,DATA_CADASTRO_SISTEMA,DADOS_LIVRO,CLASSIFICACAO,AREA_CONHECIMENTO,AUTOR,TITULO,ANO,ISBN,EDITORA,PAGINAS)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, livro.getCodigoDeBarras());
            pstmt.setInt(2, livro.getIdLivro());
            pstmt.setInt(3, livro.getExemplar());
            pstmt.setDate(4, java.sql.Date.valueOf(livro.getDataAquisicaoExemplar()));
            pstmt.setDate(5, java.sql.Date.valueOf(livro.getDataCadastroSistema()));
            pstmt.setString(6, livro.getDadosLivro());
            pstmt.setString(7, livro.getClassificacao());
            pstmt.setString(8, livro.getAreaConhecimento());
            pstmt.setString(9, livro.getAutor());
            pstmt.setString(10, livro.getTitulo());
            pstmt.setString(11, livro.getAno());
            pstmt.setLong(12, livro.getIsbn());
            pstmt.setString(13, livro.getEditora());
            pstmt.setInt(14, livro.getPaginas());

            pstmt.executeUpdate();
            pstmt.close();

            conn.close();
        } catch (Exception ex) {
            System.out.println("Erro insert emprestimo: " + ex);
        }
    }
}
