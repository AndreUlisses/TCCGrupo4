package dao;

import conexao.ConnectionManager;
import entidade.Pessoa;
import entidade.Usuario;
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
                    + ", rua = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, estado = ? where idPessoa = ? ";

            if (pessoa.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, pessoa.getUsuario().getId());
                stmt.setString(2, pessoa.getNome());
                stmt.setString(3, pessoa.getTelResidencial());
                stmt.setString(4, pessoa.getTelCelular());
                stmt.setString(5, pessoa.getRua());
                stmt.setString(6, pessoa.getNumero());
                stmt.setString(7, pessoa.getComplemento());
                stmt.setString(8, pessoa.getBairro());
                stmt.setString(9, pessoa.getCep());
                stmt.setString(10, pessoa.getCidade());
                stmt.setString(11, pessoa.getEstado());
                            
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setInt(1, pessoa.getUsuario().getId());
                stmt.setString(2, pessoa.getNome());
                stmt.setString(3, pessoa.getTelResidencial());
                stmt.setString(4, pessoa.getTelCelular());
                stmt.setString(5, pessoa.getRua());
                stmt.setString(6, pessoa.getNumero());
                stmt.setString(7, pessoa.getComplemento());
                stmt.setString(8, pessoa.getBairro());
                stmt.setString(9, pessoa.getCep());
                stmt.setString(10, pessoa.getCidade());
                stmt.setString(11, pessoa.getEstado());
                stmt.setInt(12, pessoa.getId());

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

            String QUERY_DELETE = "delete from PESSOA where idPessoa = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, pessoa.getUsuario().getId());

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

            String QUERY_DETALHE = "select * from PESSOA where idPessoa = ?";
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
           pessoa = null;
            
        }
        
        return pessoa;
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
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("idPessoa"));
                pessoa.setUsuario((Usuario) rs.getObject("usuario"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelResidencial(rs.getString("telResidencial"));
                pessoa.setTelCelular(rs.getString("telCelular"));
                pessoa.setRua(rs.getString("rua"));
                pessoa.setNumero(rs.getString("numero"));
                pessoa.setComplemento(rs.getString("complemento"));
                pessoa.setBairro(rs.getString("bairro"));
                pessoa.setCep(rs.getString("cep"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                lista.add(pessoa);
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        } finally {
            
            return lista;
            
        }
    }
}