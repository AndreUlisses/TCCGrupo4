
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Gest�o de Cliente</h1>
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
                <form role="form" name="frmPessoa" id="frmPessoa">
                    <input type="hidden" name="txtId" id="txtId" value="${pessoa.id}">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <label>Nome:  </label>
                                <input class="form-control" id="txtNome" name="txtNome" type="text" value="${pessoa.nome}">
                                <p class="help-block">Informe seu nome completo.</p>
                            </div>
                        </div> 
                    </div>    
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Telefone </label>                              
                                <input class="form-control " id="txtTelResidencial" name="txtTelResidencial" value="${pessoa.telResidencial}">
                                <p class="help-block">Informe seu telefone.</p>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Telefone Celular </label>                              
                                <input class="form-control " id="txtTelCelular" name="txtTelCelular" value="${pessoa.telCelular}">
                                <p class="help-block">Informe seu celular.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <center>
                            <label>Endere�o:</label>
                        </center>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Rua: </label>
                                <input class="form-control" id="txtRua" name="txtRua" type="text" value="${pessoa.rua}">
                                <p class="help-block">Informe sua rua.</p>
                            </div>
                        </div>                                          
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Numero: </label>                              
                                <input class="form-control " id="txtNumero" type="text" name="txtNumero" value="${pessoa.numero}">
                                <p class="help-block">Informe sua numero.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Complemento: </label>
                                <input class="form-control" id="txtComplemento" name="txtComplemento:" type="text" value="${pessoa.complemento}">
                                <p class="help-block">Informe um complemento.</p>
                            </div>
                        </div>                                          
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Bairro: </label>                              
                                <input class="form-control " id="txtBairro" type="text" name="txtBairro" value="${pessoa.bairro}">
                                <p class="help-block">Informe seu bairro.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="form-group">
                                <label>Cep: </label>
                                <input class="form-control" id="txtCep" name="txtCep" type="text" value="${pessoa.cep}">
                                <p class="help-block">Informe seu cep.</p>
                            </div>
                        </div>                                          
                        <div class="col-lg-3">
                            <div class="form-group">
                                <label>Cidade: </label>                              
                                <input class="form-control " id="txtCidade" type="text" name="txtCidade" value="${pessoa.cidade}">
                                <p class="help-block">Informe sua cidade.</p>
                            </div>
                        </div>
                        <div class="col-lg-1">
                            <div class="form-group">
                                <label>Estado: </label>                              
                                <select class="form-control " id="txtEstado" name="txtEstado" value="${pessoa.estado}">
                                    <option>PR</option>
                                    <option>SC</option>
                                    <option>RS</option>
                                </select>
                                    <p class="help-block">Selecione seu estado.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Email: </label>
                                <input class="form-control" id="txtEmail" name="txtEmail:" type="text" value="${pessoa.email}">
                                <p class="help-block">Informe um email.</p>
                            </div>
                        </div>      
                    </div>
                        <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Cpf: </label>                              
                                <input class="form-control " id="txtCpf" type="text" name="txtCpf" value="${pessoa.cpf}">
                                <p class="help-block">Informe seu cpf.</p>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Cnpj: </label>                              
                                <input class="form-control " id="txtCnpj" type="text" name="txtCnpj" value="${pessoa.cnpj}">
                                <p class="help-block">Informe seu cnpj.</p>
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
            txtObjeto: 'Pessoa'
            , txtMetodo: 'Salvar'
            , txtId: document.getElementById("txtId").value
            , txtNome: document.getElementById("txtNome").value
            , txtTelResidencial: document.getElementById("txtTelResidencial").value
            , txtTelCelular: document.getElementById("txtTelCelular").value
            , txtRua: document.getElementById("txtRua").value
            , txtNumero: document.getElementById("txtNumero").value
            , txtComplemento: document.getElementById("txtComplemento").value
            , txtBairro: document.getElementById("txtBairro").value
            , txtCep: document.getElementById("txtCep").value
            , txtCidade: document.getElementById("txtCidade").value
            , txtEstado: document.getElementById("txtEstado").value
            , txtEmail: document.getElementById("txtEmail").value
            , txtCpf: document.getElementById("txtCpf").value
            , txtCnpj: document.getElementById("txtCnpj").value
        }, function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "error") {
                alert("Error: " + xhr.status + ": " + xhr.statusText);
            }
        });

    })
</script>






