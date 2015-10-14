package dao;

import conexao.ConnectionManager;
import entidade.Executa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExecutaDao {
    
    public int salvar(Executa executa) {
        
       
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_INSERT = "insert into EXECUTA (idFuncionario,idChamado) values (?, ?)";

            if ((executa.getChamado().getId() == null)&&(executa.getFuncionario().getId()) == null) {
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, executa.getFuncionario().getId());
                stmt.setInt(2, executa.getChamado().getId());
                
                            
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                resultado = -1;
            }

            conn.close();

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = -1;
            
        }

        return resultado;
    }

    public boolean excluir(Executa executa) {

        boolean resultado = false;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            String QUERY_DELETE = "delete from EXECUTA where idfuncionario = ? and idchamado = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
            stmt.setInt(1, executa.getFuncionario().getId());
            stmt.setInt(2, executa.getChamado().getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }

    public List<Executa> listar() {
        List<Executa> lista = new ArrayList<Executa>();
        try {
            String QUERY_DETALHE = "select * from EXECUTA";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Executa executa = new Executa();
                ChamadoDao chamadoDao = new ChamadoDao();
                FuncionarioDao funcionarioDao = new FuncionarioDao();
               // executa.setId(rs.getInt("idPessoa"));
                executa.setFuncionario(funcionarioDao.editar(rs.getInt("idFuncionario"))); 
                executa.setChamado(chamadoDao.editar(rs.getInt("idChamado")));
                lista.add(executa);
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        } finally {
            
            return lista;
            
        }
    }
    
}
