package br.com.tchefood.DAO;


import br.com.tchefood.banco.ConexaoMysql;
import br.com.tchefood.model.UsuarioModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    public ArrayList<UsuarioModel> listarTodos() {
        try {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection conexao = conexaoMysql.obterConexao();

            String sql = "SELECT nome, email FROM tb_usuario";
            PreparedStatement statement = conexao.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<UsuarioModel> listaUsuarios = new ArrayList<UsuarioModel>();

            while (resultSet.next()) {
                UsuarioModel usuarioModel = new UsuarioModel();
                usuarioModel.setNome(resultSet.getString("nome"));
                usuarioModel.setEmail(resultSet.getString("email"));


                listaUsuarios.add(usuarioModel);

            }

            resultSet.close();
            statement.close();
            conexao.close();

            return listaUsuarios;
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        return null;

    }

    public boolean cadastro (String nome, String email, String telefone, int sexo) {
        try {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection conexao = conexaoMysql.obterConexao();

            String sql = "INSERT INTO tb_usuario (nome, email, telefone, sexo) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, telefone);
            statement.setInt(4, sexo);

            int linhasAfetadas = statement.executeUpdate();

            statement.close();
            conexao.close();

            return linhasAfetadas > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean deletar(String nome, String email, String telefone, int sexo) {
        try {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection conexao = conexaoMysql.obterConexao();

            String sql = "DELETE FROM tb_usuario WHERE nome = ? AND email = ? AND telefone = ? AND sexo = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, telefone);
            statement.setInt(4, sexo);

            int linhasAfetadas = statement.executeUpdate();

            statement.close();
            conexao.close();

            return linhasAfetadas > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }


    public boolean atualizar(int id, String nome, String email, String telefone, int sexo) {

        try {
            ConexaoMysql conexaoMysql = new ConexaoMysql();
            Connection conexao = conexaoMysql.obterConexao();

            String sql = "UPDATE tb_usuario SET nome = ?, email = ?, telefone = ?, sexo = ? WHERE id = ?";
            PreparedStatement statement = conexao.prepareStatement(sql);

            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, telefone);
            statement.setInt(4, sexo);
            statement.setInt(5, id);

            int linhasAfetadas = statement.executeUpdate();

            statement.close();
            conexao.close();

            return linhasAfetadas > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

}
