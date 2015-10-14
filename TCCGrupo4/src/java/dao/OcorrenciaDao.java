package dao;

import conexao.ConnectionManager;
import entidade.Ocorrencia;
import entidade.Chamado;
import entidade.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OcorrenciaDao {
    public int salvar(Ocorrencia ocorrencia) {
        
       
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();
         
   

            String QUERY_INSERT = "insert into OCORRENCIA (idChamado, idFuncionario, descricao, data) values (?, ?, ?, ?)";
            String QUERY_UPDATE = "update OCORRENCIA set idChamado = ?, idFuncionario= ?, descricao = ?, data = ? where idOcorrencia = ? ";

            if (ocorrencia.getId() == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, ocorrencia.getChamado().getId());
                stmt.setInt(2, ocorrencia.getFuncionario().getId());
                stmt.setString(3, ocorrencia.getDescricao());
                stmt.setDate(4, ocorrencia.getData());
                
                            
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setInt(1, ocorrencia.getChamado().getId());
                stmt.setInt(2, ocorrencia.getFuncionario().getId());
                stmt.setString(3, ocorrencia.getDescricao());
                stmt.setDate(4, ocorrencia.getData());
                stmt.setInt(12, ocorrencia.getId());

                stmt.executeUpdate();
                resultado = ocorrencia.getId(); 
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }

    public boolean excluir(Ocorrencia ocorrencia) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from OCORRENCIA where idOcorrencia = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, ocorrencia.getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }

    public Ocorrencia editar(int id) {

        Ocorrencia ocorrencia = new Ocorrencia();
        
        try {

            String QUERY_DETALHE = "select * from OCORRENCIA where idOcorrencia = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                ocorrencia = new Ocorrencia();
                ocorrencia.setId(rs.getInt("idPessoa"));
                ChamadoDao chamadoDao = new ChamadoDao();
                FuncionarioDao funcionarioDao = new FuncionarioDao();
                ocorrencia.setChamado(chamadoDao.editar(rs.getInt("idChamado")));
                ocorrencia.setFuncionario(funcionarioDao.editar(rs.getInt("idFuncionario")));
                               
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
           ocorrencia = null;
            
        }
        
        return ocorrencia;
    }

    public List<Ocorrencia> listar() {
        List<Ocorrencia> lista = new ArrayList<Ocorrencia>();
        try {
            String QUERY_DETALHE = "select * from OCORRENCIA";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();     
                ocorrencia.setId(rs.getInt("idPessoa"));
                
                ChamadoDao chamadoDao = new ChamadoDao();
                FuncionarioDao funcionarioDao = new FuncionarioDao();
                ocorrencia.setChamado(chamadoDao.editar(rs.getInt("idChamado")));
                ocorrencia.setFuncionario(funcionarioDao.editar(rs.getInt("idFuncionario")));
                lista.add(ocorrencia);
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        } finally {
            
            return lista;
            
        }
    }
}
