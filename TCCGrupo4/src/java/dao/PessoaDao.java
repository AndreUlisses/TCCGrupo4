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

            String QUERY_INSERT = "insert into PESSOA (nome, telResidencial, telCelular, rua"
                    + ", numero, complemento, bairro, cep, cidade, estado, email, cnpj, cpf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String QUERY_UPDATE = "update PESSOA set nome = ?, telResidencial = ?, telCelular = ?"
                    + ", rua = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, estado = ?, email = ?,"
                    + "cnpj = ?, cpf = ? where idPessoa = ? ";
            if (pessoa.getId() == null) {

                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, pessoa.getNome());
                stmt.setString(2, pessoa.getTelResidencial());
                stmt.setString(3, pessoa.getTelCelular());
                stmt.setString(4, pessoa.getRua());
                stmt.setInt(5, pessoa.getNumero());
                stmt.setString(6, pessoa.getComplemento());
                stmt.setString(7, pessoa.getBairro());
                stmt.setString(8, pessoa.getCep());
                stmt.setString(9, pessoa.getCidade());
                stmt.setString(10, pessoa.getEstado());
                stmt.setString(11, pessoa.getEmail());
                stmt.setString(12, pessoa.getCnpj());
                stmt.setString(13, pessoa.getCpf());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }

            } else {

                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setString(1, pessoa.getNome());
                stmt.setString(2, pessoa.getTelResidencial());
                stmt.setString(3, pessoa.getTelCelular());
                stmt.setString(4, pessoa.getRua());
                stmt.setInt(5, pessoa.getNumero());
                stmt.setString(6, pessoa.getComplemento());
                stmt.setString(7, pessoa.getBairro());
                stmt.setString(8, pessoa.getCep());
                stmt.setString(9, pessoa.getCidade());
                stmt.setString(10, pessoa.getEstado());
                stmt.setString(11, pessoa.getEmail());
                stmt.setString(12, pessoa.getCnpj());
                stmt.setString(13, pessoa.getCpf());
                stmt.setInt(14, pessoa.getId());

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
            stmt.setInt(1, pessoa.getId());

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
                pessoa.setEmail(rs.getString("email"));                       //setando em pessoa o objeto de Usuario
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setCnpj(rs.getString("cnpj"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelResidencial(rs.getString("telResidencial"));
                pessoa.setTelCelular(rs.getString("telCelular"));
                pessoa.setRua(rs.getString("rua"));
                pessoa.setNumero(rs.getInt("numero"));
                pessoa.setComplemento(rs.getString("complemento"));
                pessoa.setBairro(rs.getString("bairro"));
                pessoa.setCep(rs.getString("cep"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));

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
                UsuarioDao usuarioDao = new UsuarioDao();
                pessoa.setId(rs.getInt("idPessoa"));
                pessoa.setUsuario(usuarioDao.editar(rs.getInt("idUsuario")));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setCnpj(rs.getString("cnpj"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelResidencial(rs.getString("telResidencial"));
                pessoa.setTelCelular(rs.getString("telCelular"));
                pessoa.setRua(rs.getString("rua"));
                pessoa.setNumero(rs.getInt("numero"));
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

    public Pessoa pesquisarUsuario(int id) {

        Pessoa pessoa = new Pessoa();

        try {

            String QUERY_DETALHE = "select * from PESSOA where idusuario = ?";
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
                pessoa.setEmail(rs.getString("email"));                       //setando em pessoa o objeto de Usuario
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setCnpj(rs.getString("cnpj"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelResidencial(rs.getString("telResidencial"));
                pessoa.setTelCelular(rs.getString("telCelular"));
                pessoa.setRua(rs.getString("rua"));
                pessoa.setNumero(rs.getInt("numero"));
                pessoa.setComplemento(rs.getString("complemento"));
                pessoa.setBairro(rs.getString("bairro"));
                pessoa.setCep(rs.getString("cep"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));

            }
            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            pessoa = null;

        }

        return pessoa;
    }

}
