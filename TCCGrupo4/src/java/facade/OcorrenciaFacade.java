package facade;

import dao.OcorrenciaDao;
import dao.UsuarioDao;
import entidade.Chamado;
import entidade.Funcionario;
import entidade.Ocorrencia;
import entidade.Usuario;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OcorrenciaFacade {
    private Ocorrencia requestForm(HttpServletRequest request){
        
        Ocorrencia retorno = new Ocorrencia();
        
        if ((request.getParameter("txtId")!=null)&&(!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }
         if ((request.getParameter("txtIdChamado")!=null)&& (!request.getParameter("txtIdChamado").equals(""))) {
            Chamado chamado = new Chamado();
            chamado.setId(Integer.parseInt(request.getParameter("txtIdChamado")));
            retorno.setChamado(chamado);
        }
          if ((request.getParameter("txtIdFuncionario")!=null)&& (!request.getParameter("txtIdFuncionario").equals(""))) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(Integer.parseInt(request.getParameter("txtIdFuncionario")));
            retorno.setFuncionario(funcionario);
        }
        if ((request.getParameter("txtDescricao")!=null)&& (!request.getParameter("txtDescricao").equals(""))) {
            retorno.setDescricao(request.getParameter("txtDescricao"));
        }
        if ((request.getParameter("txtData")!=null)&& (!request.getParameter("txtData").equals(""))) {
             try{
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                retorno.setData(new java.sql.Date(formato.parse(request.getParameter("txtData")).getTime()));
            }catch(Exception ex){
                retorno.setData(null);
            }
            
        }
        return retorno;
    };    
    
    public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("UsuarioCadastro.jsp");
        rd.forward(request, response);
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ocorrencia ocorrencia = new Ocorrencia();
        OcorrenciaDao ocorrenciaDao = new OcorrenciaDao();

        ocorrencia = requestForm(request);
        ocorrencia =ocorrenciaDao.editar(ocorrencia.getId());
        if (ocorrencia != null) {
            request.setAttribute("ocorrencia", ocorrencia);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioEditar.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ocorrencia ocorrencia = new Ocorrencia();
        OcorrenciaDao ocorrenciaDao = new OcorrenciaDao();

        ocorrencia = requestForm(request);

        if (ocorrenciaDao.salvar(ocorrencia) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        }
    }

    public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ocorrencia ocorrencia = new Ocorrencia();
        OcorrenciaDao ocorrenciaDao = new OcorrenciaDao();

        ocorrencia = requestForm(request);

        if (ocorrenciaDao.excluir(ocorrencia)) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OcorrenciaDao ocorrenciaDao = new OcorrenciaDao();

        List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
        ocorrencias = ocorrenciaDao.listar();

        if (ocorrencias != null) {
            request.setAttribute("ocorrencias", ocorrencias);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioLista.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }
}
