/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import DAO.Binario.RelatorioDAO;
//import DAO.Banco.RelatorioDAO;
/**
 *
 * @author tads
 */
public class TestaRelatorio {
 
    public static void main(String[] args) {
        RelatorioDAO dao = new RelatorioDAO();
        try {
            System.out.println("* livro com maior número de páginas");
            System.out.println(dao.maiorNumPagina());
        } catch (Exception ex) {
            System.out.println("erro ao fazer um emprestimo" + ex);
        }
        
        try {
            System.out.println("* quantidade de livros por uma especifica area de conhecimento");
            System.out.println(dao.quantAreaConhecimento("ESPIONAGEM INDUSTRIAL"));
        } catch (Exception ex) {
            System.out.println("erro ao fazer um emprestimo" + ex);
        }
        
        try {
            System.out.println("* quantidade de livros emprestados");
            System.out.println(dao.numeroEmprestados());
        } catch (Exception ex) {
            System.out.println("erro ao fazer um emprestimo" + ex);
        }
    }
}
