package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;
import br.com.tchefood.model.CategoriaModel;
import br.com.tchefood.model.ProdutoModel;

import java.sql.*;
import java.util.ArrayList;

public class ProdutoDAO {
    public void salvar(ProdutoModel produto){
        try {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection con = null;

            con = conexaoMysql.obterConexao();

            PreparedStatement stmt = null;
            stmt = con.prepareStatement("INSERT INTO tb_produto(descricao) VALUES (?)");
            stmt.setString(1, produto.getDescricao());
            stmt.executeUpdate();
        } catch (Exception e1){
            System.err.println(e1.getMessage());
        }
    }

    public ProdutoModel obterProdutoPorID(int produtoID) {
        ProdutoModel produto = null;
        try {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection con = null;
            con = conexaoMysql.obterConexao();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT id, descricao FROM tb_produto WHERE id = (?)");
            stmt.setInt(1, produtoID);
            stmt.executeUpdate();
            ResultSet rs = stmt.getResultSet();
            rs.next();
            produto = new ProdutoModel();
            produto.setId(rs.getInt("id"));
            produto.setDescricao(rs.getString("descricao"));
        } catch (Exception e1) {
            System.err.println(e1.getMessage());
        }
        return produto;
    }

}