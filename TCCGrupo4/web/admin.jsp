<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Não tem nome ainda (${TemFuncionario}) </title>

        <!-- Bootstrap Core CSS -->
        <link href="include/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="include/css/metisMenu.min.css" rel="stylesheet">

        <!-- Timeline CSS -->
        <link href="include/css/timeline.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="include/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="include/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>

        <div id="wrapper">
            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">SISACH</a>
                </div>
                <!-- /.navbar-header -->
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <c:if test="${TemFuncionario == 'S'}">
                                <i class="fa fa-user fa-fw"></i> ${usuarioLogado.nomeFuncionario} <i class="fa fa-caret-down"></i>
                            </c:if>
                            <c:if test="${TemPessoa == 'S'}">
                                <i class="fa fa-user fa-fw"></i> ${usuarioLogado.nomePessoa} <i class="fa fa-caret-down"></i>
                            </c:if>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <c:if test="${TemFuncionario == 'S'}">
                            <li><a href="#" id="${usuarioLogado.funcionario}" class="PerfilFuncionario"><i class="fa fa-user fa-fw"></i> Perfil do Funcionário</a>
                            </c:if>
                            <c:if test="${TemPessoa == 'S'}">
                            <li><a href="#" id="${usuarioLogado.pessoa}" class="PerfilPessoa"><i class="fa fa-user fa-fw"></i> Perfil do Pessoa</a>
                            </c:if>
                            </li>
                            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Configuração</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="#" class="Logoff"><i class="fa fa-sign-out fa-fw"></i> Sair</a>
                            </li>
                        </ul>
                        <!-- /.dropdown-user -->
                    </li>                
                </ul>
                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">

                            <li>
                                <a href="#" class="menuItem" id="Principal"><i class="fa fa-dashboard fa-fw"></i> Principal</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-users fa-fw"></i>Usuários<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#" class="menuItemNovo" id="Usuario">Novo</a>
                                    </li>
                                    <li>
                                        <a href="#" class="menuItemLista" id="Usuario">Lista</a>
                                    </li>
                                </ul>                            
                            </li>
                 
                            <li>
                                <a href="#"><i class="fa fa-users fa-fw"></i>Cliente<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#" class="menuPessoaNovo" id="Pessoa">Novo</a>
                                    </li>
                                    <li>
                                        <a href="#" class="menuPessoaLista" id="Pessoa">Lista</a>
                                    </li>
                                </ul>                            
                            </li>
                      
                            <li>
                                <a href="#"><i class="fa fa-users fa-fw"></i>Funcionários<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#" class="menuFuncionarioNovo" id="Funcionario">Novo</a>
                                    </li>
                                    <li>
                                        <a href="#" class="menuFuncionarioLista" id="Funcionario">Lista</a>
                                    </li>
                                </ul>                            
                            </li>
                  
                            <li>
                                <a href="#"><i class="fa fa-users fa-fw"></i>Chamados<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#" class="menuChamadoNovo" id="Chamado">Novo</a>
                                    </li>
                                    <li>
                                        <a href="#" class="menuChamadoLista" id="Chamado">Lista</a>
                                    </li>
                                </ul>                            
                            </li>


                        </ul>
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <div id="page-wrapper">

            </div>

        </div>
        
        <form id="frmAdmin" name="frmAdmin" method="Post" action="Servlet">
            <input type="hidden" name="txtObjeto" id="txtObjeto" value="">
            <input type="hidden" name="txtMetodo" id="txtMetodo" value="">
        </form>

        <!-- jQuery -->
        <script src="include/js/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="include/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="include/js/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="include/js/sb-admin-2.js"></script>    

        <script>
            $(".menuItemNovo").click(function () {
                $("#page-wrapper").load("Servlet", {
                    //variaveis de controle
                    txtObjeto: this.id
                    , txtMetodo: 'Cadastrar'
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });
            });

            $(".menuItemLista").click(function () {
                $("#page-wrapper").load("Servlet", {
                    //variaveis de controle
                    txtObjeto: this.id
                    , txtMetodo: 'Listar'
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });
            });
        </script>
        <script>
            $(".menuPessoaNovo").click(function () {
                $("#page-wrapper").load("Servlet", {
                    txtObjeto: this.id
                    , txtMetodo: 'Cadastrar'
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });
            });
            $(".menuPessoaLista").click(function () {
                $("#page-wrapper").load("Servlet", {
                    //variaveis de controle
                    txtObjeto: this.id
                    , txtMetodo: 'Listar'
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });
            });

        </script>
        <script>
            $(".menuFuncionarioNovo").click(function () {
                $("#page-wrapper").load("Servlet", {
                    txtObjeto: this.id
                    , txtMetodo: 'Cadastrar'
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });
            });
            $(".menuFuncionarioLista").click(function () {
                $("#page-wrapper").load("Servlet", {
                    //variaveis de controle
                    txtObjeto: this.id
                    , txtMetodo: 'Listar'
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });
            });

        </script>
        <script>////////////////////////////////////////////////
            $(".menuChamadoNovo").click(function () {
                $("#page-wrapper").load("Servlet", {
                    txtObjeto: this.id
                    , txtMetodo: 'Cadastrar'
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });
            });
            $(".menuChamadoLista").click(function () {
                $("#page-wrapper").load("Servlet", {
                    //variaveis de controle
                    txtObjeto: this.id
                    , txtMetodo: 'Listar'
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });
            });

        </script>

        <script>
            $(".btnLogar").click(function () {
                $("#page-wrapper").load("Servlet", {
                    //variaveis de controle
                    txtObjeto: 'Usuario'
                    , txtMetodo: 'Logon'
                    , txtEmail: document.getElementById("txtEmail").value
                    , txtSenha: document.getElementById("txtSenha").value
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });

            })

            $(".PerfilFuncionario").click(function () {
                $("#page-wrapper").load("Servlet", {
                    //variaveis de controle
                    txtObjeto: 'Funcionario'
                    , txtMetodo: 'Editar'
                    , txtId: this.id
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });

            })

            $(".PerfilPessoa").click(function () {
                $("#page-wrapper").load("Servlet", {
                    //variaveis de controle
                    txtObjeto: 'Pessoa'
                    , txtMetodo: 'Editar'
                    , txtId: this.id
                }, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == "error") {
                        alert("Error: " + xhr.status + ": " + xhr.statusText);
                    }
                });

            })
            
            $(".Logoff").click(function () {
                document.getElementById("txtObjeto").value = 'Usuario';
                document.getElementById("txtMetodo").value = 'Logoff';
                document.getElementById("frmAdmin").submit();
            })
        </script>
    </body>
</html>