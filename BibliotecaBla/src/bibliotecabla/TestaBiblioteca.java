/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecabla;

import DAO.Banco.LivroDAO;
//import DAO.Binario.LivroDAO;
import DTO.Livro;

/**
 *
 * @author informatica
 */
public class TestaBiblioteca {
    
    public static void main(String[] args) {
        
        LivroDAO dao = new LivroDAO();
        
        // incluir um livro
        Livro livro1 = new Livro();
        livro1.setTitulo("Java");
        dao.incluiLivro(livro1);
        
        
    }
    
}
