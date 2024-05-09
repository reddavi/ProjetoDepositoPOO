package org.example;

import java.io.IOException;
import java.util.List;

public interface InterfaceDeposito {
    boolean cadastrarItem(Item item) throws IOException;
    Item pesquisarItem(String nome) throws ItemInexistenteException;
    boolean removerItem(String nome);
    List<Item> listarItens();
    int contarQuantidadeDoEstoque();
    boolean temMercadorias(Item item);
    public List<Item> buscarPorCategoria(tipoEstoqueCategoria categoria);
    public void zerarEstoque();

}
