package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;
import br.com.tchefood.model.CategoriaModel;
import br.com.tchefood.model.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {
    public void salvar(CategoriaModel produto) throws SQLException, ClassNotFoundException {
        try {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection conn = conexaoMysql.obterConexao();

            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO tb_categoria_produto(descricao) VALUES (?)");
            stmt.setString(1, produto.getDescricao());
            stmt.executeUpdate();
        } catch (Exception e1) {
            System.err.println(e1.getMessage());

        }
    }

    public void atualizar(CategoriaModel categoria) throws SQLException, ClassNotFoundException {
        try {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection conn = conexaoMysql.obterConexao();
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("UPDATE tb_categoria_produto SET descricao = ? WHERE id = ?");
            stmt.setString(1, categoria.getDescricao());
            stmt.setInt(2, categoria.getId());

            stmt.executeUpdate();


        } catch (Exception e1) {
            System.err.println(e1.getMessage());
        }

    }

    public ArrayList<CategoriaModel> listarCategoria() {
        /*indo pegar/ fazer minha listaaa -> henrique*/
        try {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection conexao = conexaoMysql.obterConexao();

            String sqlParaPegarMinhaDescricao = "SELECT descricao, id FROM tb_categoria_produto";
            PreparedStatement statement = conexao.prepareStatement(sqlParaPegarMinhaDescricao);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<CategoriaModel> listaCategoria = new ArrayList<CategoriaModel>();

            while (resultSet.next()) {
                CategoriaModel categoriaModel = new CategoriaModel();
                categoriaModel.setDescricao(resultSet.getString("descricao"));
                categoriaModel.setId(resultSet.getInt("id"));


                listaCategoria.add(categoriaModel);

            }

            resultSet.close();
            statement.close();
            conexao.close();

            return listaCategoria;
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        return null;

    }
}