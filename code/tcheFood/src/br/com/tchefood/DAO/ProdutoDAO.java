package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;
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
            stmt = con.prepareStatement("INSERT INTO tb_produto(id, categoria_produto, nome, descricao, preco) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getCategoriaProduto());
            stmt.setString(3, produto.getNome());
            stmt.setString(4, produto.getDescricao());
            stmt.setDouble(5, produto.getValor());
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
            model.setValor(rs.getDouble("preco"));


            produtosList.add(model);
        }

        return produtosList;
    }


    public static class InformacaoProdutoDAO {
        public static String obterDescricaoPorID(int idBuscado) throws SQLException, ClassNotFoundException {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection conn = conexaoMysql.obterConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                stmt = conn.prepareStatement("SELECT descricao FROM tb_categoria_produto WHERE id = ?");
                stmt.setInt(1, idBuscado);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    return rs.getString("descricao");
                } else {
                    return "Produto não encontrado";
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                conexaoMysql.obterConexao();
            }
        }


    }
}