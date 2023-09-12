package br.com.tchefood.view;

import br.com.tchefood.DAO.UsuarioDAO;
import br.com.tchefood.banco.ConexaoMysql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtualizarMain {
    private JTextField textField1id;
    private JTextField textField2nome;
    private JTextField textField3email;
    private JTextField textField4telefone;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femininoRadioButton;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JButton buscarButton;
    private javax.swing.JPanel JPanel;
    private JLabel IDLabel;
    private JLabel nomeLabel;
    private JLabel eMailLabel;
    private JLabel telefoneLabel;
    private JLabel sexoLabel;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("AtualizarMain");
        frame.setContentPane(new AtualizarMain().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public AtualizarMain() {

        ButtonGroup bgGrupo = new ButtonGroup();

        bgGrupo.add(femininoRadioButton);
        bgGrupo.add(masculinoRadioButton);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1id.getText());
                String nome = textField2nome.getText();
                String email = textField3email.getText();
                String telefone = textField4telefone.getText();
                int sexo = masculinoRadioButton.isSelected() ? 1 : 2;

                UsuarioDAO UsuarioDAO = new UsuarioDAO();

                boolean atualizadoComSucesso = UsuarioDAO.atualizar(id, nome, email, telefone, sexo);

                if (atualizadoComSucesso) {
                    JOptionPane.showMessageDialog(salvarButton, "Registro atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(salvarButton, "Erro ao atualizar o registro.");
                }
            }
        });


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1id.getText());

                try {
                    ConexaoMysql conexaoMysql = new ConexaoMysql();
                    Connection conexao = conexaoMysql.obterConexao();

                    String sql = "SELECT * FROM tb_usuario WHERE id = ?";

                    PreparedStatement statement = conexao.prepareStatement(sql);
                    statement.setInt(1, id);

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        textField2nome.setText(resultSet.getString("nome"));
                        textField3email.setText(resultSet.getString("email"));
                        textField4telefone.setText(resultSet.getString("telefone"));

                        String sexo = resultSet.getString("sexo");

                        if ("Masculino".equals(sexo)) {
                            masculinoRadioButton.setSelected(true);
                        } else if ("Feminino".equals(sexo)) {
                            femininoRadioButton.setSelected(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(buscarButton, "ID não encontrado.");
                    }

                    resultSet.close();
                    statement.close();
                    conexao.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(buscarButton, "Erro ao buscar: " + ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



    }

}