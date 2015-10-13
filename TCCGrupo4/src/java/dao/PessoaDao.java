package dao;

import conexao.ConnectionManager;
import entidade.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PessoaDao {
    
    public int salvar(Pessoa pessoa) {
        
       
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();
         
   

            String QUERY_INSERT = "insert into PESSOA (idUsuario, nome, telResidencial, telCelular, rua"
                    + ", numero, complemento, bairro, cep, cidade, estado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String QUERY_UPDATE = "update PESSOA set idUsuario = ?, nome = ?, telResidencial = ?, telCelular = ?"
                    + ", rua = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, estado = ? where idpessoa = ? ";

            if (pessoa.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, pessoa.getUsuario().getId());
              //  stmt.
                

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
              //  stmt.setString(2, pessoa.getEmail());
              //  stmt.setString(3, pessoa.getSenha());
                stmt.setInt(4, pessoa.getId());

                stmt.executeUpdate();
                resultado = pessoa.getId(); 
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }

    public boolean excluir(Pessoa pessoa) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from PESSOA where idusuario = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
          //  stmt.setInt(1, usuario.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }

    public Pessoa editar(int id) {

        Pessoa pessoa = new Pessoa();
        
        try {

            String QUERY_DETALHE = "select * from PESSOA where idpessoa = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setId(rs.getInt("idPessoa"));
                UsuarioDao usuarioDao = new UsuarioDao();
                pessoa.setUsuario(usuarioDao.editar(rs.getInt("idUsuario"))); // puxar o objeto Usuario "gravar" no usuarioDao
                                                     //setando em pessoa o objeto de Usuario
                               
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
           usuario = null;
            
        }
        
        return usuario;
    }

    public List<Pessoa> listar() {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        try {
            String QUERY_DETALHE = "select * from PESSOA";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Pessoa usuario = new Pessoa();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                lista.add(usuario);
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        } finally {
            
            return lista;
            
        }
    }
}
