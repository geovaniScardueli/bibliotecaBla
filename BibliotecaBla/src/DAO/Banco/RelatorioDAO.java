/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Banco;

import Utilitarios.ConexaoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tads
 */
public class RelatorioDAO extends ConexaoDao {
 
    public String maiorNumPagina() throws Exception {
        Connection conn = novaConexao();
        int paginas = 0;

        String sql = "select max(paginas) resultado from livro";
        PreparedStatement pst = conn.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            paginas = rs.getInt("RESULTADO");
        }
        return paginas + "";
    }
    
    public String quantAreaConhecimento(String area) throws Exception {
        Connection conn = novaConexao();
        int count = 0;

        String sql = "select count(1) resultado from livro where area_conhecimento = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, area.toUpperCase());
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            count = rs.getInt("RESULTADO");
        }
        return count + "";
    }
    
    public String numeroEmprestados() throws Exception {
        Connection conn = novaConexao();
        int count = 0;

        String sql = "select count(1) resultado from emprestimo";
        PreparedStatement pst = conn.prepareStatement(sql);
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            count = rs.getInt("RESULTADO");
        }
        return count + "";
    }
}
