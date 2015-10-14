package dao;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import conexao.ConnectionManager;
import entidade.Executa;
import entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExecutaDao {
    //realmente tem salvar, excluir, editar dados nessa classe?
    public int salvar(Executa executa) {
        
       
        int resultado = -1;

        try {
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();
         
   

            String QUERY_INSERT = "insert into EXECUTA (idFuncionario,idChamado) values (?, ?)";
            String QUERY_UPDATE = "update EXECUTA set idFuncionario = ?, idChamado = ? where idExecuta = ? ";

            if (executa.getChamado().getId() == null) {//POSSIVEL ERRO AQUI, CLASSE EXECUTA N TEM SEU ID PROPRIO 
                
                stmt = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setObject(1, executa.getFuncionario());
                stmt.setObject(2, executa.getChamado());
                
                            
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    resultado = rs.getInt(1);
                }
                
                
            } else {
                
                stmt = conn.prepareStatement(QUERY_UPDATE);
                stmt.setObject(1, executa.getFuncionario());
                stmt.setObject(2, executa.getChamado());
                stmt.setInt(3, resultado);
                stmt.executeUpdate();
          //      resultado = executa.getId(); //n tem ID
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

            String QUERY_DELETE = "delete from EXECUTA where idExecuta = ?";

            stmt = conn.prepareStatement(QUERY_DELETE);
          //  stmt.setInt(1, executa.getUsuario().getId());

            stmt.executeUpdate();
            conn.close();

            resultado = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            resultado = false;
        }

        return resultado;
    }

    public Executa editar(int id) {

        Executa executa = new Executa();
        
        try {

            String QUERY_DETALHE = "select * from EXECUTA where idExecuta = ?";
            PreparedStatement stmt = null;
            Connection conn = ConnectionManager.getConnection();

            ResultSet rs = null;

            stmt = conn.prepareStatement(QUERY_DETALHE);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                executa = new Executa();
                ChamadoDao chamadoDao = new ChamadoDao();
                FuncionarioDao funcionarioDao = new FuncionarioDao();
               // executa.setId(rs.getInt("idPessoa"));
                executa.setFuncionario(funcionarioDao.editar(rs.getInt("idFuncionario"))); 
                executa.setChamado(chamadoDao.editar(rs.getInt("idChamado"))); 
                               
            }
            conn.close();

        } catch (Exception ex) {
            
            ex.printStackTrace();
           executa = null;
            
        }
        
        return executa;
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
