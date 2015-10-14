
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Gestão de Usuário</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Formulário
            </div>
            <div class="panel-body">
                <form role="form" name="frmUsuario" id="frmUsuario">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <input type="hidden" name="txtId" id="txtId" value="${usuario.id}">
                                <label>Nome: </label>
                                <input class="form-control" id="txtNome" name="txtNome" value="${usuario.nome}">
                                <p class="help-block">Informe seu nome completo.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Email: </label>
                                <input class="form-control" id="txtEmail" name="txtEmail" value="${usuario.email}">
                                <p class="help-block">Informe seu email.</p>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Senha: </label>
                                <input class="form-control " id="txtSenha" name="txtSenha" type="password" value="${usuario.senha}">
                                <p class="help-block">Informe sua senha.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <button type="button" class="btn btn-default btnSalvar">Salvar</button>
                            <button type="reset" class="btn btn-default">Limpar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(".btnSalvar").click(function () {
        $("#page-wrapper").load("Servlet", {
            //variaveis de controle
            txtObjeto: 'Usuario'
            , txtMetodo: 'Salvar'
            , txtId: document.getElementById("txtId").value
            , txtNome: document.getElementById("txtNome").value
            , txtEmail: document.getElementById("txtEmail").value
            , txtSenha: document.getElementById("txtSenha").value
        }, function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "error") {
                alert("Error: " + xhr.status + ": " + xhr.statusText);
            }
        });

    })
</script>
