create database docky;
use docky;
show databases;

create table candidato(
    UniqueID int primary key auto_increment,
    nome varchar(100),
    telefone varchar(20),
    email varchar(100),
    endereco varchar(150),
    competencias text,
    idiomas varchar(100),
);

/*Selecionar todos os itens*/
select * from candidato;

/*Apagar todos os itens da tabela*/
truncate table candidato;


/*Dropar uma tabela e excluí-la do banco de dados. Use com cautela*/
DROP table candidato;


/*Deletar itens de tabelas com alguma característica*/
DELETE from candidato WHERE UniqueID=6;
