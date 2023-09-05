package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoriaDAO {
    public void salvar(Cate usuario) throws SQLException, ClassNotFoundException {
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        Connection conn = conexaoMysql.obterConexao();
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("INSERT INTO tb_usuario(email, senha, nome, sobrenome, telefone) VALUES (?,?,?,?,?)");
        stmt.setString(1, usuario.getEmail());
        stmt.setString(2, usuario.getSenha());
        stmt.setString(3, usuario.getNome());
        stmt.setString(4, usuario.getSobrenome());
        stmt.setString(5, usuario.getTelefone());
        stmt.executeUpdate();
    }
}
