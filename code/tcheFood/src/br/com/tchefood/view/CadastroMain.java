package br.com.tchefood.view;

import br.com.tchefood.DAO.UsuarioDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroMain {
    private JTextField textField1Nome;
    private JTextField textField2Email;
    private JTextField textField3Telefone;
    private JRadioButton femininoRadioButton;
    private JRadioButton masculinoRadioButton;
    private JButton cadastrarButton;
    private JButton voltarButton;
    private JLabel CADASTRARPESSOASLabel;
    private JLabel nomeLabel;
    private JLabel eMailLabel;
    private JLabel telefoneLabel;
    private JLabel sexoLabel;
    private javax.swing.JPanel JPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CadastroMain");
        frame.setContentPane(new CadastroMain().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public CadastroMain() {

        ButtonGroup bgGrupo = new ButtonGroup();

        bgGrupo.add(femininoRadioButton);
        bgGrupo.add(masculinoRadioButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textField1Nome.getText();
                String email = textField2Email.getText();
                String telefone = textField3Telefone.getText();
                int sexo = 0;

                if (femininoRadioButton.isSelected()) {
                    sexo = 1;
                } else if (masculinoRadioButton.isSelected()) {
                    sexo = 2;
                }

                if (sexo == 0) {
                    JOptionPane.showMessageDialog(cadastrarButton, "Selecione uma opção de sexo");
                    return;
                }

                UsuarioDAO UsuarioDAO = new UsuarioDAO();
                boolean cadastradoComSucesso = UsuarioDAO.cadastro(nome, email, telefone, sexo);

                if (cadastradoComSucesso) {
                    JOptionPane.showMessageDialog(cadastrarButton, "Cadastro realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(cadastrarButton, "Erro ao cadastrar.");
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textField1Nome.setText("");
                textField2Email.setText("");
                textField3Telefone.setText("");
                bgGrupo.clearSelection();

            }
        });


    }
}
