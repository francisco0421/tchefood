package br.com.tchefood.model;

public class ProdutoModel {
    private int id;
    private String descricao;
    private float preco;
    private String CategoriaProduto;

    private CategoriaModel categoria;

    public String getCategoriaProduto() {
        return CategoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        CategoriaProduto = categoriaProduto;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
