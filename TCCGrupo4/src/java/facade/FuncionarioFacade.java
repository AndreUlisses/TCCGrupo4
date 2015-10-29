package facade;

import dao.FuncionarioDao;
import dao.UsuarioDao;
import entidade.Funcionario;
import entidade.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FuncionarioFacade {

    private Funcionario requestForm(HttpServletRequest request){
        Funcionario retorno = new Funcionario();
        
        if ((request.getParameter("txtId")!=null)&&(!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }        
        if ((request.getParameter("txtNome")!=null)&& (!request.getParameter("txtNome").equals(""))) {
            retorno.setNome(request.getParameter("txtNome"));
        }
        if ((request.getParameter("txtTelCelular")!=null)&& (!request.getParameter("txtTelCelular").equals(""))) {
            retorno.setTelCelular(request.getParameter("txtTelCelular"));
        }
        if ((request.getParameter("txtAdm")!=null)&& (!request.getParameter("txtAdm").equals(""))) {
            retorno.setAdm(request.getParameter("txtAdm"));
        }
        if ((request.getParameter("txtIdUsuario")!=null)&& (!request.getParameter("IdUsuario").equals(""))) {
            Usuario usuario = new Usuario();
            usuario.setId(Integer.parseInt(request.getParameter("IdUsuario")));
            retorno.setUsuario(usuario);
        }
        if ((request.getParameter("txtEmail")!=null)&& (!request.getParameter("txtEmail").equals(""))) {
            retorno.setEmail(request.getParameter("txtEmail"));
        }
        
        return retorno;
    }
    
    public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("FuncionarioCadastro.jsp");
        rd.forward(request, response);
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Funcionario funcionario = new Funcionario();
        FuncionarioDao funcionarioDao = new FuncionarioDao();

        funcionario = requestForm(request);
        funcionario =funcionarioDao.editar(funcionario.getId());
        if (funcionario != null) {
            request.setAttribute("funcionario", funcionario);
            RequestDispatcher rd = request.getRequestDispatcher("FuncionarioEditar.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Funcionario funcionario = new Funcionario();
        FuncionarioDao funcionarioDao = new FuncionarioDao();

        funcionario = requestForm(request);

        if (funcionarioDao.salvar(funcionario) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        }
    }

    public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Funcionario funcionario = new Funcionario();
        FuncionarioDao funcionarioDao = new FuncionarioDao();

        funcionario = requestForm(request);

        if (funcionarioDao.excluir(funcionario)) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FuncionarioDao funcionarioDao = new FuncionarioDao();

        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        funcionarios = funcionarioDao.listar();

        if (funcionarios != null) {
            request.setAttribute("funcionarios", funcionarios);
            RequestDispatcher rd = request.getRequestDispatcher("FuncionarioLista.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }
}
