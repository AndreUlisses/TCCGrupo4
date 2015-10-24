package facade;

import dao.PessoaDao;
import entidade.Pessoa;
import entidade.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PessoaFacade {
private Pessoa requestForm(HttpServletRequest request){
        
        Pessoa retorno = new Pessoa();
        
        if ((request.getParameter("txtId")!=null)&&(!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }        
        if ((request.getParameter("txtNome")!=null)&& (!request.getParameter("txtNome").equals(""))) {
            retorno.setNome(request.getParameter("txtNome"));
        }
        if ((request.getParameter("txtTelResidencial")!=null)&& (!request.getParameter("txtTelResidencial").equals(""))) {
            retorno.setTelResidencial(request.getParameter("txtTelResidencial"));
        }
        if ((request.getParameter("txtTelCelular")!=null)&& (!request.getParameter("txtTelCelular").equals(""))) {
            retorno.setTelCelular(request.getParameter("txtTelCelular"));
        }
        if ((request.getParameter("txtRua")!=null)&& (!request.getParameter("txtRua").equals(""))) {
            retorno.setRua(request.getParameter("txtRua"));
        }
        if ((request.getParameter("txtNumero")!=null)&& (!request.getParameter("txtNumero").equals(""))) {
            retorno.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
        }
        if ((request.getParameter("txtComplemento")!=null)&& (!request.getParameter("txtComplemento").equals(""))) {
            retorno.setComplemento(request.getParameter("txtComplemento"));
        }
        if ((request.getParameter("txtBairro")!=null)&& (!request.getParameter("txtBairro").equals(""))) {
            retorno.setBairro(request.getParameter("txtBairro"));
        }
        if ((request.getParameter("txtCep")!=null)&& (!request.getParameter("txtCep").equals(""))) {
            retorno.setCep(request.getParameter("txtCep"));
        }
        if ((request.getParameter("txtCidade")!=null)&& (!request.getParameter("txtCidade").equals(""))) {
            retorno.setCidade(request.getParameter("txtCidade"));
        }
        if ((request.getParameter("txtEstado")!=null)&& (!request.getParameter("txtEstado").equals(""))) {
            retorno.setEstado(request.getParameter("txtEstado"));
        }
        if ((request.getParameter("txtEmail")!=null)&& (!request.getParameter("txtEmail").equals(""))) {
            retorno.setEmail(request.getParameter("txtEmail"));
        }
        if ((request.getParameter("txtCnpj")!=null)&& (!request.getParameter("txtCnpj").equals(""))) {
            retorno.setCnpj(request.getParameter("txtCnpj"));
        }
        if ((request.getParameter("txtCpf")!=null)&& (!request.getParameter("txtCpf").equals(""))) {
            retorno.setCpf(request.getParameter("txtCpf"));
        }
         if ((request.getParameter("txtIdUsuario")!=null)&& (!request.getParameter("IdUsuario").equals(""))) {
            Usuario usuario = new Usuario();
            usuario.setId(Integer.parseInt(request.getParameter("IdUsuario")));
            retorno.setUsuario(usuario);
        }
    
        return retorno;
    };    
    
    public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("PessoaCadastro.jsp");
        rd.forward(request, response);
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pessoa pessoa = new Pessoa();
        PessoaDao pessoaDao = new PessoaDao();

        pessoa = requestForm(request);
        pessoa =pessoaDao.editar(pessoa.getId());
        if (pessoa != null) {
            request.setAttribute("pessoa", pessoa);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioEditar.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pessoa pessoa = new Pessoa();
        PessoaDao pessoaDao = new PessoaDao();

        pessoa = requestForm(request);

        if (pessoaDao.salvar(pessoa) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        }
    }

    public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pessoa pessoa = new Pessoa();
        PessoaDao pessoaDao = new PessoaDao();

        pessoa = requestForm(request);

        if (pessoaDao.excluir(pessoa)) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PessoaDao pessoaDao = new PessoaDao();

        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas = pessoaDao.listar();

        if (pessoas != null) {
            request.setAttribute("pessoas", pessoas);
            RequestDispatcher rd = request.getRequestDispatcher("PessoaLista.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }
    
    
}
