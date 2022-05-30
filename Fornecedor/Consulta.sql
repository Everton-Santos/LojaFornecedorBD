talvez seja necessário trocar o localhost:3307 para 3306;

create database fornecedorbd;

use fornecedorbd;

create table fornecedor(id int, nome char(100), prazo date, produto char(150), telefone char(15)
CNPJ char(20));

select * from fornecedor;