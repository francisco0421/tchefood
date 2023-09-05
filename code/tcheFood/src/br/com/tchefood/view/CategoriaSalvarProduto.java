package br.com.tchefood.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriaSalvarProduto {
    private JPanel panel1;
    private JPanel jpanel;
    private JTextField textField1;




    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JButton getSALVARButton() {
        return SALVARButton;
    }

    public void setSALVARButton(JButton SALVARButton) {
        this.SALVARButton = SALVARButton;
    }

    public JLabel getJLnomeProduto() {
        return JLnomeProduto;
    }

    public void setJLnomeProduto(JLabel JLnomeProduto) {
        this.JLnomeProduto = JLnomeProduto;
    }

    private JButton SALVARButton;
    private JLabel JLnomeProduto;
    public CategoriaSalvarProduto() {
    SALVARButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categ

                    categoriaDAP
        }
    });
}
}
