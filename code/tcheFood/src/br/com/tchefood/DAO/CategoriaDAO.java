package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;
import br.com.tchefood.model.CategoriaModel;

import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static int getCategoriaExcluir(int pedidoId) {
        try {
            ConexaoMysql conexaoMYSQL = new ConexaoMysql();
            Connection con = conexaoMYSQL.obterConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            int categoria = -1;

            stmt = con.prepareStatement("SELECT id FROM tb_categoria_produto WHERE id = ?");
            stmt.setInt(1, pedidoId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                categoria = rs.getInt("id");
            }

            return categoria;

        } catch(Exception GETcategoriaExcluir){
            throw new RuntimeException(GETcategoriaExcluir);
        }
    }
}
