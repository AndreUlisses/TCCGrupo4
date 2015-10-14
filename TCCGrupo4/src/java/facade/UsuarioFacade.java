package facade;

import dao.UsuarioDao;
import entidade.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioFacade {

    private Usuario requestForm(HttpServletRequest request){
        
        Usuario retorno = new Usuario();
        
        if ((request.getParameter("txtId")!=null)&&(!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }
        
        if ((request.getParameter("txtEmail")!=null)&& (!request.getParameter("txtEmail").equals(""))) {
            retorno.setEmail(request.getParameter("txtEmail"));
        }
        if ((request.getParameter("txtSenha")!=null)&& (!request.getParameter("txtSenha").equals(""))) {
            retorno.setSenha(request.getParameter("txtSenha"));
        }
        return retorno;
    };    
    
    public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("UsuarioCadastro.jsp");
        rd.forward(request, response);
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        UsuarioDao usuarioDao = new UsuarioDao();

        usuario = requestForm(request);
        usuario =usuarioDao.editar(usuario.getId());
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

    
}
