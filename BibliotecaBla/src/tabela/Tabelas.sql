/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  tads
 * Created: 29/05/2017
 */

create table livro(
CODIGO_BARRAS int primary key,
ID_LIVRO int,
EXEMPLAR int,
DATA_AQUISICAO_EXEMPLAR date,
DATA_CADASTRO_SISTEMA date,
DADOS_LIVRO varchar(255),
CLASSIFICACAO varchar(255),
AREA_CONHECIMENTO varchar(255),
AUTOR varchar(255),
TITULO varchar(255),
ANO varchar(255),
ISBN long,
EDITORA varchar(255),
PAGINAS int
);


create table emprestimo(
COD_BARRAS int primary key,
DATA_EMPRESTIMO date,
DATA_DEVOLUCAO date,
USUARIO varchar(100)
);