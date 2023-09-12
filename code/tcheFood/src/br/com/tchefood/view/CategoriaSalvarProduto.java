package br.com.tchefood.view;

import br.com.tchefood.DAO.CategoriaDAO;
import br.com.tchefood.DAO.InformacaoProdutoDAO;
import br.com.tchefood.banco.ConexaoMysql;
import br.com.tchefood.model.CategoriaModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
    private JButton EXCLUIRButton;

    private JTextField textField2;
    private JButton BUSCARButton;
    private JButton SALVAREDIÇÃOButton;
    private JLabel IDCasoVocêQueiraLabel;
    private JTable fasdf;

    public CategoriaSalvarProduto() {
        SALVARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                CategoriaModel categoria = new CategoriaModel();

                // Verifique se o campo de descrição está vazio antes de salvar
                String descricao = textField1.getText().trim(); // Remova espaços em branco desnecessários
                if (descricao.isEmpty()) {
                    // Exiba uma mensagem de erro se a descrição estiver vazia
                    JOptionPane.showMessageDialog(null, "A descrição do produto é obrigatória", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    categoria.setDescricao(descricao);

                    try {
                        categoriaDAO.salvar(categoria);
                        // Se a operação de salvamento for bem-sucedida, exiba uma mensagem de sucesso
                        JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        // Em caso de erro SQL, exiba uma mensagem de falha
                        JOptionPane.showMessageDialog(null, "Erro ao adicionar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        });
        EXCLUIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idUsuario;
                try {
                    idUsuario = Integer.parseInt(textField2.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID de usuário inválido.");
                    return;
                }

                try (Connection conn = new ConexaoMysql().obterConexao();
                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM tb_categoria_produto WHERE id = ?")) {

                    stmt.setInt(1, idUsuario);
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhuma categoria foi excluída.");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace(); // Trate a exceção de maneira apropriada, por exemplo, exibindo uma mensagem de erro.
                }
            }
        });
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                InformacaoProdutoDAO informacaoProdutoDAO = new InformacaoProdutoDAO();
                // 1. Obter o ID digitado pelo usuário
                int idBuscado = Integer.parseInt(textField2.getText()); // Suponha que textField2 seja o campo de texto onde o usuário insere o ID.

                try {
                    // 2. Consultar o banco de dados com base no ID
                    String descricao = InformacaoProdutoDAO.obterDescricaoPorID(idBuscado); // Substitua informacaoProdutoDAO pelo nome da sua classe DAO adequada.

                    // 3. Preencher o campo de texto de descrição com o valor obtido
                    textField1.setText(descricao); // Suponha que textField1 seja o campo de texto onde você deseja exibir a descrição.
                } catch (SQLException ex) {
                    // Trate a exceção de forma adequada, como mostrar uma mensagem de erro ao usuário
                    JOptionPane.showMessageDialog(null, "Erro ao buscar descrição: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    // Trate a exceção de forma adequada, como mostrar uma mensagem de erro ao usuário
                    JOptionPane.showMessageDialog(null, "Erro de classe: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        SALVAREDIÇÃOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                CategoriaModel categoria = new CategoriaModel();

                // Verifique se o campo de descrição está vazio antes de salvar a edição
                String descricao = textField1.getText().trim(); // Remova espaços em branco desnecessários
                int idEditar = Integer.parseInt(textField2.getText());

                try {
                    // Defina o ID da categoria que você deseja editar
                    categoria.setId(idEditar);

                    // Configure a nova descrição para a categoria
                    categoria.setDescricao(descricao);

                    categoriaDAO.atualizar(categoria); // Suponha que você tenha um método "atualizar" no seu DAO para atualizar um produto
                    // Se a operação de atualização for bem-sucedida, exiba uma mensagem de sucesso
                    JOptionPane.showMessageDialog(null, "Produto editado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    // Em caso de erro SQL, exiba uma mensagem de falha
                    JOptionPane.showMessageDialog(null, "Erro ao editar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CategoriaSalvarProduto");
        frame.setContentPane(new CategoriaSalvarProduto().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
