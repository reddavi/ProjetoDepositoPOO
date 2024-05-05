package org.example;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {

    private String nome;
    private int quantidade;
    private tipoEstoqueCategoria categoria;

    public Item(String nome, int quantidade,tipoEstoqueCategoria categoria) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String toString() {
        return "Temos **" + this.quantidade + "** unidades de " + this.nome + " no estoque.";
    }

    public tipoEstoqueCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(tipoEstoqueCategoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return quantidade == item.quantidade && Objects.equals(nome, item.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, quantidade);
    }
}
