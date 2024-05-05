package org.example;

import java.io.IOException;

public class Sistemateste {
    public Sistemateste() {
    }

    public static void main(String[] args) throws ItemInexistenteException, IOException{
        SistemaDeposito sistemaDeposito = new SistemaDeposito();


        //System.out.println(sistemaDeposito.pesquisarItem("GELADEIRA"));

        sistemaDeposito.zerarEstoque();
        System.out.println(sistemaDeposito.listarItens());

    }
}
