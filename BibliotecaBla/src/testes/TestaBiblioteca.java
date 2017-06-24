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
        
        /*
        Criei arquivos separados para todos os livros, ou seja, cada livro
        é um novo aruivo isso inclui os emprestimos também. Para isso existem 
        duas pastas uma para representar todos os livros "todos os arquivos.png"
        e outra pasta para os emprestimos "todos os do emprestimo.png", sendo assim
        ao realizar um emprestimo e deleto o livro da daspa do livro e adicionado
        na pasta do emprestimo, vice-versa ao devolver ele.
        
        OBS: isto é somente para binário;
        */
        
        
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
