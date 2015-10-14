package dao;

import conexao.ConnectionManager;
import entidade.Fisica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FisicaDao {
    
     public int salvar(Fisica fisica) {
        
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into FISICA (idPessoa, cpf) values ( ?, ?)";
            String QUERY_UPDATE = "update FISICA idPessoa = ?, senha = ? where idusuario = ? ";

            if (fisica.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setObject(1, fisica.getPessoa());
                stmt.setString(2, fisica.getCpf());

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setObject(1, fisica.getPessoa());
                stmt.setString(2, fisica.getCpf());
                stmt.setInt(3, fisica.getId());

                stmt.executeUpdate();
                resultado = fisica.getId(); // alterei aqui pra ficar igual ao do ProfessorDAO
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }

    public boolean excluir(Fisica fisica) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from FISICA where idusuario = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, fisica.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }

    public Fisica editar(int id) {

        Fisica fisica = new Fisica();
        
        try {

            String QUERY_DETALHE = "select * from FISICA where idusuario = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                fisica = new Fisica();
                PessoaDao pessoaDao = new PessoaDao();
                fisica.setId(rs.getInt("idUsuario"));
                fisica.setPessoa(pessoaDao.editar(rs.getInt("idPessoa")));
                fisica.setCpf(rs.getString("cpf"));
                
                
             
       
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            fisica = null;
            
        }
        
        return fisica;
    }

    public List<Fisica> listar() {
        List<Fisica> lista = new ArrayList<Fisica>();
        try {
            String QUERY_DETALHE = "select * from FISICA";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Fisica fisica = new Fisica();
                PessoaDao pessoaDao = new PessoaDao();
                fisica.setId(rs.getInt("idUsuario"));
                fisica.setPessoa(pessoaDao.editar(rs.getInt("idPessoa")));
                fisica.setCpf(rs.getString("cpf"));
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        } finally {
            
            return lista;
            
        }
    }
    
}
