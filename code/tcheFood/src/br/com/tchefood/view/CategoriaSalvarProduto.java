    package br.com.tchefood.view;

    import br.com.tchefood.DAO.CategoriaDAO;
    import br.com.tchefood.DAO.ProdutoDAO;
    import br.com.tchefood.DAO.UsuarioDAO;
    import br.com.tchefood.banco.ConexaoMysql;
    import br.com.tchefood.model.CategoriaModel;
    import br.com.tchefood.model.ProdutoModel;
    import br.com.tchefood.model.UsuarioModel;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
    import java.util.ArrayList;


    public class CategoriaSalvarProduto {

        private JTextField textField1;
        private JTextField textField2;
        private JLabel jlDescricao;
        private JLabel jlID;
        private JButton SALVARButton;
        private JButton EXCLUIRButton;
        private JButton BUSCARButton;
        private JButton SALVAREDICAOButton;
        private JPanel jpCategoriaProduto;
        private JTable table1;
        private JButton LISTARButton;
        private DefaultTableModel tableModel;

        public CategoriaSalvarProduto() {
        SALVARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                CategoriaModel categoria = new CategoriaModel();

                String descricao = textField1.getText().trim();
                if (descricao.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "A descrição do produto é obrigatória", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    categoria.setDescricao(descricao);

                    try {
                        categoriaDAO.salvar(categoria);
                        JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
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
                        ex.printStackTrace();
                    }
                }
            });
            BUSCARButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ProdutoDAO.InformacaoProdutoDAO informacaoProdutoDAO = new ProdutoDAO.InformacaoProdutoDAO();
                    int idBuscado = Integer.parseInt(textField2.getText());

                    try {

                        String descricao = ProdutoDAO.InformacaoProdutoDAO.obterDescricaoPorID(idBuscado);

                        textField1.setText(descricao);
                    } catch (SQLException ex) {

                        JOptionPane.showMessageDialog(null, "Erro ao buscar descrição: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException ex) {

                        JOptionPane.showMessageDialog(null, "Erro de classe: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            SALVAREDICAOButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CategoriaDAO categoriaDAO = new CategoriaDAO();
                    CategoriaModel categoria = new CategoriaModel();

                    String descricao = textField1.getText().trim();
                    int idEditar = Integer.parseInt(textField2.getText());

                    try {
                        categoria.setId(idEditar);
                        categoria.setDescricao(descricao);

                        categoriaDAO.atualizar(categoria);
                        JOptionPane.showMessageDialog(null, "Produto editado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao editar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            tableModel = new DefaultTableModel();
            tableModel.addColumn("descricao");
            tableModel.addColumn("id");
            table1.setModel(tableModel);

            LISTARButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    CategoriaDAO categoriaDAO = new CategoriaDAO();
                    ArrayList<CategoriaModel> listaCategoria = categoriaDAO.listarCategoria();

                    for(CategoriaModel categoria : listaCategoria){
                        tableModel.addRow(new Object[]{categoria.getDescricao(), categoria.getId()});
                    }
                }
            });
        }



        public static void main(String[] args) {

                JFrame frame = new JFrame("CategoriaSalvarProduto");
                frame.setContentPane(new CategoriaSalvarProduto().jpCategoriaProduto);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

        }
    }
