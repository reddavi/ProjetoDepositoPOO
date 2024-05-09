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

        ImageIcon cadastrar = new ImageIcon("cadastro.png");
        JButton cadastrarButton = new JButton("Cadastrar Item",cadastrar);
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog("Digite o nome do item:");
                String quantidadeStr = JOptionPane.showInputDialog("Digite a quantidade do item:");
                tipoEstoqueCategoria tipo = tipoEstoqueCategoria.valueOf(JOptionPane.showInputDialog("Informe a categoria: "));
                int quantidade = Integer.parseInt(quantidadeStr);
                Item item = new Item(nome, quantidade, tipo);
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

        //Função pesquisar
        ImageIcon pesquisar = new ImageIcon("pesquisa.png");
        JButton pesquisarButton = new JButton("Pesquisar Item",pesquisar);
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

        //Função remover
        ImageIcon remover = new ImageIcon("remover.png");
        JButton removerButton = new JButton("Remover Item",remover);
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

        //Função Listar
        ImageIcon listaItens = new ImageIcon("listar.png");
        JButton listarButton = new JButton("Listar Itens do Estoque", listaItens);
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

        ///Função contar
        ImageIcon contaQuant = new ImageIcon("contaQuantidade.png");
        JButton contarButton = new JButton("Contador Estoque", contaQuant);
        contarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Item> itens = sistemaDeposito.listarItens();
                StringBuilder mensagem = new StringBuilder();
                mensagem.append("Quantidade total de itens no estoque: ").append(sistemaDeposito.contarQuantidadeDoEstoque()).append("\n\n");

                for (Item item : itens) {
                    mensagem.append(item.getNome()).append(": ").append(item.getQuantidade()).append("\n");
                }

                JOptionPane.showMessageDialog(null, mensagem.toString());
            }
        });
        add(contarButton);
        setVisible(true);

        ///Interface de salvar
        ImageIcon salve = new ImageIcon("salvar.png");
        JButton salvarButton = new JButton("Salvar", salve);
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
        //Função sair
        ImageIcon sair = new ImageIcon("sair.png");
        JButton sairButton = new JButton("Sair", sair);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(sairButton);
        setVisible(true);

        ImageIcon zerar = new ImageIcon("zerar.png");
        JButton zerarButton = new JButton("Limpar estoque",zerar);
        zerarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacao = JOptionPane.showConfirmDialog(null,"Tem certeza que quer zerar o estoque?","Confirmação",JOptionPane.YES_NO_OPTION);
                if(confirmacao == JOptionPane.YES_NO_OPTION){
                    sistemaDeposito.zerarEstoque();
                    JOptionPane.showMessageDialog(null,"Estoque zerado com sucesso!");
                }
            }
        });
        add(zerarButton);
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
}