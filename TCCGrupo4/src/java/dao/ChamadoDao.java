package dao;

import conexao.ConnectionManager;
import entidade.Chamado;
import entidade.Pessoa;
import entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChamadoDao {
    
    public int salvar(Chamado chamado) {
        
        //inicializando o retorno da função, caso tenha algum problema deve ser retornar o valor -1
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into CHAMADO (idPessoa, descricao, nome, email, telContato, telCelular, dtChamado, situacao, rua, numero, complemento, bairro, cep, cidade, estado)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String QUERY_UPDATE = "update CHAMADO set idPessoa = ?, descricao = ?, nome = ?, email = ?, telContato = ?, telCelular = ?, dtChamado = ?, situacao = ?, "
                    + "rua = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, estado = ? where idChamado = ? ";

            if (chamado.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setObject(1, chamado.getPessoa());
                stmt.setString(2, chamado.getDescricao());
                stmt.setString(3, chamado.getNome());
                stmt.setString(4, chamado.getEmail());
                stmt.setString(5, chamado.getTelContato());
                stmt.setString(6, chamado.getTelCelular());
                stmt.setDate(7, chamado.getDtChamado());
                stmt.setString(8, chamado.getSituacao());
                stmt.setString(9, chamado.getRua());
                stmt.setString(10, chamado.getNumero());
                stmt.setString(11, chamado.getComplemento());
                stmt.setString(12, chamado.getBairro());
                stmt.setString(13, chamado.getCep());
                stmt.setString(14, chamado.getCidade());
                stmt.setString(15, chamado.getEstado());
                
                

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setObject(1, chamado.getPessoa());
                stmt.setString(2, chamado.getDescricao());
                stmt.setString(3, chamado.getNome());
                stmt.setString(4, chamado.getEmail());
                stmt.setString(5, chamado.getTelContato());
                stmt.setString(6, chamado.getTelCelular());
                stmt.setDate  (7, chamado.getDtChamado());
                stmt.setString(8, chamado.getSituacao());
                stmt.setString(9, chamado.getRua());
                stmt.setString(10, chamado.getNumero());
                stmt.setString(11, chamado.getComplemento());
                stmt.setString(12, chamado.getBairro());
                stmt.setString(13, chamado.getCep());
                stmt.setString(14, chamado.getCidade());
                stmt.setString(15, chamado.getEstado());
                stmt.setInt(16, chamado.getId());

                stmt.executeUpdate();
                resultado = chamado.getId(); 
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }

    public boolean excluir(Chamado chamado) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from CHAMADO where idChamado = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, chamado.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }

    public Chamado editar(int id) {

        Chamado chamado = new Chamado();
        
        try {

            String QUERY_DETALHE = "select * from CHAMADO where idChamado = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                chamado = new Chamado();
                PessoaDao pessoaDao = new PessoaDao();
                chamado.setId(rs.getInt("idChamado"));
                chamado.setPessoa(pessoaDao.editar(rs.getInt("idPessoa")));
                chamado.setDescricao(rs.getString("descricao"));
                chamado.setNome(rs.getString("nome"));
                chamado.setEmail(rs.getString("email"));
                chamado.setTelContato(rs.getString("telContato"));
                chamado.setTelCelular(rs.getString("telCelular"));
                chamado.setDtChamado(rs.getDate("dtChamado"));
                chamado.setSituacao(rs.getString("situacao"));
                chamado.setRua(rs.getString("rua"));
                chamado.setNumero(rs.getString("numero"));
                chamado.setComplemento(rs.getString("complemento"));
                chamado.setBairro(rs.getString("bairro"));
                chamado.setCep(rs.getString("cep"));
                chamado.setCidade(rs.getString("cidade"));
                chamado.setEstado(rs.getString("estado"));
                            
                
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            chamado = null;
            
        }
        
        return chamado;
    }

    public List<Chamado> listar() {
        List<Chamado> lista = new ArrayList<Chamado>();
        try {
            String QUERY_DETALHE = "select * from CHAMADO";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Chamado chamado = new Chamado();
                PessoaDao pessoaDao = new PessoaDao();
                chamado.setId(rs.getInt("idChamado"));
                chamado.setPessoa(pessoaDao.editar(rs.getInt("idPessoa")));
                chamado.setDescricao(rs.getString("descricao"));
                chamado.setNome(rs.getString("nome"));
                chamado.setEmail(rs.getString("email"));
                chamado.setTelContato(rs.getString("telContato"));
                chamado.setTelCelular(rs.getString("telCelular"));
                chamado.setDtChamado(rs.getDate("dtChamado"));
                chamado.setSituacao(rs.getString("situacao"));
                chamado.setRua(rs.getString("rua"));
                chamado.setNumero(rs.getString("numero"));
                chamado.setComplemento(rs.getString("complemento"));
                chamado.setBairro(rs.getString("bairro"));
                chamado.setCep(rs.getString("cep"));
                chamado.setCidade(rs.getString("cidade"));
                chamado.setEstado(rs.getString("estado"));
                lista.add(chamado);
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        } finally {
            
            return lista;
            
        }
    }
}
