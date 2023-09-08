package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;
import br.com.tchefood.model.CategoriaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {
    public void salvar(CategoriaModel usuario) throws SQLException, ClassNotFoundException {
    try {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        Connection conn = conexaoMysql.obterConexao();
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("INSERT INTO tb_categoria_produto(descricao) VALUES (?)");
        stmt.setString(1, usuario.getDescricao());
        stmt.executeUpdate();
    } catch (Exception e1){
        System.err.println(e1.getMessage());

    }
    }

    public static int informacaoProduto(int produtoId) {
        try {
            ConexaoMysql conexaoMYSQL = new ConexaoMysql();
            Connection con = conexaoMYSQL.obterConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            int categoria = -1;

            stmt = con.prepareStatement("SELECT id FROM tb_categoria_produto WHERE id = ?");
            stmt.setInt(1, produtoId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                categoria = rs.getInt("id");
            }

            return categoria;

        } catch(Exception informacaoProduto){
            throw new RuntimeException(informacaoProduto);
        }
    }

    public ArrayList<Produto> obterTodosProdutos() throws SQLException, ClassNotFoundException {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        Connection con = conexaoMysql.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = con.prepareStatement("SELECT id, descricao FROM tb_categoria_produto");
        rs = stmt.executeQuery();

        ArrayList<Produto> produtosList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String descricao = rs.getString("descricao");
            Produto produto = new Produto(id, descricao);
            produtosList.add(produto);
        }

        // Retorna a lista de produtos
        return produtosList;
    }


    public void atualizar(CategoriaModel categoria) {

    }
}
