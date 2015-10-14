package entidade;


public class Preventiva {
   
    private Integer id;
    private Pessoa pessoa;
    private String descricao;
    private String periodo;
    private Integer intevalo;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getIntevalo() {
        return intevalo;
    }

    public void setIntevalo(Integer intevalo) {
        this.intevalo = intevalo;
    }
    
    
}
