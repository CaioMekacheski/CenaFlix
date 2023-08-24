package data;

/**
 * Cria um objeto do tipo Filme para receber os dados  
 * @author caiocesar
 */

public class Filmes 
{
    private int id;
    private String nome;
    private String dataLancamento;
    private String categoria;
    //Construtor
    /**
    * Cria um objeto nulo  
    * @author caiocesar
    */
    public Filmes()
    {
        
    }
    //Getters and 
    /**
    * Define o ID  
    * @author caiocesar
    */
    public void setId(int id)
    {
        this.id = id;
    }
    /**
    * Define o nome do filme  
    * @author caiocesar
    */
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    /**
    * Define a data de lançamento  
    * @author caiocesar
    */
    public void setDataLancamento(String data)
    {
        this.dataLancamento = data;
    }
    /**
    * Define a categoria  
    * @author caiocesar
    */
    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }
    /**
    * Retorna o ID  
    * @author caiocesar
    */
    public int getId()
    {
        return this.id;
    }
    /**
    * Retorna o nome do filme  
    * @author caiocesar
    */
    public String getNome()
    {
        return this.nome;
    }
    /**
    * Retorna a data de lançamento  
    * @author caiocesar
    */
    public String getDataLancamento()
    {
        return this.dataLancamento;
    }
    /**
    * Retorna a categoria  
    * @author caiocesar
    */
    public String getCategoria()
    {
        return this.categoria;
    }
}
