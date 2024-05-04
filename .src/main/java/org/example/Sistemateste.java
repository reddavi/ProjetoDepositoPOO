package org.example;

import java.io.IOException;

public class Sistemateste {
    public Sistemateste() {
    }

    public static void main(String[] args) throws ItemInexistenteException, IOException{
        SistemaDeposito sistemaDeposito = new SistemaDeposito();
        SistemaDeposito n1 = new SistemaDeposito();

        System.out.println(n1.listarItens());
        //System.out.println(sistemaDeposito.pesquisarItem("GELADEIRA"));
        n1.removerItem("GELADEIRA");
        System.out.println(n1.listarItens());
    }
}
