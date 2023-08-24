
package data;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cria o objeto de conexão e manipulação do banco de dados 
 * @author caiocesar
 */

public class FilmesDao 
{
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    public String url = "jdbc:mysql://localhost:3306/cenaflix";
    public String user = "root";
    public String password = "password";
    
    //Conectar
    /**
    * Inicia a conexão ao banco de dados
    * Retorna true ou false
    * @author caiocesar
    */
    public boolean conectar()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cenaflix", "root", "password");
            return true;
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
    
    //Pesquisar por categoria
    /**
    * Retorna uma lista com os filmes filtrados por categoria  
    * @author caiocesar
    */
    public List<Filmes> listarCategoria(String categoria)
    {
        try 
        {
            List<Filmes> lista = new ArrayList<>();
            int linhas;
            
            st = conn.prepareStatement("SELECT COUNT(*) from filmes WHERE categoria = ?");
            st.setString(1, categoria);
            rs = st.executeQuery();
            rs.next();
            linhas = rs.getInt(1);
            
            st = conn.prepareStatement("SELECT id from filmes WHERE categoria = ?");
            st.setString(1, categoria);
            rs = st.executeQuery();
            List<Integer> ids = new ArrayList<>();
            
            while(rs.next())
            {
                ids.add(rs.getInt("id"));
            }
            
            for(int i = 0; i < linhas; i++)
            {  
                Filmes filme = consultar(String.valueOf(ids.get(i)));
                
                if(filme != null)
                {
                    lista.add(filme);
                }
            }
            return lista;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(FilmesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //Pesquisar por Titulo
    /**
    * Retorna uma lista com os filmes filtrados por titulo  
    * @author caiocesar
    */
    public List<Filmes> listarTitulo(String titulo)
    {
        try 
        {
            List<Filmes> lista = new ArrayList<>();
            int linhas;
            
            st = conn.prepareStatement("SELECT COUNT(*) from filmes WHERE nome = ?");
            st.setString(1, titulo);
            rs = st.executeQuery();
            rs.next();
            linhas = rs.getInt(1);
            
            st = conn.prepareStatement("SELECT id from filmes WHERE nome = ?");
            st.setString(1, titulo);
            rs = st.executeQuery();
            List<Integer> ids = new ArrayList<>();
            
            while(rs.next())
            {
                ids.add(rs.getInt("id"));
            }
            
            for(int i = 0; i < linhas; i++)
            {  
                Filmes filme = consultar(String.valueOf(ids.get(i)));
                
                if(filme != null)
                {
                    lista.add(filme);
                }
            }
            return lista;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(FilmesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //Pesquisar por ID
    /**
    * Retorna uma lista com os filmes filtrados por ID  
    * @author caiocesar
    */
    public List<Filmes> listarID(String id)
    {
        try 
        {
            List<Filmes> lista = new ArrayList<>();
            int linhas;
            
            st = conn.prepareStatement("SELECT COUNT(*) from filmes WHERE id = ?");
            st.setString(1, id);
            rs = st.executeQuery();
            rs.next();
            linhas = rs.getInt(1);
            
            st = conn.prepareStatement("SELECT id from filmes WHERE id = ?");
            st.setString(1, id);
            rs = st.executeQuery();
            List<Integer> ids = new ArrayList<>();
            
            while(rs.next())
            {
                ids.add(rs.getInt("id"));
            }
            
            for(int i = 0; i < linhas; i++)
            {  
                Filmes filme = consultar(String.valueOf(ids.get(i)));
                
                if(filme != null)
                {
                    lista.add(filme);
                }
            }
            return lista;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(FilmesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //Listar
    /**
    * Retorna uma lista com todos os filmes cadastrados  
    * @author caiocesar
    */
    public List<Filmes> listarFilmes()
    {
        try 
        {
            //Lista de filmes e quantidade de linhas
            List<Filmes> lista = new ArrayList<>();
            int linhas;
            //Conta o numero de linhas e armazena em linhas
            st = conn.prepareStatement("SELECT COUNT(*) from filmes WHERE id IS NOT NULL");
            rs = st.executeQuery();
            rs.next();
            linhas = rs.getInt(1);
            //Seleciona todos os ids e salva em uma lista
            st = conn.prepareStatement("SELECT id from filmes");
            rs = st.executeQuery();
            
            List<Integer> ids = new ArrayList<>();
            
            while(rs.next())
            {
                ids.add(rs.getInt("id"));
            }
            
            //Carrega e salva todos os filmes cadastrados de acordo com os ids salvos na lista 
            for(int i = 0; i < linhas; i++)
            {  
                Filmes filme = consultar(String.valueOf(ids.get(i)));
                
                if(filme != null)
                {
                    lista.add(filme);
                }
            }
            return lista;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(FilmesDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //Consultar
    /**
    * Retorna um objeto do tipo filmes de acordo com a String inserida 
    * para ser incluido em uma lista de resultados filtrados por id, título ou categoria
    * Se o valor inserido for inteiro faz a busca pelo Id
    * Senão o faz pelo nome
    * @author caiocesar
    */
    public Filmes consultar(String nome)
    {
        try
        {
            List<Filmes> lista = new ArrayList<>();
            Filmes filme = new Filmes();
            
            
            if(nome.matches("[0-9]{1}") || nome.matches("[0-9]{2}") || nome.matches("[0-9]{3}"))
            {
                st = conn.prepareStatement("SELECT * from filmes WHERE id = ? AND id IS NOT NULL");
                st.setInt(1, Integer.parseInt(nome));
            }
            else
            {
                st = conn.prepareStatement("SELECT * from filmes WHERE nome = ? AND nome IS NOT NULL");
                st.setString(1, nome);  
            }    
            
            rs = st.executeQuery();
            if(rs.next())
            {
                filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome"));
                filme.setDataLancamento(rs.getString("datalancamento"));
                filme.setCategoria(rs.getString("categoria"));
                lista.add(filme);
                return filme;
            }
            else 
            {
                return null;
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
    }
    //Excluir
    /**
    * Remove o registro selecionado usando seu id como referência 
    * @author caiocesar
    */
    public boolean excluir(int id)
    {
        try 
        {
            st = conn.prepareStatement("DELETE FROM filmes WHERE id = ?");
            st.setInt(1,id);
            st.executeUpdate();
            return true;
        } 
        catch (SQLException ex) 
        {
                return false;
        }
    }
    
    //Salvar
    /**
    * Salva um novo registro   
    * @author caiocesar
    */
    public int salvar(Filmes filme)
    {
        int status;
        
        try
        {
            //Seleciona o último id cadastrado
            st = conn.prepareStatement("SELECT id FROM filmes ORDER BY ID DESC LIMIT 1");
            rs = st.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            
            //Insere os dados atualizando o id na sequencia
            st = conn.prepareStatement("INSERT INTO filmes VALUES(?,?,?,?)");
            st.setInt(1,id+1);
            st.setString(2,filme.getNome());
            st.setString(3,filme.getDataLancamento());
            st.setString(4, filme.getCategoria());
            status = st.executeUpdate();
            return status;
        }
        catch(SQLException ex)
        {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    //Atualizar
    /**
    * Atualiza os dados de um item selecionado na lista  
    * @author caiocesar
    */
    public int atualizar(Filmes filme)
    {
        int status;
        try {
            st = conn.prepareStatement
                        ("UPDATE filmes SET nome = ?, dataLancamento = ?, categoria = ? where id = ?");
            st.setString(1,filme.getNome());
            st.setString(2,filme.getDataLancamento());
            st.setString(3, filme.getCategoria());
            st.setInt(4,filme.getId());
            status = st.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }
    /**
    * Desconecta do banco de dados 
    * @author caiocesar
    */
    public void desconectar()
    {
        try 
        {
            conn.close();
        } 
        catch (SQLException ex) 
        {
            
        }
    }
}

