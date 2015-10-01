package Controller;

import DAO.UsuarioDAO;
import Entidade.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioController {

    public void Cadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("UsuarioCadastro.jsp");
        rd.forward(request, response);

    }

    public void Salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = new Usuario();
        usuario.setNome(request.getParameter("txtNome"));
        usuario.setEmail(request.getParameter("txtEmail"));
        usuario.setSenha(request.getParameter("txtSenha"));

        UsuarioDAO usuarioDao = new UsuarioDAO();
        if (usuarioDao.salvar(usuario) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        }

    }
    
    
    public void Excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = new Usuario();
        usuario.setId(Integer.parseInt(request.getParameter("txtId")));

        UsuarioDAO usuarioDao = new UsuarioDAO();
        if (!usuarioDao.deletar(usuario)) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        }

    }    

    public void Listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsuarioDAO usuarioDao = new UsuarioDAO();

        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = usuarioDao.listar();
                
        if (usuarios == null) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        } else {
            
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioLista.jsp");            
            request.setAttribute("usuarios", usuarios);
            rd.forward(request, response);
        }
        
    }
    
}
