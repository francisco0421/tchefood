package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InformacaoProdutoDAO {
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
                // Se a consulta retornar um resultado, obtenha a descrição
                return rs.getString("descricao");
            } else {
                // Se não houver resultados, retorne uma mensagem de erro ou valor padrão
                return "Produto não encontrado";
            }
        } finally {
            // Certifique-se de fechar os recursos (stmt, rs e conexão) no bloco finally
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            conexaoMysql.obterConexao(); // Feche a conexão corretamente
        }
    }


}
