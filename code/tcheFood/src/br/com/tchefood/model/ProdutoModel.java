package br.com.tchefood.model;

public class ProdutoModel {
    int id;
    CategoriaModel categoria_produto_id;
    String descricao;
    double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoriaModel getCategoria_produto_id() {
        return categoria_produto_id;
    }

    public void setCategoria_produto_id(CategoriaModel categoria_produto_id) {
        this.categoria_produto_id = categoria_produto_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
