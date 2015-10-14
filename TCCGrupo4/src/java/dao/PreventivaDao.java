package dao;

import conexao.ConnectionManager;
import entidade.Preventiva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PreventivaDao {
    public int salvar(Preventiva preventiva) {
        
       
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();
         
   

            String QUERY_INSERT = "insert into PREVENTIVA (idPessoa, descricao, periodo, intervalo) values (?, ?, ?, ?)";
            String QUERY_UPDATE = "update PREVENTIVA set idChamado = ?, idFuncionario= ?, descricao = ?, data = ? where idPreventiva = ? ";

            if (preventiva.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, preventiva.getPessoa().getId());
                stmt.setString(2, preventiva.getDescricao());
                stmt.setString(3, preventiva.getPeriodo());
                stmt.setInt(4, preventiva.getIntevalo());
                
                            
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setInt(1, preventiva.getPessoa().getId());
                stmt.setString(2, preventiva.getDescricao());
                stmt.setString(3, preventiva.getPeriodo());
                stmt.setInt(4, preventiva.getIntevalo());
                stmt.setInt(5, preventiva.getId());

                stmt.executeUpdate();
                resultado = preventiva.getId(); 
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }

    public boolean excluir(Preventiva preventiva) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from PREVENTIVA where idPreventiva = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, preventiva.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }

    public Preventiva editar(int id) {

        Preventiva preventiva = new Preventiva();
        
        try {

            String QUERY_DETALHE = "select * from PREVENTIVA where idPreventiva = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                preventiva = new Preventiva();
                preventiva.setId(rs.getInt("idPessoa"));
                PessoaDao pessoaDao = new PessoaDao();
                preventiva.setPessoa(pessoaDao.editar(rs.getInt("idPessoa")));
                preventiva.setDescricao(rs.getString("descricao"));
                preventiva.setPeriodo(rs.getString("periodo"));
                preventiva.setIntevalo(rs.getInt("intervalo"));
                               
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
           preventiva = null;
            
        }
        
        return preventiva;
    }

    public List<Preventiva> listar() {
        List<Preventiva> lista = new ArrayList<Preventiva>();
        try {
            String QUERY_DETALHE = "select * from PREVENTIVA";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Preventiva preventiva = new Preventiva();     

                preventiva.setId(rs.getInt("idPessoa"));
                PessoaDao pessoaDao = new PessoaDao();
                preventiva.setPessoa(pessoaDao.editar(rs.getInt("idPessoa")));
                preventiva.setDescricao(rs.getString("descricao"));
                preventiva.setPeriodo(rs.getString("periodo"));
                preventiva.setIntevalo(rs.getInt("intervalo"));
                lista.add(preventiva);
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        } finally {
            
            return lista;
            
        }
    }
}
