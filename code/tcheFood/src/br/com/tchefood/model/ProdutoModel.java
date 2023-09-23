package br.com.tchefood.model;

public class ProdutoModel {
    private int id;
    private String descricao;
    private double valor;
    private String categoriaProduto;

    private String nome;

    private double preco;


    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getPreco() {
        return preco;
    }
}