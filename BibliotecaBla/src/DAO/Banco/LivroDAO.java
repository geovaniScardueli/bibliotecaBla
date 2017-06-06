/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Banco;

import DTO.Livro;
import Utilitarios.ConexaoDao;
import Utilitarios.Utilitarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author tads
 */
public class LivroDAO extends ConexaoDao {

    public void importarCSV() {
        new Utilitarios("banco").importar();
    }

    public void incluiLivro(Livro livro) {
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
    
    
    public void realizaEmprestimo(String nomeLivro, String usuario) {
        try {
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
        } catch (Exception ex) {
            System.out.println("erro ao realizar emprestimo banco");
        }
    }
}
