talvez seja necessário trocar o localhost:3307 para 3306;

create database fornecedorbd;

use fornecedorbd;

create table fornecedor(
             id int primary key,
             nome char(100) not null,
             prazo date not null,
             produto char(150) not null,
             telefone char(15) not null,
             CNPJ char(20) not null);

select * from fornecedor;