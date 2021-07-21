package br.com.projeto.dao;

import br.com.projeto.Resources.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe criada para 
 * @author Adilson Luz
 * @since Classe Criada em 05/07/2021, 19:01:29
 */
public class ProdutosDAO {
    
    private Connection conn;

    public ProdutosDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    //Método para cadastrar Produtos
    public void cadastrarProdutos(Produtos obj) {
        try {
            //1 Passo - criar comando SQL
            String sql = "INSERT INTO tb_produtos (descricao, preco, qtd_estoque, for_id)"
                       + "VALUES (?,?,?,?)";

            //2 Passo - Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd());
            stmt.setInt(4, obj.getFornecedor().getId());

            //3 Passo - Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    //Método para alterar Produtos
    public void alterarProdutos(Produtos obj) {
        try {
            //1 Passo - criar comando SQL
            String sql = "UPDATE tb_produtos SET descricao=?, preco=?, qtd_estoque=?, for_id=? WHERE id=?";

            //2 Passo - Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.setInt(5, obj.getId());

            //3 Passo - Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    //Método para excluir Produtos
    public void excluirProdutos(Produtos obj) {
        try {
            //1 Passo - criar comando SQL
            String sql = "DELETE FROM tb_produtos WHERE id = ?";

            //2 Passo - Conectar no banco da dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            //3 Passo - Executar o comando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com Sucesso!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
        }
    }//Fim do metodo

    //Método que lista todos os Produtos
    public List<Produtos> listarProdutos() {
        try {
            //1 Passo criar a lista
            List<Produtos> lista = new ArrayList<>();

            //2 Passo - Criar o SQL, organizar e executar
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                        +"inner join tb_fornecedores as f on (p.for_id = f.id)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);

                lista.add(obj);

            }
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
            return null;
        }

    }

    //Método consultar Produtos por nome
    public Produtos consultarProdutosPorNome(String nome) {
        try {
            //1 Passo - Criar o SQL, organizar e executar
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                        +"inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);

            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            return null;
        }
    }

    //Método que busca Produtos por nome
    public List<Produtos> buscarProdutosPorNome(String nome) {
        try {
            //1 Passo criar a lista
            List<Produtos> lista = new ArrayList<>();

            //2 Passo - Criar o SQL, organizar e executar
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                        +"inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);

                lista.add(obj);

            }
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops, aconteceu o erro " + e);
            return null;
        }
    }
    
    //Método consultar Produtos por nome
    public Produtos consultarProdutosPorCodigo(int id) {
        try {
            //1 Passo - Criar o SQL, organizar e executar
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
                        +"inner join tb_fornecedores as f on (p.for_id = f.id) where p.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);

            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            return null;
        }
    }
    
    //Metodo que da baixa no estoque
    public void baixaEstoque(int id, int qtd_nova){
        try {
            //1 Passo - Comando SQL
            String sql = "UPDATE tb_produtos SET qtd_estoque=? WHERE id=?";
            
            //2 Passo - conectar com banco de dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            
            stmt.execute();
            stmt.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
    
    //Método que retorna o estoque atual de um produto
    public int retornaEstoqueAtual(int id){
        try {
            int qtd_estoque = 0;
            
            //1 Passo - Comando SQL
            String sql = "SELECT qtd_estoque FROM tb_produtos WHERE id=?";
            
            //2 Passo - Organizar e executar o SQL
            PreparedStatement stmt  = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Produtos p = new Produtos();
                
                qtd_estoque = (rs.getInt("qtd_estoque"));
            }
            return qtd_estoque;            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Metodo que adiciona uma quantidade no estoque
    public void adicionarEstoque(int id, int qtd_nova){
        try {
            //1 Passo - Comando SQL
            String sql = "UPDATE tb_produtos SET qtd_estoque=? WHERE id=?";
            
            //2 Passo - conectar com banco de dados e organizar o comando SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            
            stmt.execute();
            stmt.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
    

}//fim da classe
