package dao;

import conexao.ConnectionManager;
import entidade.Juridica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JuridicaDao {
   
    public int salvar(Juridica juridica) {
        
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into FISICA (idPessoa, cpf) values ( ?, ?)";
            String QUERY_UPDATE = "update FISICA idPessoa = ?, senha = ? where idJuridica = ? ";

            if (juridica.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, juridica.getPessoa().getId());
                stmt.setString(2, juridica.getCnpj());

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setInt(1, juridica.getPessoa().getId());
                stmt.setString(2, juridica.getCnpj());
                stmt.setInt(3, juridica.getId());

                stmt.executeUpdate();
                resultado = juridica.getId(); // alterei aqui pra ficar igual ao do ProfessorDAO
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }

    public boolean excluir(Juridica juridica) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from FISICA where idJuridica = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, juridica.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }

    public Juridica editar(int id) {

        Juridica juridica = new Juridica();
        
        try {

            String QUERY_DETALHE = "select * from FISICA where idJuridica = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                juridica = new Juridica();
                PessoaDao pessoaDao = new PessoaDao();
                juridica.setId(rs.getInt("idUsuario"));
                juridica.setPessoa(pessoaDao.editar(rs.getInt("idPessoa")));
                juridica.setCnpj(rs.getString("cnpj"));
                
                
             
       
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            juridica = null;
            
        }
        
        return juridica;
    }

    public List<Juridica> listar() {
        List<Juridica> lista = new ArrayList<Juridica>();
        try {
            String QUERY_DETALHE = "select * from FISICA";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Juridica juridica = new Juridica();
                PessoaDao pessoaDao = new PessoaDao();
                juridica.setId(rs.getInt("idUsuario"));
                juridica.setPessoa(pessoaDao.editar(rs.getInt("idPessoa")));
                juridica.setCnpj(rs.getString("cnpj"));
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        } finally {
            
            return lista;
            
        }
    }
    
}
