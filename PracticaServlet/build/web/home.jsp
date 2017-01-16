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
          <a class="navbar-brand" href="#">DAW</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li ><a href="index.jsp">Inicio</a></li>
            <li class="active"><a href="Conferencia">Conferencias</a></li>
            <li><a href="asistentes.jsp">Asistentes</a></li>
          </ul>
          
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <div class="container usuarios">
      <h2>Registro de Conferencias</h2>
      <button type="button" class="btn btn-primary agregar" data-toggle="modal" data-target="#modalUsuarios">Agregar Conferencia</button>
      <table id="tablaUsuarios" class="table table-bordered table-striped">
        <thead>
          <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Fecha</th>
            <th>Descripcion</th>
            
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
        <% List<Conferencia> conferencias = (List<Conferencia>) request.getAttribute("conferenciasArray");
                int cont = 0;
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                for (Conferencia con : conferencias) {
        %>
        
          <tr>
            
            <th scope="row"><%= ++cont%></th>
            <td><%= con.getNombre() %></td>
            <td><%= formatoDelTexto.format(con.getFecha()) %></td>
            <td><%= con.getDescripcion() %></td>
            <td style="display: none" ><%= con.getId() %></td>
            <td>          
                    
                <button type="button" class="btn btn-primary editar" data-toggle="modal" data-target="#modalUsuarios" ><span class="glyphicon glyphicon-edit" aria-hidden="true"></span>Editar</button>
            </td>
            <td>
                
                 
                 <button type="submit" class="btn btn-primary eliminar" data-toggle="modal" data-target="#modalEliminar" ><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>Eliminar</button>
                
            </td>
          </tr>
          <% } %>

        </tbody>
      </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="modalUsuarios" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <form action="Conferencia" method="post">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">Ingresar Conferencia</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                  <input type="text" class="form-control" id="inputNombre" name="nombre" placeholder="Nombre de la conferencia">
                </div>
                <div class="form-group">
                  <input type="date" class="form-control" id="inputFecha" name="fecha" placeholder="Fecha de la conferencia">
                </div>
                <div class="form-group">
                    <textarea class="form-control" rows="5" id="comment" name="descripcion" placeholder="Algo que agregar..?"></textarea>
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
              <button type="submit" class="btn btn-primary">Ingresar</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <form action="Conferencia" method="post">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">Eliminar</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Seguro desea eliminar el registro??</label>
                    <div class="form-group">
                    <input type="hidden" class="form-control id" id="idEliminar" name="id" value="">
                    <input type="hidden" class="form-control"  name="action" value="eliminar">
                </div>
                </div>
                
                </div>
            
            <div class="modal-footer">
                
              <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
              <button type="submit" class="btn btn-primary">SI</button>
            </div>
          </form>
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
            
        });
    </script>
  </body>
</html>
