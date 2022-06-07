talvez seja necessário trocar o localhost:3307 para 3306;

create database lojabd;

use lojabd;

create table loja(
             id int primary key,
             nome char(100) not null,
             dataCadastro date not null,
             endereco char(150) not null,
             telefone char(15) not null,
             CNPJ char(20) not null);

select * from loja;