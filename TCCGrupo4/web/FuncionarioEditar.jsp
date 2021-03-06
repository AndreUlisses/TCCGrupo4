
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Gest�o de Funcion�rios</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Formul�rio
            </div>
            <div class="panel-body">
                <form role="form" name="frmFuncionario" id="frmFuncionario">
                    <input type="hidden" name="txtId" id="txtId" value="${funcionario.id}">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <label>Nome:  </label>
                                <input class="form-control" id="txtNome" name="txtNome" type="text" value="${funcionario.nome}">
                                <p class="help-block">Informe seu nome completo.</p>
                            </div>
                        </div> 
                    </div>    
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Telefone Celular </label>                              
                                <input class="form-control " id="txtTelCelular" name="txtTelCelular" value="${funcionario.telCelular}">
                                <p class="help-block">Informe seu celular.</p>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <label>Administrador: </label>                              
                                <select class="form-control " id="txtAdm" name="txtAdm" value="${funcionario.adm}">
                                    <option>N�o</option>
                                    <option>Sim</option>
                                </select>
                                    <p class="help-block">Selecione se este funcionario ser� um adm.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Email: </label>
                                <input class="form-control" id="txtEmail" name="txtEmail:" type="text" value="${funcionario.email}">
                                <p class="help-block">Informe um email.</p>
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

<script>
    $(".btnSalvar").click(function () {
        $("#page-wrapper").load("Servlet", {
            //variaveis de controle
            txtObjeto: 'Funcionario'
            , txtMetodo: 'Salvar'
            , txtNome: document.getElementById("txtNome").value
            , txtTelCelular: document.getElementById("txtTelCelular").value
            , txtAdm: document.getElementById("txtAdm").value
            , txtEmail: document.getElementById("txtEmail").value
        }, function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "error") {
                alert("Error: " + xhr.status + ": " + xhr.statusText);
            }
        });

    })
</script>






