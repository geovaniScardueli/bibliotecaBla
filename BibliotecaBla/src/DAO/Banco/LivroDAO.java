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
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public void realizaEmprestimo(int codBarra, String usuario) {
        try {
            String retorno = podeEmprestar(codBarra);
            if (!"".equals(retorno)) {
                System.out.println(retorno);
                return;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(sdf.format(new Date(System.currentTimeMillis())), formatter);
            LocalDate dataDevolucao = date.plusDays(15);

            Connection conn = novaConexao();
            
            String sql = "insert into emprestimo(COD_BARRAS, DATA_EMPRESTIMO, DATA_DEVOLUCAO, USUARIO) values (?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, codBarra);
            pstmt.setDate(2, java.sql.Date.valueOf(date));
            pstmt.setDate(3, java.sql.Date.valueOf(dataDevolucao));
            pstmt.setString(4, usuario);

            pstmt.executeUpdate();
            pstmt.close();
            
            conn.close();
        } catch (Exception ex) {
            System.out.println("erro ao realizar emprestimo banco");
        }
    }
    
    private String podeEmprestar(int codBarras) throws Exception {
        Connection conn = novaConexao();
        int registros = 0;
        
        String sql = "select count(1) resultado from emprestimo where COD_BARRAS = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, codBarras);
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            registros = rs.getInt("RESULTADO");
        }
        pst.close();
        
        if (registros > 0) {
            return "Este livro já foi emprestado";
        }
        
        String sql2 = "select count(1) resultado from livro where CODIGO_BARRAS = ?";
        PreparedStatement pst2 = conn.prepareStatement(sql2);
        pst2.setInt(1, codBarras);
        
        rs = pst2.executeQuery();
        while (rs.next()) {
            registros = rs.getInt("RESULTADO");
        }
        pst2.close();
        
        if (registros == 0) {
            return "Nao existe este livro";
        }
        
        return "";
    }
}
