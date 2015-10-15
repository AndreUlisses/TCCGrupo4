package facade;

import dao.ExecutaDao;
import dao.UsuarioDao;
import entidade.Chamado;
import entidade.Executa;
import entidade.Funcionario;
import entidade.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExecutaFacade {
    private Executa requestForm(HttpServletRequest request){
        
        Executa retorno = new Executa();
        
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
        return retorno;
    };    
    
    public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("UsuarioCadastro.jsp");
        rd.forward(request, response);
    }

    public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Executa executa = new Executa();
        ExecutaDao executaDao = new ExecutaDao();
        
        executa = requestForm(request);

        if (executaDao.salvar(executa) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        }
    }

    public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Executa executa = new Executa();
        ExecutaDao executaDao = new ExecutaDao();

        executa = requestForm(request);

        if (executaDao.excluir(executa)) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExecutaDao executaDao = new ExecutaDao();

        List<Executa> executa = new ArrayList<Executa>();
        executa = executaDao.listar();

        if (executa != null) {
            request.setAttribute("executa", executa);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioLista.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }
}
