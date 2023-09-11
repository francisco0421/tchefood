package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;
import br.com.tchefood.model.ProdutoModel;
import br.com.tchefood.view.FormaDePagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
    public static ArrayList<ProdutoModel> obterProdutos(int id) throws SQLException, ClassNotFoundException, SQLException {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        Connection con = conexaoMysql.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = con.prepareStatement("SELECT id, descricao, preco FROM tb_produto WHERE id = ?");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        ArrayList<ProdutoModel> produtosList = new ArrayList<>();

        while (rs.next()) {
            ProdutoModel model = new ProdutoModel();
            model.setId(rs.getInt("id"));
            model.setDescricao(rs.getString("descricao"));
            model.setPreco(rs.getFloat("preco"));


            produtosList.add(model);
        }

        return produtosList;
    }
}
