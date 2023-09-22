package br.com.tchefood.view;

import br.com.tchefood.model.ProdutoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenciamentoProduto {
    private JButton salvarButton;
    private JComboBox comboBoxCategoria;
    private JTextField tfDescricao;
    private JTextField tfProduto;
    private JLabel jlDescricao;
    private JLabel jlCategoria;
    private JLabel jlProduto;
    private JPanel jpGerenciamentoProduto;
    private JLabel jlPreco;
    private JTextField tfPreco;

    public GerenciamentoProduto() {
        comboBoxCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox <String> comboBoxCategoria = new JComboBox<>();

                comboBoxCategoria.addItem("Pizza");
                comboBoxCategoria.addItem("Xis");
                comboBoxCategoria.addItem("Batata frita");

                ProdutoModel produto = new ProdutoModel();
                produto.setValor(Float.parseFloat(tfProduto.getText()));

            }
        });
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton salvarButton = new JButton();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GerenciamentoProduto");
        frame.setContentPane(new GerenciamentoProduto().jpGerenciamentoProduto);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}