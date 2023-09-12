package br.com.tchefood.view;

import br.com.tchefood.DAO.ProdutoDAO;
import br.com.tchefood.model.ProdutoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class FormaDePagamento {
    private JComboBox jcbForma;
    private JLabel labelText;
    private JLabel labelTitle;
    private JPanel jpformadepagamento;
    public JTextField tfproduto;
    private JTable tabelaProdutos;
    private JTextField tfquantidade;
    private JButton jbadicionar;
    private JButton COMPRARButton;
    private DefaultTableModel modelTable;

    public FormaDePagamento() {
        modelTable = new DefaultTableModel();
        modelTable.addColumn("id");
        modelTable.addColumn("descricao");
        modelTable.addColumn("preco");


        jbadicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(tfproduto.getText());

                //String quantidade = tfquantidade.getText();

                try {
                    for (ProdutoModel pm:ProdutoDAO.obterProdutos(id))
                        modelTable.addRow(new Object[]{pm.getId(), pm.getDescricao(), pm.getPreco()});
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }


                tabelaProdutos.setModel(modelTable);
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("FormaDePagamento");
        frame.setContentPane(new FormaDePagamento().jpformadepagamento);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
