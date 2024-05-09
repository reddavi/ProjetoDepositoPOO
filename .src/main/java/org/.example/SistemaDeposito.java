package org.example;

import java.io.IOException;
import java.util.*;

public class SistemaDeposito implements InterfaceDeposito {

    private GravadorDeDados gravador = new GravadorDeDados();
    private Map<String, Item> itens = new HashMap();

    public SistemaDeposito() throws IOException {
        this.itens = new HashMap<>();
        recuperaDados();

    }
    public void salvarDados(){
        try {
            this.gravador.salvaEstoque(this.itens);
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void recuperaDados(){
        try {
            this.itens = this.gravador.recuperarItens();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    public boolean cadastrarItem(Item item) throws IOException {
        if (this.temMercadorias(item)) {
            return false;
        } else {
            this.itens.put(item.getNome(), item);
            salvarDados();
            return true;
        }
    }

    public Item pesquisarItem(String nome) throws ItemInexistenteException {
        if (this.itens.containsKey(nome)) {
            return (Item)this.itens.get(nome);
        } else {
            throw new ItemInexistenteException("Negativo");
        }
    }

    public boolean removerItem(String nome){
        if (this.itens.containsKey(nome)) {
            this.itens.remove(nome);
            System.out.println(nome + " removido com sucesso do estoque!!");
            return true;
        } else {
            return false;
        }
    }

    public List<Item> listarItens() {
        List<Item> itens = new LinkedList<>();
        for (Item i : this.itens.values()) {
            itens.add(i);
        }
        return itens;
    }

    public int contarQuantidadeDoEstoque() {
        int totalQuantidade = 0;
        for (Item item : itens.values()) {
            totalQuantidade += item.getQuantidade();
        }
        return totalQuantidade;
    }
    public boolean temMercadorias(Item item) {
        return this.itens.containsKey(item);
    }

    @Override
    public List<Item> buscarPorCategoria(tipoEstoqueCategoria categoria) {
        List<Item> itensCategoria = new ArrayList<>();
            for (Item item : itens.values()) {
                if (item.getCategoria() == categoria) {
                    itensCategoria.add(item);
                }
            }
            return itensCategoria;
        }
    public void zerarEstoque(){
        itens.clear();
    }

    public Map<String, Item> getItens() {
        return itens;
    }

    public void setItens(Map<String, Item> itens) {
        this.itens = itens;
    }
}
