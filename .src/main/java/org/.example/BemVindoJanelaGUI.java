package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BemVindoJanelaGUI extends JFrame {

    public BemVindoJanelaGUI() {

        setTitle("Bem-vindo ao Aplicativo de Depósito");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel mensagemLabel = new JLabel("Bem-vindo ao aplicativo de depósito!");
        mensagemLabel.setHorizontalAlignment(JLabel.CENTER);
        add(mensagemLabel, BorderLayout.CENTER);


        ImageIcon iconDeposito = new ImageIcon("deposito.png");
        JLabel iconLabel = new JLabel(iconDeposito);
        add(iconLabel, BorderLayout.WEST);

        JButton continuarButton = new JButton("Continuar");
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela de boas-vindas
                abrirJanelaPrincipal();
            }
        });
        add(continuarButton, BorderLayout.SOUTH);


        setLocationRelativeTo(null);


        setVisible(true);
    }

    private void abrirJanelaPrincipal() {
        try {
            new SistemaDepositoGUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BemVindoJanelaGUI();
            }
        });
    }
}