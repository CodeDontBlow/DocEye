create database docky;
use docky;
show databases;

create table candidato(
    UniqueID int primary key auto_increment,
    nome varchar(100),
);

create table curriculo(
    UniqueID int primary key ,
    telefone varchar(20),
    email varchar(100),
    linkedin varchar (100),
    portifolio varchar(100),
    endereco varchar(150),
    competencias text,
    idiomas varchar(100),
    foreign key (UniqueID) references candidato(UniqueID)
);

/*Inserção de dados nas tabelas*/
INSERT INTO candidato (nome) VALUES
    ('Aurelio'),
    ('Isadora'),
    ('Benjamin'),
    ('Valentina'),
    ('Gael');

INSERT INTO curriculo (UniqueID, telefone, email, linkedin, portifolio, endereco, competencias, idiomas) VALUES
    (1, '(11) 99999-1234', 'aurelio@gmail.com', 'linkedin.com/in/aurelio', 'aureliodesign.com',
    'Rua das Flores, 45 - São Paulo/SP', 'Design Gráfico, Adobe Photoshop, Illustrator', 'Português, Inglês'),
    (4, '(21) 98888-5678', 'valentina@yahoo.com', 'linkedin.com/in/valentina', 'valentinart.com.br',
    'Av. Brasil, 300 - Rio de Janeiro/RJ', 'Marketing Digital, SEO, Google Ads', 'Português, Espanhol, Inglês');



/*Selecionar todos os itens*/
select * from candidato;
select * from curriculo;

/*Apagar todos os itens da tabela*/
truncate table candidato;
truncate table curriculo;


/*Dropar uma tabela e excluí-la do banco de dados. Use com cautela*/
DROP table candidato;
DROP table curriculo;


/*Deletar itens de tabelas com alguma característica*/
DELETE from candidato WHERE UniqueID=6;

/*Selecionar linhas de uma tabela com base em colunas similares de uma outra tabela, usando o JOIN*/
SELECT curriculo.UniqueID, curriculo.telefone, curriculo.email, curriculo.linkedin, curriculo.portifolio, curriculo.endereco, curriculo.competencias, curriculo.idiomas
FROM curriculo
INNER JOIN candidato ON curriculo.UniqueID=candidato.UniqueID;
