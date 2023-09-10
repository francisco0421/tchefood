package br.com.tchefood.DAO;

import br.com.tchefood.banco.ConexaoMysql;
import br.com.tchefood.model.CategoriaModel;
import br.com.tchefood.view.CategoriaSalvarProduto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


}
