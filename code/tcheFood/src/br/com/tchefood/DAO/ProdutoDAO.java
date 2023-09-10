package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;
import br.com.tchefood.view.FormaDePagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
    public ArrayList<String> obterProdutos() throws SQLException, ClassNotFoundException, SQLException {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        Connection con = conexaoMysql.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = con.prepareStatement("SELECT descricao FROM tb_produto WHERE id = ?");
        stmt.setInt(1, );
        rs = stmt.executeQuery();

        ArrayList<String> produtosList = new ArrayList<>();

        while (rs.next()) {

            String description = rs.getString("descricao");

            produtosList.add(description);
        }

        return null;
    }
}
