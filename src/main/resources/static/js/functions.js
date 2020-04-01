var editando=false;


//PROFESOR Y SOCIO
function transformarEnEditable(nodo, entidad){
// El nodo recibido es SPAN
	if (editando == false) {
		var nodoTd = nodo.parentNode; // Nodo TD
		var nodoTr = nodoTd.parentNode; // Nodo TR
		var nodoContenedorForm = document.getElementById('contenedorForm'); // Nodo DIV
		var nodosEnTr = nodoTr.getElementsByTagName('td');
		var id=nodosEnTr[0].textContent; var nombre = nodosEnTr[1].textContent;
		var apellido1 = nodosEnTr[2].textContent;
		var apellido2 = nodosEnTr[3].textContent; var dni = nodosEnTr[4].textContent;
		var correo = nodosEnTr[5].textContent;
		var nuevoCodigoHtml = 	
		'<td><input type="text" name="id" id="id" value="'+id+'" size="2" readonly="readonly"></td>'+	
		'<td><input type="text" name="nombre" id="nombre" value="'+nombre+'" size="10"></td>'+
		'<td><input type="text" name="apellido1" id="apellido1" value="'+apellido1+'" size="10"</td>'+
		'<td><input type="text" name="apellido2" id="apellido2" value="'+apellido2+'" size="10"</td>'+
		'<td><input type="text" name="dni" id="dni" value="'+dni+'" size="10"</td>'+
		'<td><input type="text" name="correo" id="correo" value="'+correo+'" size="10"</td>'+
		'<td>En edición</td>';
		nodoTr.innerHTML = nuevoCodigoHtml;
		nodoContenedorForm.innerHTML = 
		'<form name = "formulario" action="/'+entidad+'/modificar" method="get" onsubmit="capturarEnvio(\''+entidad+'\')" onreset="anular()">'+
		'<input class=botom2 type = "submit" value="Aceptar"> <input class=botom2 type="reset" value="Cancelar">';
		editando = "true";}
	else {alert ('Solo se puede editar una línea a la vez, acepte o cancele para modificar otra línea');
	}
}
function capturarEnvio(entidad){
	var nodoContenedorForm = document.getElementById('contenedorForm'); // Nodo DIV
	nodoContenedorForm.innerHTML = '<form name = "formulario" action="/'+entidad+'/modificar" method="get" onsubmit="capturarEnvio()" onreset="anular()">'+
	'<input type="hidden" name="id" value="'+document.querySelector('#id').value+'">'+
	'<input type="hidden" name="nombre" value="'+document.querySelector('#nombre').value+'">'+
	'<input type="hidden" name="apellido1" value="'+document.querySelector('#apellido1').value+'">'+
	'<input type="hidden" name="apellido2" value="'+document.querySelector('#apellido2').value+'">'+
	'<input type="hidden" name="dni" value="'+document.querySelector('#dni').value+'">'+
	'<input type="hidden" name="correo" value="'+document.querySelector('#correo').value+'">'+
	'<input class=botomSumbit type = "submit" value="Aceptar"> <input class=botomReset type="reset" value="Cancelar">';
	document.formulario.submit();
}

//MODIFICAR CLASE
function transformarEnEditableClase(nodo, entidad){
	// El nodo recibido es SPAN
		if (editando == false) {
			var nodoTd = nodo.parentNode; // Nodo TD
			var nodoTr = nodoTd.parentNode; // Nodo TR
			var nodoContenedorForm = document.getElementById('contenedorForm'); // Nodo DIV
			var nodosEnTr = nodoTr.getElementsByTagName('td');
			var id=nodosEnTr[0].textContent; var tipo = nodosEnTr[1].textContent;
			var fecha = nodosEnTr[2].textContent;
			var duracion = nodosEnTr[3].textContent; var intensidad = nodosEnTr[4].textContent;
			var nuevoCodigoHtml = 	
			'<td><input type="text" name="id" id="id" value="'+id+'" size="2" readonly="readonly"></td>'+	
			'<td><input type="text" name="tipo" id="tipo" value="'+tipo+'" size="10"></td>'+
			'<td><input type="date" name="fecha" id="fecha" value="'+fecha+'" size="10"</td>'+
			'<td><input type="number" name="duracion" id="duracion" value="'+duracion+'" size="10"</td>'+
			'<td><input type="text" name="intensidad" id="intensidad" value="'+intensidad+'" size="10"</td>'+
			'<td>En edición</td>';
			nodoTr.innerHTML = nuevoCodigoHtml;
			nodoContenedorForm.innerHTML = 
			'<form name = "formulario" action="/'+entidad+'/modificar" method="get" onsubmit="capturarEnvioClase(\''+entidad+'\')" onreset="anular()">'+
			'<input class=botom2 type = "submit" value="Aceptar"> <input class=botom2 type="reset" value="Cancelar">';
			editando = "true";}
		else {alert ('Solo se puede editar una línea a la vez, acepte o cancele para modificar otra línea');
		}
	}
	function capturarEnvioClase(entidad){
		var nodoContenedorForm = document.getElementById('contenedorForm'); // Nodo DIV
		nodoContenedorForm.innerHTML = '<form name = "formulario" action="/'+entidad+'/modificar" method="get" onsubmit="capturarEnvio()" onreset="anular()">'+
		'<input type="hidden" name="id" value="'+document.querySelector('#id').value+'">'+
		'<input type="hidden" name="tipo" value="'+document.querySelector('#tipo').value+'">'+
		'<input type="hidden" name="fecha" value="'+document.querySelector('#fecha').value+'">'+
		'<input type="hidden" name="duracion" value="'+document.querySelector('#duracion').value+'">'+
		'<input type="hidden" name="intensidad" value="'+document.querySelector('#intensidad').value+'">'+
		'<input class=botomSumbit type = "submit" value="Aceptar"> <input class=botomReset type="reset" value="Cancelar">';
		document.formulario.submit();
	}

function anular(){
	window.location.reload();
}

function confirmarAlta() {
	//Ingresamos un mensaje a mostrar
	var mensaje = confirm("Socio correctamente añadido a la base de datos");
	//Detectamos si el usuario acepto el mensaje
	if (mensaje) {
	alert("¡Gracias por aceptar!");
	}
	//Detectamos si el usuario denegó el mensaje
	else {
	alert("¡Haz denegado el mensaje!");
	}
}



