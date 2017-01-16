<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelos.Conferencia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Conferencias</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="stylesheet/styles.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.css"/>
  </head>

  <body>
    <nav class="navbar navbar-inverse navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">AdmProyectos</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li ><a href="index.jsp">Inicio</a></li>
            <li ><a href="Conferencia">Conferencias</a></li>
            <li class="active"><a href="#">Asistentes</a></li>
          </ul>
          
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <div class="container usuarios">
      <h2>Registro de Asistentes</h2>
      <button type="button" class="btn btn-primary agregar" data-toggle="modal" data-target="#modalUsuarios">Agregar Asistentes</button>
      <table id="tablaUsuarios" class="table table-bordered table-striped">
        <thead>
          <tr>
            <th style ="display:none;">ID</th>
            <th>Cedula</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Conferencia</th>
            <th>Correo</th>
            
            <th></th>
          
          </tr>
        </thead>
        <tbody id ="asistentes">
        
        </tbody>
      </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="modalUsuarios" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">Ingresar Asistente</h4>
            </div>
            <div class="modal-body ag">
                <div class="form-group">
                  <input type="text" class="form-control" id="inputCedula" name="cedula" placeholder="Ingrese su cedula">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" id="inputNombre" name="nombre" placeholder="Ingrese su nombre">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" id="inputApellido" name="apellido" placeholder="Ingrese su apellido">
                </div>
                <div class="form-group">
                    <textarea class="text"  id="inputCorreo" name="correo" placeholder="Ingrese su correo"></textarea>
                </div>
                <div class="form-group">
                  <input type="hidden" class="form-control" id="action" name="action" value="agregar">

                </div>
                <div class="form-group">
                  <input type="hidden" class="form-control" id="id" name="id" value="">

                </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
              <button type="submit" onclick="agregarAsistente();" class="btn btn-primary" data-dismiss="modal" >Ingresar</button>
            </div>
        </div>
      </div>
    </div>

    <div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">Eliminar</h4>
            </div>
            <div class="modal-body">
                
                    <label>Seguro desea eliminar el registro??</label>
                    
                    <p id ="id"></p>
                
                
                
                </div>
            
            <div class="modal-footer">
                
              <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
              <button type="submit" class="btn btn-primary" data-dismiss="modal" onclick="eliminarAsistente();">SI</button>
            </div>
          
        </div>
      </div>
    </div>
    
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="javascript/conferencia.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#tablaUsuarios').DataTable({
                "language": {
                    url: 'i8/dt-spanish.json'
                },
                "aoColumnDefs": [
                    { 'bSortable': false, 'aTargets': [ 4, 5 ] }
                 ]
            });    
            cargarAsistentes();
            cargarConferencias();
            




            $("select option").click(function(event) {
              opciones = $($(event.target).parents("select")[0]).children();
              for (var i = 0; i < opciones.length; i++) {
                $(opciones[i]).attr('id', '');
              }
              $(event.target).attr('id', 'clickeado');
            });

            

        });
        
        function cargarConferencias(){
            conferencias = [];
            $.ajax({  
              type: "GET",  
              url: "./Conferencias",  
              data: "",  
              success: function(response){
                  console.log(response[0]);
                  var cadena = '<div class="form-group"><select id="inputConferencia" class="form-control">';
                  for (var i=0; i < response.length; i++){
                      if (i===0){
                          var agregar = "<option selected>"+response[i].nombre+"</option>";
                      }else{
                          var agregar = "<option>"+response[i].nombre+"</option>";
                      }
                      cadena += agregar;
                  }
                  cadena += "</select></div>";
                  $(".ag").append(cadena);           
                  }
            });
        }; 
        
        function cargarAsistentes(){
            $.ajax({  
              type: "GET",  
              url: "./Asistentes",  
              data: "",  
              success: function(response){
                  $("#asistentes").empty();  
                  console.log(response[0]);
                  var cadena = '';
                  for (var i=0; i < response.length; i++){
                      cadena += '<tr>';
                      cadena += '<td style ="display:none;">'+response[i].id+'</td>'
                      cadena += '<td>'+response[i].cedula+'</td>';
                      cadena += '<td>'+response[i].nombre+'</td>';
                      cadena += '<td>'+response[i].apellido+'</td>';
                      cadena += '<td>'+response[i].conferencia+'</td>';
                      cadena += '<td>'+response[i].correo+'</td>';
                      cadena += '<td><button type="submit" class="btn btn-primary" id ="eliminar" data-toggle="modal" data-target="#modalEliminar" onclick= "eliminar(event);"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>Eliminar</button></td>';
                      cadena += '</tr>';
                      
                  }
                  $("#asistentes").append(cadena);                         
              }                
            });              
        }
        
        function agregarAsistente(){
            conferencia = $("#clickeado").html();
            //aqui hay que obtener la conferencia con eso de target
            data = {"cedula": $("#inputCedula").val(), "nombre": $("#inputNombre").val(), "apellido": $("#inputApellido").val(), "correo": $("#inputCorreo").val(), "conferencia": $("#inputConferencia").val()};
            $.ajax({  
              type: "POST",  
              url: "./Registrar",  
              data: data,  
              success: function(){
                  alert("Ingresado Exitosamente!");
                  cargarAsistentes();
              }     
            });
            
        }

        function eliminar(event){
            columnas = $($(event.target).parents("tr")[0]).children();
            var id = $(columnas[0]).html();
            $("#id").html(id);
        }
        
        function eliminarAsistente(){
            
             
            var id = $("#id").html();//Aqui hay que obtener el ID seleccionado a eliminar

            data = {"id": id};   
            $.ajax({  
              type: "POST",  
              url: "./Eliminar",  
              data: data,  
              success: function(){
                  alert("Eliminado");
                  cargarAsistentes();
              }                
            });
            
            
        }        
    </script>
  </body>
</html>
