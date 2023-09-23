package br.com.tchefood.view;

import br.com.tchefood.DAO.ProdutoDAO;
import br.com.tchefood.model.CategoriaModel;
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

        comboBoxCategoria.addItem("Pizza");
        comboBoxCategoria.addItem("Xis");
        comboBoxCategoria.addItem("Batata frita");


        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ProdutoModel produto = new ProdutoModel();

                produto.setDescricao(tfDescricao.getText());
                produto.setNome(tfProduto.getText());
                produto.setCategoriaProduto(String.valueOf(comboBoxCategoria.getSelectedItem()));
                produto.setValor(Double.parseDouble(tfPreco.getText()));

                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.salvar(produto);
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