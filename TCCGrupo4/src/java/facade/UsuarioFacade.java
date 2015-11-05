package facade;

import dao.FuncionarioDao;
import dao.PessoaDao;
import dao.UsuarioDao;
import entidade.Funcionario;
import entidade.Pessoa;
import entidade.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UsuarioFacade {

    private Usuario requestForm(HttpServletRequest request) {

        Usuario retorno = new Usuario();

        if ((request.getParameter("txtId") != null) && (!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }
        if ((request.getParameter("txtEmail") != null) && (!request.getParameter("txtEmail").equals(""))) {
            retorno.setEmail(request.getParameter("txtEmail"));
        }
        if ((request.getParameter("txtSenha") != null) && (!request.getParameter("txtSenha").equals(""))) {
            retorno.setSenha(request.getParameter("txtSenha"));
        }
        return retorno;
    }

    ;    
    
    public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("UsuarioCadastro.jsp");
        rd.forward(request, response);
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        UsuarioDao usuarioDao = new UsuarioDao();

        usuario = requestForm(request);
        usuario = usuarioDao.editar(usuario.getId());
        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioEditar.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        UsuarioDao usuarioDao = new UsuarioDao();

        usuario = requestForm(request);

        if (usuarioDao.salvar(usuario) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        }
    }

    public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        UsuarioDao usuarioDao = new UsuarioDao();

        usuario = requestForm(request);

        if (usuarioDao.excluir(usuario)) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDao usuarioDao = new UsuarioDao();

        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = usuarioDao.listar();

        if (usuarios != null) {
            request.setAttribute("usuarios", usuarios);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioLista.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void logon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        Funcionario funcionario = new Funcionario();
        Pessoa pessoa = new Pessoa();

        UsuarioDao usuarioDao = new UsuarioDao();
        PessoaDao pessoaDao = new PessoaDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();

        usuario = requestForm(request);
        usuario = usuarioDao.login(usuario.getEmail(), usuario.getSenha());

        if (usuario != null) {

            HashMap<String, String> resultado = new HashMap<String, String>();
            resultado.put("id", Integer.toString(usuario.getId()));
            resultado.put("email", usuario.getEmail());

            funcionario = funcionarioDao.pesquisarUsuario(usuario.getId());
            if (funcionario != null) {
                request.setAttribute("TemFuncionario", "S");
                resultado.put("funcionario", Integer.toString(funcionario.getId()));
                resultado.put("nomeFuncionario", funcionario.getNome());
                resultado.put("temAdm", funcionario.getAdm());
            }

            pessoa = pessoaDao.pesquisarUsuario(usuario.getId());
            if (pessoa != null) {
                request.setAttribute("TemPessoa", "S");
                resultado.put("pessoa", Integer.toString(pessoa.getId()));
                resultado.put("nomePessoa", pessoa.getNome());
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", resultado);
            session.setAttribute("SessaoLogado", true);

            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
            
        } else {

            HttpSession session = request.getSession();
            session.setAttribute("SessaoLogado", false);

            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
    
    public void login(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        
        RequestDispatcher rd = pRequest.getRequestDispatcher("login.jsp");
        rd.forward(pRequest, pResponse);
        
    }

    public void logoff(HttpServletRequest pRequest, HttpServletResponse pResponse) throws ServletException, IOException {
        HttpSession session = pRequest.getSession();
        session.invalidate();

        RequestDispatcher rd = pRequest.getRequestDispatcher("login.jsp");
        rd.forward(pRequest, pResponse);

    }


}
