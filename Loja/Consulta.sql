talvez seja necessário trocar o localhost:3307 para 3306;

create database lojabd;

use lojabd;

create table loja(id int, nome char(100), dataCadastro date, endereco char(150), telefone char(15)
CNPJ char(20));

select * from loja;