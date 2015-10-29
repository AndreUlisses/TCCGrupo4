package facade;

import dao.ChamadoDao;
import entidade.Chamado;
import entidade.Pessoa;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChamadoFacade {
    private Chamado requestForm(HttpServletRequest request){
        
        Chamado retorno = new Chamado();
        
        if ((request.getParameter("txtId")!=null)&&(!request.getParameter("txtId").equals(""))) {
            retorno.setId(Integer.parseInt(request.getParameter("txtId")));
        }        
         if ((request.getParameter("txtIdPessoa")!=null)&& (!request.getParameter("txtIdPessoa").equals(""))) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(Integer.parseInt(request.getParameter("txtIdPessoa")));
            retorno.setPessoa(pessoa);
        }       
        if ((request.getParameter("txtNome")!=null)&& (!request.getParameter("txtNome").equals(""))) {
            retorno.setNome(request.getParameter("txtNome"));
        }
        if ((request.getParameter("txtDescricao")!=null)&& (!request.getParameter("txtDescricao").equals(""))) {
            retorno.setDescricao(request.getParameter("txtDescricao"));
        }
        if ((request.getParameter("txtTelCelular")!=null)&& (!request.getParameter("txtTelCelular").equals(""))) {
            retorno.setTelCelular(request.getParameter("txtTelCelular"));
        }
        if ((request.getParameter("txtRua")!=null)&& (!request.getParameter("txtRua").equals(""))) {
            retorno.setRua(request.getParameter("txtRua"));
        }
        if ((request.getParameter("txtNumero")!=null)&& (!request.getParameter("txtNumero").equals(""))) {
            retorno.setNumero(request.getParameter("txtNumero"));
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
        if ((request.getParameter("txtTelContato")!=null)&& (!request.getParameter("TelContato").equals(""))) {
            retorno.setTelContato(request.getParameter("TelContato"));
        }
        if ((request.getParameter("txtDtChamado")!=null)&& (!request.getParameter("txtDtChamado").equals(""))) {
            try{
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                retorno.setDtChamado(new java.sql.Date(formato.parse(request.getParameter("txtDtChamado")).getTime()));
            }catch(Exception ex){
                retorno.setDtChamado(null);
            }
            
        }
        if ((request.getParameter("txtSituacao")!=null)&& (!request.getParameter("txtSituacao").equals(""))) {
            retorno.setSituacao(request.getParameter("txtSituacao"));
        }
        return retorno;
    };    
    
    public void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("ChamadoCadastro.jsp");
        rd.forward(request, response);
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Chamado chamado = new Chamado();
        ChamadoDao chamadoDao = new ChamadoDao();

        chamado = requestForm(request);
        chamado =chamadoDao.editar(chamado.getId());
        if (chamado != null) {
            request.setAttribute("chamado", chamado);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioEditar.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void salvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Chamado chamado = new Chamado();
        ChamadoDao chamadoDao = new ChamadoDao();

        chamado = requestForm(request);

        if (chamadoDao.salvar(chamado) == -1) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        }
    }

    public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Chamado chamado = new Chamado();
        ChamadoDao chamadoDao = new ChamadoDao();

        chamado = requestForm(request);

        if (chamadoDao.excluir(chamado)) {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemOK.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }

    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChamadoDao chamadoDao = new ChamadoDao();

        List<Chamado> chamados = new ArrayList<Chamado>();
        chamados = chamadoDao.listar();

        if (chamados != null) {
            request.setAttribute("chamados", chamados);
            RequestDispatcher rd = request.getRequestDispatcher("UsuarioLista.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("mensagemErro.jsp");
            rd.forward(request, response);
        }
    }
}
