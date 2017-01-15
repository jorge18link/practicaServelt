/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
	$(".editar").click(function (ev) {
		columnas = $($(ev.target).parents("tr")[0]).children();
		for (var i = 0; i < columnas.length-2; i++) {
			console.log($(columnas[i]).html())
		}
		
	})
});