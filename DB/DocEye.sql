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
    idiomas varchar(100)
);

/*Inserindo alguns dados na tabela como exemplo. Pode apagar depois*/
INSERT INTO candidato (nome, telefone, email, endereco, competencias, idiomas) VALUES
('Larissa', '12997173143', 'larissarosa@gmail.com', 'Rua das Flores, Nº 157', 'HTML, CSS, Javascript', 'Inglês, Espanhol'),
('João Silva', '11984562378', 'joao.silva@hotmail.com', 'Avenida Paulista, Nº 300', 'Java, Python, SQL', 'Inglês, Francês'),
('Maria Oliveira', '21976345812', 'maria.oliveira@yahoo.com.br', 'Rua dos Jacarandás, Nº 45', 'React, Node.js, MongoDB', 'Espanhol, Alemão'),
('Pedro Santos', '31998765432', 'pedro.santos@outlook.com', 'Rua das Palmeiras, Nº 78', 'C++, C#, DevOps', 'Inglês');


/*Selecionar todos os itens*/
select * from candidato;

/*Apagar todos os itens da tabela*/
truncate table candidato;


/*Dropar uma tabela e excluí-la do banco de dados. Use com cautela*/
DROP table candidato;
DROP table curriculo;
DROP table boletim;


/*Deletar itens de tabelas com alguma característica*/
DELETE from candidato WHERE UniqueID=6;




