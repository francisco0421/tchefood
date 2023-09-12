package br.com.tchefood.view;

import br.com.tchefood.DAO.UsuarioDAO;
import br.com.tchefood.model.UsuarioModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListarMain {
    private JLabel LISTARUSUÁRIOSLabel;
    private JTable table1;
    private JButton listarButton;
    private javax.swing.JPanel JPanel;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ListarMain");
        frame.setContentPane(new ListarMain().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ListarMain() {

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
        table1.setModel(tableModel);
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                ArrayList<UsuarioModel> listaUsuarios = usuarioDAO.listarTodos();

                for(UsuarioModel usuario : listaUsuarios){
                    tableModel.addRow(new Object[]{usuario.getNome(), usuario.getEmail()});
                }
            }
        });
    }

}
