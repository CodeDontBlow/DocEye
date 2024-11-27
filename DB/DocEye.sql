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
('Pedro Santos', '31998765432', 'pedro.santos@outlook.com', 'Rua das Palmeiras, Nº 78', 'C++, C#, DevOps', 'Inglês'),
('Ana Costa', '12996543218', 'ana.costa@gmail.com', 'Rua Bela Vista, Nº 12', 'HTML, CSS, React', 'Inglês, Italiano'),
('Carlos Mendes', '21987654321', 'carlos.mendes@hotmail.com', 'Avenida Central, Nº 500', 'Java, Spring Boot, MySQL', 'Espanhol'),
('Beatriz Almeida', '31976543210', 'beatriz.almeida@yahoo.com', 'Rua das Acácias, Nº 90', 'Node.js, MongoDB, Docker', 'Inglês, Francês'),
('Lucas Pereira', '22999887766', 'lucas.pereira@gmail.com', 'Rua das Hortências, Nº 23', 'Python, Flask, Django', 'Espanhol'),
('Juliana Souza', '13987654321', 'juliana.souza@hotmail.com', 'Avenida Atlântica, Nº 210', 'Kotlin, Android, Firebase', 'Alemão'),
('Rafael Lima', '11988776655', 'rafael.lima@yahoo.com.br', 'Rua das Oliveiras, Nº 34', 'C, C++, Embedded Systems', 'Inglês'),
('Camila Rocha', '11997543219', 'camila.rocha@gmail.com', 'Rua das Rosas, Nº 67', 'Vue.js, Nuxt.js, TypeScript', 'Inglês, Espanhol'),
('Gabriel Torres', '12998364587', 'gabriel.torres@hotmail.com', 'Avenida dos Pinhais, Nº 11', 'Go, Kubernetes, Terraform', 'Francês, Inglês'),
('Mariana Freitas', '22987654333', 'mariana.freitas@yahoo.com.br', 'Rua dos Cedros, Nº 101', 'Ruby, Ruby on Rails, PostgreSQL', 'Italiano, Espanhol'),
('Fernando Martins', '31965498721', 'fernando.martins@gmail.com', 'Rua das Figueiras, Nº 56', 'JavaScript, React Native, Redux', 'Inglês'),
('Patrícia Ribeiro', '21987766554', 'patricia.ribeiro@hotmail.com', 'Rua dos Pinheiros, Nº 89', 'PHP, Laravel, MySQL', 'Espanhol, Alemão'),
('Rodrigo Carvalho', '12995468732', 'rodrigo.carvalho@gmail.com', 'Avenida do Sol, Nº 78', 'Java, Hibernate, Spring Boot', 'Inglês'),
('Carla Fernandes', '11999887755', 'carla.fernandes@yahoo.com.br', 'Rua das Magnólias, Nº 44', 'Python, Pandas, Matplotlib', 'Inglês, Japonês'),
('Vinícius Alves', '31998877665', 'vinicius.alves@gmail.com', 'Rua dos Cravos, Nº 13', 'C#, ASP.NET, SQL Server', 'Inglês, Francês'),
('Eduarda Santos', '21987543219', 'eduarda.santos@hotmail.com', 'Avenida Verde, Nº 67', 'JavaScript, Angular, Bootstrap', 'Espanhol, Italiano'),
('Guilherme Correia', '13999874563', 'guilherme.correia@yahoo.com.br', 'Rua das Amoreiras, Nº 8', 'Perl, Shell Scripting, Linux', 'Inglês, Alemão'),
('Sara Lopes', '12997564732', 'sara.lopes@gmail.com', 'Rua do Campo, Nº 22', 'HTML, CSS, SEO', 'Inglês, Espanhol'),
('Felipe Nascimento', '31987766543', 'felipe.nascimento@hotmail.com', 'Rua das Palmeiras, Nº 97', 'Scala, Apache Spark, Hadoop', 'Inglês, Francês'),
('Lorena Antunes', '21996547832', 'lorena.antunes@yahoo.com.br', 'Rua dos Lírios, Nº 76', 'Swift, iOS, UIKit', 'Inglês, Italiano'),
('Thiago Oliveira', '11998765432', 'thiago.oliveira@gmail.com', 'Rua dos Pinheiros, Nº 89', 'R, Shiny, ggplot2', 'Inglês, Espanhol');


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




