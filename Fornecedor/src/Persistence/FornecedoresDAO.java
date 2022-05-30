package Persistence;

import Entity.Fornecedor;

import java.util.List;

public interface FornecedoresDAO {
    void inserir(Fornecedor e);
    List<Fornecedor> consultar(String nome);
}
