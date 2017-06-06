/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import DAO.Banco.LivroDAO;
import DTO.Livro;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
                            
                        } else if ("banco".equals(tipoDAO)) {
                            
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

    public void emprestimo(String nomeLivro, String usuario) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(sdf.format(new Date(System.currentTimeMillis())), formatter);
        LocalDate dataDevolucao = date.plusDays(15);

        HashMap param = new HashMap(5);
        param.put("USUARIO", usuario);
        param.put("LIVRO", nomeLivro);
        param.put("DATA_DEVOLUCAO", dataDevolucao);
        param.put("DATA_EMPRESTIMO", date);
    }

    private void emprestimoBinario() throws Exception {

    }

    private void emprestimoBanco(HashMap parametros) throws Exception {
        String sql = "insert into emprestimo(LIVRO, DATA_EMPRESTIMO, DATA_DEVOLUCAO, USUARIO) values (?,?,?,?);";
        Connection conn = novaConexao();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, parametros.get("LIVRO").toString());
        pstmt.setDate(2, java.sql.Date.valueOf((LocalDate)parametros.get("DATA_EMPRESTIMO")));
        pstmt.setDate(3, java.sql.Date.valueOf((LocalDate)parametros.get("DATA_DEVOLUCAO")));
        pstmt.setString(4, parametros.get("USUARIO").toString());

        pstmt.executeUpdate();
        pstmt.close();

        conn.close();
    }
}
