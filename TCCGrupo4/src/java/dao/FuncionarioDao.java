
package dao;

import conexao.ConnectionManager;
import entidade.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ROBERTO
 */
public class FuncionarioDao {
    
    public int salvar(Funcionario funcionario) {
        
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into FUNCIONARIO (nome, telCelular, adm, email) values (?, ?, ?, ?)";
            String QUERY_UPDATE = "update FUNCIONARIO set nome = ?, telCelular = ?, adm = ?, email = ? where idFuncionario = ? ";

            if(funcionario.getId() == null){
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);             
                stmt.setString(1,funcionario.getNome());
                stmt.setString(2,funcionario.getTelCelular());
                stmt.setString(3, funcionario.getAdm());
                stmt.setString(4, funcionario.getEmail());

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getTelCelular());
                stmt.setString(3, funcionario.getAdm());
                stmt.setString(4, funcionario.getEmail());
                stmt.setInt(5, funcionario.getId());

                stmt.executeUpdate();
                resultado = funcionario.getId(); 
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }
    
        return resultado;
    }

    public boolean excluir(Funcionario funcionario) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from FUNCIONARIO where idFuncionario = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, funcionario.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }

    public Funcionario editar(int id) {

        Funcionario funcionario = new Funcionario();
        
        try {

            String QUERY_DETALHE = "select * from FUNCIONARIO where idFuncionario = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                funcionario = new Funcionario();
                UsuarioDao usuarioDao = new UsuarioDao();
                funcionario.setId(rs.getInt("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelCelular(rs.getString("telCelular"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setAdm(rs.getString("adm"));
                
                funcionario.setUsuario(usuarioDao.editar(rs.getInt("idUsuario")));
                               
                
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            funcionario = null;
            
        }
        
        return funcionario;
    }

    public List<Funcionario> listar() {
        List<Funcionario> lista = new ArrayList<Funcionario>();
        try {
            String QUERY_DETALHE = "select * from FUNCIONARIO";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                UsuarioDao usuarioDao = new UsuarioDao();
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelCelular(rs.getString("telCelular"));
                funcionario.setAdm(rs.getString("adm"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setUsuario(usuarioDao.editar(rs.getInt("idUsuario")));
                lista.add(funcionario);
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        } finally {
            
            return lista;
            
        }
    }
    
    public Funcionario pesquisarUsuario(int id) {

        Funcionario funcionario = new Funcionario();
        
        try {

            String QUERY_DETALHE = "select * from FUNCIONARIO where idusuario = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                funcionario = new Funcionario();
                UsuarioDao usuarioDao = new UsuarioDao();
                funcionario.setId(rs.getInt("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelCelular(rs.getString("telCelular"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setAdm(rs.getString("adm"));
                
                funcionario.setUsuario(usuarioDao.editar(rs.getInt("idUsuario")));
                               
                
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            funcionario = null;
            
        }
        
        return funcionario;
    }
    
}
