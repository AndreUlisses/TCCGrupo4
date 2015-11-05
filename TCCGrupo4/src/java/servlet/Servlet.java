package servlet;

import facade.ChamadoFacade;
import facade.FuncionarioFacade;
import facade.PessoaFacade;
import facade.UsuarioFacade;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getParameter("txtObjeto"));
        System.out.println(request.getParameter("txtMetodo"));

        HttpSession session = request.getSession();

        boolean vSessaoLogado;

        if (session.getAttribute("SessaoLogado") != null) {
            vSessaoLogado = Boolean.parseBoolean(session.getAttribute("SessaoLogado").toString());
        } else {
            vSessaoLogado = false;
        }

        if (vSessaoLogado) {

            //-----------------------USUARIO-------------------------   
            if (request.getParameter("txtObjeto").equals("Usuario")) {

                UsuarioFacade usuarioFacade = new UsuarioFacade();
                switch (request.getParameter("txtMetodo")) {
                    case "Cadastrar":
                        usuarioFacade.incluir(request, response);
                        break;
                    case "Salvar":
                        usuarioFacade.salvar(request, response);
                        break;
                    case "Editar":
                        usuarioFacade.editar(request, response);
                        break;
                    case "Listar":
                        usuarioFacade.listar(request, response);
                        break;
                    case "Excluir":
                        usuarioFacade.excluir(request, response);
                        break;
                    case "Login":
                        usuarioFacade.login(request, response);
                        break;
                    case "Logon":
                        usuarioFacade.logon(request, response);
                        break;
                    case "Logoff":
                        usuarioFacade.logoff(request, response);
                        break;
                }

            } //---------------------Pessoa-----------------------------
            else if (request.getParameter("txtObjeto").equals("Pessoa")) {

                PessoaFacade pessoaFacade = new PessoaFacade();
                switch (request.getParameter("txtMetodo")) {
                    case "Cadastrar":
                        pessoaFacade.incluir(request, response);
                        break;
                    case "Salvar":
                        pessoaFacade.salvar(request, response);
                        break;
                    case "Editar":
                        pessoaFacade.editar(request, response);
                        break;
                    case "Listar":
                        pessoaFacade.listar(request, response);
                        break;
                    case "Excluir":
                        pessoaFacade.excluir(request, response);
                        break;
                }

            } //----------------Funcionario-----------------
            else if (request.getParameter("txtObjeto").equals("Funcionario")) {

                FuncionarioFacade funcionarioFacade = new FuncionarioFacade();
                switch (request.getParameter("txtMetodo")) {
                    case "Cadastrar":
                        funcionarioFacade.incluir(request, response);
                        break;
                    case "Salvar":
                        funcionarioFacade.salvar(request, response);
                        break;
                    case "Editar":
                        funcionarioFacade.editar(request, response);
                        break;
                    case "Listar":
                        funcionarioFacade.listar(request, response);
                        break;
                    case "Excluir":
                        funcionarioFacade.excluir(request, response);
                        break;
                }

            } else if (request.getParameter("txtObjeto").equals("Chamado")) {

                ChamadoFacade chamadoFacade = new ChamadoFacade();
                switch (request.getParameter("txtMetodo")) {
                    case "Cadastrar":
                        chamadoFacade.incluir(request, response);
                        break;
                    case "Salvar":
                        chamadoFacade.salvar(request, response);
                        break;
                    case "Editar":
                        chamadoFacade.editar(request, response);
                        break;
                    case "Listar":
                        chamadoFacade.listar(request, response);
                        break;
                    case "Excluir":
                        chamadoFacade.excluir(request, response);
                        break;
                }

            } else {

                RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                rd.forward(request, response);

            }

        } else {

            if (request.getParameter("txtObjeto").equals("Usuario")) {

                UsuarioFacade usuarioFacade = new UsuarioFacade();
                switch (request.getParameter("txtMetodo")) {
                    case "Logon":
                        usuarioFacade.logon(request, response);
                        break;
                    case "Login":
                        usuarioFacade.login(request, response);
                        break;
                    case "Logoff":
                        usuarioFacade.logoff(request, response);
                        break;
                    default:
                        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                        break;
                }

            } else {

                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.forward(request, response);

            }

        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
