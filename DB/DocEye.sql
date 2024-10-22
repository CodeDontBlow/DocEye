create database docky;
use docky;

create table candidato(
    UniqueID int primary key auto_increment,
    nome varchar(100),
    tipo_doc varchar(10)
);

drop table candidato;

create table boletim(
    UniqueID int primary key,
    matricula varchar(20),
    escola varchar(100),
    nota_port decimal(3,1) unsigned,
    nota_mat decimal(3,1) unsigned,
    foreign key (UniqueID) references candidato(UniqueID)
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

INSERT INTO candidato (nome, tipo_doc) VALUES
    ('Aurelio', 'curriculo'),
    ('Isadora', 'boletim'),
    ('Benjamin', 'boletim'),
    ('Valentina', 'curriculo'),
    ('Gael', 'boletim');

INSERT INTO boletim (UniqueID, matricula, escola, nota_port, nota_mat) VALUES
    (2, 'ISAD123', 'Colégio Horizonte', 8.5, 7.8),
    (3, 'BENJ456', 'Escola Nova Era', 9.0, 6.5),
    (5, 'GAEL789', 'Colégio Inovação', 7.3, 8.9);

INSERT INTO curriculo (UniqueID, telefone, email, linkedin, portifolio, endereco, competencias, idiomas) VALUES
    (1, '(11) 99999-1234', 'aurelio@gmail.com', 'linkedin.com/in/aurelio', 'aureliodesign.com',
    'Rua das Flores, 45 - São Paulo/SP', 'Design Gráfico, Adobe Photoshop, Illustrator', 'Português, Inglês'),

    (4, '(21) 98888-5678', 'valentina@yahoo.com', 'linkedin.com/in/valentina', 'valentinart.com.br',
    'Av. Brasil, 300 - Rio de Janeiro/RJ', 'Marketing Digital, SEO, Google Ads', 'Português, Espanhol, Inglês');


select * from candidato;
select * from boletim;
select * from curriculo;
