package br.com.projeto.model;

/**
 * Classe criada para 
 * @author Adilson Luz
 * @since Classe Criada em 05/07/2021, 18:05:27
 */
public class Produtos {
    
    //Atributos
    private int id;
    private String descricao;
    private double preco;
    private int qtd;
    
    private Fornecedores fornecedor;
    
    //Construtor vazio
    public Produtos() {
    }
    
    //Método construtor
    public Produtos(int id, String descricao, double preco, int qtd, Fornecedores fornecedor) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.qtd = qtd;
        this.fornecedor = fornecedor;
    }
    
    //Getters & Setters

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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }
    

}//fim da classe
