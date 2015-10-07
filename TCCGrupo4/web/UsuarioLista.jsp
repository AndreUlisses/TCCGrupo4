<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
<div class="row">
    <div class="col-lg-12">


        <div class="panel panel-default">
            <div class="panel-heading">
                DataTables Advanced Tables
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                            <tr>
                                
                                <th>Nome</th>
                                <th>E-mail(s)</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usuario" items="${usuarios}">
                                <tr class="odd gradeX">
                                    <td>${usuario.nome}</td>
                                    <td>${usuario.email}</td>
                                    <td >
<button type="button" class="btn btn-info btn-circle btnEditar" id="${usuario.id}"><i class="fa fa-pencil"></i></button>                                        
<button type="button" class="btn btn-warning btn-circle btnExcluir" id="${usuario.id}"><i class="fa fa-trash"></i></button>                                        
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
            txtObjeto: 'Usuario'
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