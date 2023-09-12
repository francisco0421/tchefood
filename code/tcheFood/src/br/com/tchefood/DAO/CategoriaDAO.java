package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;
import br.com.tchefood.model.CategoriaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoriaDAO {
    public void salvar(CategoriaModel usuario) throws SQLException, ClassNotFoundException {
<<<<<<< HEAD
        ConexaoMysql conexaoMysql = new ConexaoMysql();
        Connection conn = conexaoMysql.obterConexao();
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("INSERT INTO tb_categoria_produto(descricao) VALUES (?,?,?,?,?)");
        stmt.setString(1, usuario.getDescricao());
        stmt.executeUpdate();
    }
}
=======
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

    public void atualizar(CategoriaModel categoria) throws SQLException, ClassNotFoundException {
        try {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection conn = conexaoMysql.obterConexao();
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("UPDATE tb_categoria_produto SET descricao = ? WHERE id = ?");
            stmt.setString(1, categoria.getDescricao());
            stmt.setInt(2,categoria.getId());

            stmt.executeUpdate();


        } catch (Exception e1){
            System.err.println(e1.getMessage());
        }

        }
    }

>>>>>>> e87abbbdb7221cf5111f547709031600ac0786a2
