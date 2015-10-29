<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
<div class="row">
    <div class="col-lg-12">


        <div class="panel panel-default">
            <div class="panel-heading">
                Tabelha de Funcionarios
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                            <tr>
                                
                                <th>Nome</th>
                                <th>Celular</th>
                                <th>E-Mail(s)</th>
                                <th>Administrador</th>
                                <th></th>
                                
                                
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="funcionarios" items="${funcionarios}">
                                <tr class="odd gradeX">
                                    <td>${funcionarios.nome}</td>
                                    <td>${funcionarios.telCelular}</td>
                                    <td>${funcionarios.email}</td>
                                    <td>${funcionarios.adm}</td>
                                    <td >
<button type="button" title="Trocar senha da conta do usuário relacionado ao funcionário" class="btn btn-info btn-circle btnTrocaSenha" id="${funcionarios.id}"><i class="fa fa-lock"></i></button>
<button type="button" title="Editar dados do funcioário" class="btn btn-info btn-circle btnEditar" id="${funcionarios.id}"><i class="fa fa-pencil"></i></button>
<button type="button" title="Excluir funcionário" class="btn btn-warning btn-circle btnExcluir" id="${funcionarios.id}"><i class="fa fa-trash"></i></button>                                        
                                    </td>
                                </tr>                                
                            </c:forEach>


                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->        

    </div>

</div>
<script>
    $(".btnExcluir").click(function () {
        $("#page-wrapper").load("Servlet", {
            //variaveis de controle
            txtObjeto: 'Funcionario'
            , txtMetodo: 'Excluir'
            , txtId: this.id
        }, function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "error") {
                alert("Error: " + xhr.status + ": " + xhr.statusText);
            }
        });

    })
</script>
<script>
    $(".btnEditar").click(function () {
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
</script>
<script>
    $(".btnTrocaSenha").click(function () {
        $("#page-wrapper").load("Servlet", {
            //variaveis de controle
            txtObjeto: 'Usuario'
            , txtMetodo: 'Editar'
            , txtId: this.id
        }, function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "error") {
                alert("Error: " + xhr.status + ": " + xhr.statusText);
            }
        });

    })
</script>
