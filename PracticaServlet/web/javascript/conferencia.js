
$(document).ready(function() {
        $(".agregar").click(function(event) {
            $("#action").val("agregar");
            $("#inputNombre").val("");
            $("#inputFecha").val("");
            $("#comment").val("");
            $("#id").val("");
        });
    
	$(".editar").click(function (ev) {
            $("#action").val("editar");
            columnas = $($(ev.target).parents("tr")[0]).children();
            $("#inputNombre").val($(columnas[1]).html());
            $("#inputFecha").val($(columnas[2]).html());
            $("#comment").val($(columnas[3]).html());
            $("#id").val($(columnas[4]).html());
		
	});

	$(".eliminar").click(function(ev) {
            columnas = $($(ev.target).parents("tr")[0]).children();
            $(".id").val($(columnas[4]).html());
	});
});

