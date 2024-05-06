package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;


public class SistemaDepositoGUI extends JFrame {

    private SistemaDeposito sistemaDeposito;

    public SistemaDepositoGUI() throws IOException {

        setTitle("Sistema de Gerenciamento de Estoque");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));


        sistemaDeposito = new SistemaDeposito();


        JButton cadastrarButton = new JButton("Cadastrar Item");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog("Digite o nome do item:");
                String quantidadeStr = JOptionPane.showInputDialog("Digite a quantidade do item:");
                tipoEstoqueCategoria tipo = tipoEstoqueCategoria.valueOf(JOptionPane.showInputDialog("Informe a categoria: "));
                int quantidade = Integer.parseInt(quantidadeStr);
                Item item = new Item(nome, quantidade,tipo);
                try {
                    boolean cadastrado = sistemaDeposito.cadastrarItem(item);
                    if (cadastrado) {
                        JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro: Item já cadastrado!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o item: " + ex.getMessage());
                }
            }
        });
        add(cadastrarButton);

        JButton pesquisarButton = new JButton("Pesquisar Item");
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog("Digite o nome do item:");
                try {
                    Item item = sistemaDeposito.pesquisarItem(nome);
                    JOptionPane.showMessageDialog(null, "Item encontrado: " + item);
                } catch (ItemInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, "Item não encontrado!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao pesquisar o item: " + ex.getMessage());
                }
            }
        });
        add(pesquisarButton);

        JButton removerButton = new JButton("Remover Item");
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog("Digite o nome do item a ser removido:");
                boolean removido = sistemaDeposito.removerItem(nome);
                if (removido) {
                    JOptionPane.showMessageDialog(null, "Item removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: Item não encontrado!");
                }
            }
        });
        add(removerButton);

        JButton listarButton = new JButton("Listar Itens do Estoque");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Item> itens = sistemaDeposito.listarItens();
                StringBuilder listaItens = new StringBuilder();
                for (Item item : itens) {
                    listaItens.append(item).append("\n");
                }
                JOptionPane.showMessageDialog(null, listaItens.toString());
            }
        });
        add(listarButton);

        JButton contarButton = new JButton("Contar Quantidade do Estoque");
        contarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantidade = sistemaDeposito.contarQuantidadeDoEstoque();
                JOptionPane.showMessageDialog(null, "Quantidade total de itens no estoque: " + quantidade);
            }
        });
        add(contarButton);
        setVisible(true);
        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GravadorDeDados gravador = new GravadorDeDados();
                try {
                    gravador.salvaEstoque(sistemaDeposito.getItens());
                    JOptionPane.showMessageDialog(null, "Dados do estoque salvos com sucesso!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar os dados do estoque: " + ex.getMessage());
                }
            }
        });
        add(salvarButton);

        JButton sairButton = new JButton("Sair");
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(sairButton);

        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new SistemaDepositoGUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    class BemVindoJanela extends JFrame {

        public BemVindoJanela() {
            // Configurações da janela de boas-vindas
            setTitle("Bem-vindo ao Aplicativo de Depósito");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Adiciona uma etiqueta com a mensagem de boas-vindas ao centro
            JLabel mensagemLabel = new JLabel("Bem-vindo ao aplicativo de depósito!");
            mensagemLabel.setHorizontalAlignment(JLabel.CENTER);
            add(mensagemLabel, BorderLayout.CENTER);

            // Adiciona um ícone de depósito à esquerda
            ImageIcon iconDeposito = new ImageIcon("deposito_icon.png"); // Substitua "deposito_icon.png" pelo caminho do seu arquivo de ícone
            JLabel iconLabel = new JLabel(iconDeposito);
            add(iconLabel, BorderLayout.WEST);

            // Adiciona um botão "Continuar" na parte inferior
            JButton continuarButton = new JButton("Continuar");
            continuarButton.addActionListener(e -> {
                dispose(); // Fecha a janela de boas-vindas
                abrirJanelaPrincipal(); // Abre a janela principal do aplicativo
            });
            add(continuarButton, BorderLayout.SOUTH);

            // Centraliza a janela na tela
            setLocationRelativeTo(null);

            // Exibe a janela de boas-vindas
            setVisible(true);
        }

        private void abrirJanelaPrincipal() {
            // Abre a janela principal do aplicativo de gerenciamento de estoque
            try {
                new SistemaDepositoGUI();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
