package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GravadorDeDados {
    public static final String ESTOQUE = "estoque.txt";

    public HashMap<String, Item> recuperarItens() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(ESTOQUE));
            return (HashMap<String, Item>) in.readObject();
        } catch (Exception e) {
            System.out.println("Não foi possível recuperar o estoque");
            throw new IOException("Não foi possível recuperar os dados do arquivo " + ESTOQUE);

        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public void salvaEstoque(Map<String, Item> itens) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(ESTOQUE));
            out.writeObject(itens);
            System.out.println("Estoque carregado e salvo com sucesso!! ");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Erro ao salvar os dados do estoque. " + ESTOQUE);
        }
    }

    public static void imprimirItensSalvos() {
        System.out.println("Itens salvos:");
        try (BufferedReader br = new BufferedReader(new FileReader(ESTOQUE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao imprimir itens salvos: " + e.getMessage());
        }
    }
}
