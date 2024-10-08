create database docky;
use docky;

create table candidato(
   UniqueID int primary key ,
   nome varchar(100),
   tipo_doc varchar(10)
);

create table boletim(
    UniqueID int primary key ,
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


select * from candidato;
select * from boletim;
select * from curriculo;


