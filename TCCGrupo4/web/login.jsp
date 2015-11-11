<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Não tem nome ainda</title>

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
        </br></br></br></br></br></br>
        <div id="conteiner">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Login
                        </div>
                        <div class="panel-body">
                            <form role="form" name="frmUsuario" id="frmUsuario" method="Post">
                                <input type="hidden" name="txtObjeto" id="txtObjeto" value="Usuario">
                                <input type="hidden" name="txtMetodo" id="txtMetodo" value="Logon">

                                <div class="form-group">
                                    <label>Email </label>
                                    <input class="form-control" id="txtEmail" name="txtEmail" type="email">
                                    <p class="help-block">Informe seu email.</p>
                                </div>
                                <div class="form-group">
                                    <label>Senha </label>                              
                                    <input class="form-control " id="txtSenha" type="password" name="txtSenha">
                                    <p class="help-block">Informe sua senha.</p>
                                </div>
                                <button type="button" class="btn btn-default btnLogar">Entrar</button>
                                <button type="reset" class="btn btn-default">Cancelar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="include/js/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="include/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="include/js/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="include/js/sb-admin-2.js"></script>    

        <script>
            $(".btnLogar").click(function () {
                document.getElementById("frmUsuario").submit();
            });
        </script>
    </body>
</html>