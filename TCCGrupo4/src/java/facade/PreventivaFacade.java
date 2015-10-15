package facade;

import dao.PreventivaDao;
import entidade.Pessoa;
import entidade.Preventiva;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreventivaFacade {
    private Preventiva requestForm(HttpServletRequest request){
        
        Preventiva retorno = new Preventiva();
        
        if ((request.getParameter("txtId")!=null)&&(!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }        
        if ((request.getParameter("txtIdPessoa")!=null)&& (!request.getParameter("txtIdPessoa").equals(""))) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(Integer.parseInt(request.getParameter("txtIdPessoa")));
            retorno.setPessoa(pessoa);
        }
        if ((request.getParameter("txtDescricao")!=null)&& (!request.getParameter("txtDescricao").equals(""))) {
            retorno.setDescricao(request.getParameter("txtDescricao"));
        }
        if ((request.getParameter("txtPeriodo")!=null)&& (!request.getParameter("txtPeriodo").equals(""))) {
            retorno.setPeriodo(request.getParameter("txtPeriodo"));
        }
        if ((request.getParameter("txtIntervalo")!=null)&& (!request.getParameter("txtIntervalo").equals(""))) {
            retorno.setIntervalo(Integer.parseInt(request.getParameter("txtIntervalo")));
        }
        return retorno;
    };    
    
    public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("UsuarioCadastro.jsp");
        rd.forward(request, response);
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Preventiva preventiva = new Preventiva();
        PreventivaDao preventivaDao = new PreventivaDao();

        preventiva = requestForm(request);
        preventiva =preventivaDao.editar(preventiva.getId());
        if (preventiva != null) {
            request.setAttribute("preventiva", preventiva);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioEditar.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Preventiva preventiva = new Preventiva();
        PreventivaDao preventivaDao = new PreventivaDao();

        preventiva = requestForm(request);

        if (preventivaDao.salvar(preventiva) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        }
    }

    public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Preventiva preventiva = new Preventiva();
        PreventivaDao preventivaDao = new PreventivaDao();

        preventiva = requestForm(request);

        if (preventivaDao.excluir(preventiva)) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PreventivaDao preventivaDao = new PreventivaDao();

        List<Preventiva> preventivas = new ArrayList<Preventiva>();
        preventivas = preventivaDao.listar();

        if (preventivas != null) {
            request.setAttribute("preventivas", preventivas);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioLista.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }
}
