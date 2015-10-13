package entidade;


public class Preventiva {
   
    private Integer id;
    private String descricao;
    private String periodo;
    private Integer intevalo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
