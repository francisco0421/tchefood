package br.com.tchefood.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormaDePagamento {
    private JComboBox jcbForma;
    private JLabel labelText;
    private JLabel labelTitle;
    private JPanel jpformadepagamento;
    private JTextField tfproduto;
    private JTable tabelaProdutos;
    private JTextField tfquantidade;
    private JButton jbadicionar;
    private JButton COMPRARButton;
    private DefaultTableModel modelTable;

    public FormaDePagamento() {
        modelTable = new DefaultTableModel();
        modelTable.addColumn("produto");
        modelTable.addColumn("quantidade");
        tabelaProdutos.setModel(modelTable);

        jbadicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String produto = tfproduto.getText();
                String quantidade = tfquantidade.getText();
                if(!produto.isEmpty() && !quantidade.isEmpty()){
                    modelTable.addRow(new Object[]{produto, quantidade});
                    tfproduto.setText(null);
                    tfquantidade.setText(null);
                }
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
