
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Chamados</h1>
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
                <form role="form" name="frmChamado" id="frmChamado">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <label>Nome:  </label>
                                <input class="form-control" id="txtNome" name="txtNome" type="text">
                                <p class="help-block">Informe seu nome completo.</p>
                            </div>
                        </div> 
                    </div>    
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Telefone </label>                              
                                <input class="form-control " id="txtTelResidencial" name="txtTelResidencial">
                                <p class="help-block">Informe seu telefone.</p>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Telefone Celular </label>                              
                                <input class="form-control " id="txtTelCelular" name="txtTelCelular">
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
                                <input class="form-control" id="txtRua" name="txtRua" type="text">
                                <p class="help-block">Informe sua rua.</p>
                            </div>
                        </div>                                          
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Numero: </label>                              
                                <input class="form-control " id="txtNumero" type="text" name="txtNumero">
                                <p class="help-block">Informe sua numero.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Complemento: </label>
                                <input class="form-control" id="txtComplemento" name="txtComplemento:" type="text">
                                <p class="help-block">Informe um complemento.</p>
                            </div>
                        </div>                                          
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Bairro: </label>                              
                                <input class="form-control " id="txtBairro" type="text" name="txtBairro">
                                <p class="help-block">Informe seu bairro.</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="form-group">
                                <label>Cep: </label>
                                <input class="form-control" id="txtCep" name="txtCep" type="text">
                                <p class="help-block">Informe seu cep.</p>
                            </div>
                        </div>                                          
                        <div class="col-lg-3">
                            <div class="form-group">
                                <label>Cidade: </label>                              
                                <input class="form-control " id="txtCidade" type="text" name="txtCidade">
                                <p class="help-block">Informe sua cidade.</p>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <label>Estado: </label>                              
                                <select class="form-control " id="txtEstado" name="txtEstado">
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
                                <label>Data: </label>
                                <input class="form-control" id="txtDtChamado" name="txtDtChamado" type="date">
                                <p class="help-block">Informe a data.</p>
                            </div>
                        </div>      
                    </div>
                    <div class="row">
                            <div class="col-lg-6">
                                    <label>Situa��o: </label>                              
                                    <select class="form-control " id="txtSituacao" name="txtSituacao">
                                        <option>Pendente</option>
                                        <option>Em andamento</option>
                                        <option>Conclu�do</option>
                                        <option>Cancelado</option>
                                        <option>Em espera</option>
                                    </select>
                                    <p class="help-block">Informe a situa��o.</p>
                                </div>
                                <div class="col-lg-6">
                                    <label>Descri��o: </label>                              
                                    <textarea class="form-control " id="txtDescricao" name="txtDescricao"></textarea>
                                    <p class="help-block">Descreva o chamado.</p>

                                </div>
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
            txtObjeto: 'Chamado'
            , txtMetodo: 'Salvar'
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
            , txtSituacao: document.getElementById("txtSituacao").value
            , txtDescricao: document.getElementById("txtDescricao").value
            , txtDtChamado: document.getElementById("txtDtChamado").value
        }, function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "error") {
                alert("Error: " + xhr.status + ": " + xhr.statusText);
            }
        });

    })
</script>






