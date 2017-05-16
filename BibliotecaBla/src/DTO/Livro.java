/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author tads
 */
public class Livro {
    int codigoDeBarras;
    int idLivro;
    int exemplar;
    LocalDate dataAquisicaoExemplar;
    LocalDate dataCadastroSistema;
    String dadosLivro;
    String classificacao; 
    String areaConhecimento;

    public int getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(int codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getExemplar() {
        return exemplar;
    }

    public void setExemplar(int exemplar) {
        this.exemplar = exemplar;
    }

    public LocalDate getDataAquisicaoExemplar() {
        return dataAquisicaoExemplar;
    }

    public void setDataAquisicaoExemplar(LocalDate dataAquisicaoExemplar) {
        this.dataAquisicaoExemplar = dataAquisicaoExemplar;
    }

    public LocalDate getDataCadastroSistema() {
        return dataCadastroSistema;
    }

    public void setDataCadastroSistema(LocalDate dataCadastroSistema) {
        this.dataCadastroSistema = dataCadastroSistema;
    }

    public String getDadosLivro() {
        return dadosLivro;
    }

    public void setDadosLivro(String dadosLivro) {
        this.dadosLivro = dadosLivro;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(String areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }
    
    
}
