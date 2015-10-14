
package dao;

import conexao.ConnectionManager;
import entidade.Funcionario;
import entidade.Usuario;
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
        
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into FUNCIONARIO (idUsuario, nome, telCelular) values (?, ?, ?)";
            String QUERY_UPDATE = "update FUNCIONARIO set idUsuario = ?, nome = ?, telCelular = ? where idusuario = ? ";

            if (funcionario.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setObject(1,funcionario.getUsuario());
                stmt.setString(2,funcionario.getNome() );
                stmt.setString(3,funcionario.getTelCelular() );

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setObject(1, funcionario.getUsuario());
                stmt.setString(2, funcionario.getNome());
                stmt.setString(3, funcionario.getTelCelular());
                stmt.setInt(4, funcionario.getId());

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

            String QUERY_DELETE = "delete from FUNCIONARIO where idusuario = ?";

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
}
