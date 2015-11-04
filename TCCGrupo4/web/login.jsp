<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Login
            </div>
            <div class="panel-body">
                <form role="form" name="frmUsuario" id="frmUsuario">
                   
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Email </label>
                                <input class="form-control" id="txtEmail" name="txtEmail" type="email">
                                <p class="help-block">Informe seu email.</p>
                            </div>
                        </div>                                          
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Senha </label>                              
                                <input class="form-control " id="txtSenha" type="password" name="txtSenha">
                                <p class="help-block">Informe sua senha.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <button type="button" class="btn btn-default btnLogar">Entrar</button>
                            <button type="reset" class="btn btn-default">Cancelar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

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
</script>
