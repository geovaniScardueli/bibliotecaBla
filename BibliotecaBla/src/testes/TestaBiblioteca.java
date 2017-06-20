/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import DAO.Binario.LivroDAO;
//import DAO.Banco.LivroDAO;

/**
 *
 * @author informatica
 */
public class TestaBiblioteca {

    public static void main(String[] args) {
        LivroDAO dao = new LivroDAO();
        try {
            // testa um emprestimo
            System.out.println("* teste de emprestimo normal");
            System.out.println(dao.realizaEmprestimo(97475904, "Geovani scardueli"));
        } catch (Exception ex) {
            System.out.println("erro ao fazer um emprestimo" + ex);
        }

        try {
            // tenta emprestar um livro já emprestado
            System.out.println("* teste de emprestimo de livro ja emprestado");
            System.out.println(dao.realizaEmprestimo(97475904, "Geovani scardueli"));
        } catch (Exception ex) {
            System.out.println("erro ao fazer um emprestimo" + ex);
        }

        try {
            // tenta emprestar um livro que não existe
            System.out.println("* teste de emprestimo de livro que não existe");
            System.out.println(dao.realizaEmprestimo(9999, "Geovani scardueli"));
        } catch (Exception ex) {
            System.out.println("erro ao fazer um emprestimo" + ex);
        }

        try {
            // testa devolver um livro
            System.out.println("* teste devolver livro");
            System.out.println(dao.devolverLivro(97475904));
        } catch (Exception ex) {
            System.out.println("erro ao fazer um emprestimo" + ex);
        }

        try {
            // tenta devolver um livro já devolvido
            System.out.println("* teste devolver livro já devolvido");
            System.out.println(dao.devolverLivro(97475904));
        } catch (Exception ex) {
            System.out.println("erro ao fazer um emprestimo" + ex);
        }

        try {
            // tenta devolver um livro que não existe
            System.out.println("* teste devolver livro que não existe");
            System.out.println(dao.devolverLivro(999));
        } catch (Exception ex) {
            System.out.println("erro ao fazer um emprestimo" + ex);
        }
    }

}
