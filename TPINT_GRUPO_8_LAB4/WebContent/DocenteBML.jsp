<%@ page import = "entidades.Pais" %>
<%@ page import = "entidades.Provincia" %>
<%@ page import = "entidades.Localidad" %>
<%@page import="entidades.Docente"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="javax.servlet.http.HttpSession" %> 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#ListaDocentes').DataTable();
	});
</script>

</head>
<style  type="text/css">
html { 
  background: url(img/fondoIniciarSesion.png) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

.textbox{
            width: 15%;         
            border: none;
            border-bottom:2px solid #fff;
            outline: none;
            height: 40px;
            color: black;
            font-size:16px;
            background-color:transparent;

        }
        
        .button{
    border: none;
    outline: none;
    height: 30px;
    width:120px;
    font-size: 16px;
    font-weight:bold;
    color: white;
    background: #46468c;
    border-radius: 20px;
}
.button:hover{
	background-color:#575796;
	cursor:pointer;
	transition:0.2s;
}
.buttonTabla{
	width:80px;
	border-radius:10px;
    color: white;
    background: #46468c;
}
.buttonTabla:hover{
	background-color:#575796;
	cursor:pointer;
	transition:0.2s;
}
        .body{
        }
        


}
</style>
<body class="body" onLoad="myOnLoad()">
 <jsp:include page="menu.jsp"></jsp:include>
 <%
HttpSession sesion = request.getSession();
String nombre=(String)sesion.getAttribute("Fuser");
		 
if(sesion.getAttribute("Fuser")==null){
		response.sendRedirect("IniciarSesion.jsp");
}else if(nombre==null){
		response.sendRedirect("IniciarSesion.jsp");
}
int num=0;
if(sesion.getAttribute("TipoUsuario")!=null){
	 num= (Integer)sesion.getAttribute("TipoUsuario");

		if(num==2){
			response.sendRedirect("inicio.jsp");
			
		}
	}else{
		response.sendRedirect("inicio.jsp");

	}
%>
<br><br><br><br><br>

<form action="servletDocenteBML" name="FormFiltro" method="get">

<h2>Lista de docentes</h2>

<p> Filtrar por legajo: <input type="number" placeholder="Ingrese Legajo" name="legajo" class="textbox"> <input type ="submit" value="Aceptar" name="btnFiltrarLegajo" onclick="return  getConfirmacionFiltroLegajo()" class="button"><br> </p>
<p> Filtrar por DNI: <input type="number" placeholder="Ingrese DNI" name="dni" class="textbox"> <input type ="submit" value="Aceptar" name="btnFiltrarDni" onclick="return getConfirmacionFiltroDni()" class="button"><br> </p>
<p> Filtrar por provincia: <select name="provincias" id="provinciaDelDocente" onchange="cargar_localidades()" class="textbox"> </p>
<%
ArrayList<Provincia> listaProvincias = null;
if(request.getAttribute("listaProvincias") != null){
	listaProvincias = (ArrayList<Provincia>)request.getAttribute("listaProvincias");
}

if(listaProvincias != null){
	
	for(Provincia provincia: listaProvincias){		
		%>
		<option value="<%= provincia.getIdProvincia()%>"><%=provincia.getDescripcionProvincia()%></option>
<%}
	}%>
</select>
<input type ="submit" value="Aceptar" name="btnFiltrarProvincia" onclick="return getConfirmacionFiltro()" class="button"><br>
<p> Filtrar por localidad: <select name="localidades" id="localidadDelDocente" class="textbox"> </p>
</select>
<input type ="submit" value="Aceptar" name="btnFiltrarLocalidad" onclick="return getConfirmacionFiltro()" class="button"><br>
<p> Filtrar por nombre y apellido: <input type="text" placeholder="Ingrese Nombre o Apellido" name="nombre" class="textbox"> <input type ="submit" value="Aceptar" name="btnFiltrarNombre" onclick="return getConfirmacionFiltroNombre()" class="button"> </p> <br> 
<input type ="submit" value="Quitar Filtros" name="Param" class="button"> <br>
<br>

</form>

<table id="ListaDocentes" border=1 class="display">
<thead>
<tr class="encabezadoLista"> 
	<th>Legajo</th>
	<th>DNI</th>
	<th>Nombre y Apellido</th>
	<th>Correo</th>
	<th>Fecha Nacimiento</th>
	<th>Nacionalidad</th>
	<th>Provincia</th>
	<th>Localidad</th>
	<th>Direccion</th>
	<th>Telefono</th>
	<th></th>
	<th></th>
</tr> 
</thead>
<tbody>
<% ArrayList<Docente> listaDeDocentes = null;
	if(request.getAttribute("listaDeDocentes")!=null){
		listaDeDocentes = (ArrayList<Docente>) request.getAttribute("listaDeDocentes");
	}
	int fl=0;
	if(listaDeDocentes!=null){
	for(Docente doc : listaDeDocentes){
	
 %>
<tr>
	<form name="formularioDocente" action="servletDocenteBML?legajoDocente=<%=doc.getLegajoDocente()%>" method="get" id="Form"  >
	
	<td id="<%=fl%>"><%=doc.getLegajoDocente() %><input type="hidden" name="legajoDocente" value="<%=doc.getLegajoDocente() %>" id="legDocente"> </td>
	<td><%=doc.getDniDocente() %><input type="hidden" name="dniDocente" value="<%=doc.getDniDocente() %>"></td>
	<td><%=doc.getNombreYApellidoDocente() %><input type="hidden" name="nyaDocente" value="<%=doc.getNombreYApellidoDocente() %>"></td>
	<td><%=doc.getCorreoDocente() %><input type="hidden" name="correoDocente" value="<%=doc.getCorreoDocente() %>"></td>
	<td><%=doc.getFechaNacimientoDocente() %><input type="hidden" name="fnDocente" value="<%=doc.getFechaNacimientoDocente() %>"></td>
	<td><%=doc.getPais().getDescripcionPais() %><input type="hidden" name="paisDocente" value="<%=doc.getPais().getIdPais()%>"></td>
	<td><%=doc.getProvincia().getDescripcionProvincia() %><input type="hidden" name="provinciaDocente" value="<%=doc.getProvincia().getIdProvincia() %>"></td>
	<td><%=doc.getLocalidad().getDescripcionLocalidad() %><input type="hidden" name="localidadDocente" value="<%=doc.getLocalidad().getIdLocalidad()%>"></td>
	<td><%=doc.getDireccionDocente() %><input type="hidden" name="direccionDocente" value="<%=doc.getDireccionDocente() %>"></td>
	<td><%=doc.getTelefonoDocente() %><input type="hidden" name="telefonoDocente" value="<%=doc.getTelefonoDocente() %>"></td>
	<td><input type ="submit" value="Modificar" class="buttonTabla" name="btnModificar" onclick="return getConfirmacionModificar();"></td>
	<td><input type ="submit" value="Eliminar" class="buttonTabla" name="btnEliminar" onclick="return getConfirmacionBaja();"></td>
	</form>
</tr>

<%
fl++;
	}
}
 %>
</tbody>
</table>
<%
	boolean modificado;
	if(request.getAttribute("modificoDocente")!=null){
		modificado = (boolean)request.getAttribute("modificoDocente");
	if(modificado){
		%><script>alert("Se modificó el docente con éxito.")</script><% 
	}else{
	%><script>alert("No se pudo modificar el docente.")</script><% 	
	}
} 
%>



<%


boolean eliminado;
if(request.getAttribute("eliminoDocente")!=null){
	eliminado = (boolean)request.getAttribute("eliminoDocente");
if(eliminado){
	%><script>alert("Se dio de baja al docente con éxito.")</script><%
}else{
%><script>alert("No se pudo dar de baja al docente.")</script><% 	
}
} 

%>


<script type="text/javascript">
function getConfirmacionBaja(){
	return confirm("¿Desea dar de baja al docente?");
	}

</script>

<script type="text/javascript">
function getConfirmacionModificar(){
	return confirm("¿Desea continuar con la modificacion?");
	}

</script>

<script type="text/javascript">
function getConfirmacionFiltro(){
	return confirm("¿Desea aplicar el filtro?");
	}

</script>

<script type="text/javascript">
function getConfirmacionFiltroNombre(){
	var a = document.forms["FormFiltro"]["nombre"].value;
	if (a == null || a == "") {
	      alert("Debe rellenar el campo nombre");
	      return false;
	    }
	return confirm("¿Desea aplicar el filtro?");
	}

</script>

<script type="text/javascript">
function getConfirmacionFiltroDni(){
	var a = document.forms["FormFiltro"]["dni"].value;
	if (a == null || a == "") {
	      alert("Debe rellenar el campo dni");
	      return false;
	    }
	return confirm("¿Desea aplicar el filtro?");
	}

</script>

<script type="text/javascript">
function getConfirmacionFiltroLegajo(){
	var a = document.forms["FormFiltro"]["legajo"].value;
	if (a == null || a == "") {
	      alert("Debe rellenar el campo legajo");
	      return false;
	    }
	return confirm("¿Desea aplicar el filtro?");
	}

</script>

<select name="localidades" id="localidadDelDocente2">
<%
ArrayList<Localidad> listaLocalidades2 = null;
if(request.getAttribute("listaLocalidades") != null){
	listaLocalidades2 = (ArrayList<Localidad>)request.getAttribute("listaLocalidades");
}

if(listaLocalidades2 != null){
	
	for(Localidad localidad: listaLocalidades2){		
		%>
		<option value="<%= localidad.getProvincia().getIdProvincia()%>" data-uid="<%=localidad.getIdLocalidad()%>"><%=localidad.getDescripcionLocalidad()%></option>
<%}
	}%>
</select>

<script>
function myOnLoad() {
		var earrings = document.getElementById('localidadDelDocente2');
		earrings.style.visibility = 'hidden';
		 cargar_localidades();
	
	}
</script>

<script>
function cargar_localidades() {
	document.getElementById("localidadDelDocente").options.length = 0;
	
	var x = document.getElementById("localidadDelDocente2");
	var array = new Array();
	var a = new Array();
	var b = new Array();
	for (i = 0; i < x.length; i++) { 
		
		array.push(x.options[i].text);
		a.push(x.options[i].value);
		b.push(x.options[i].getAttribute('data-uid'));
		
}

	

	 addOptions("localidades", array, a,b);
	}
</script>

<script>
function addOptions(domElement, array, a,b) {
	 var select = document.getElementsByName(domElement)[0];
	 var inde = document.getElementById('provinciaDelDocente').value;

	 for (value in array) {
		if(a[value] === inde){
	  var option = document.createElement("option");
	  option.text = array[value];
	  option.value = b[value];
	  select.add(option);
		}
	 }
	}
</script>



<script>
var tableElements = document.getElementById("ListaDocentes");

for(var j = 0; j < tableElements.rows.length; j++){
    var lol = tableElements.rows.item(j);
    var legajo =  document.getElementById(j);
    lol.style.backgroundColor = "lightgrey";
    legajo.style.backgroundColor = "lightgrey";
    
}
</script>







</body>
</html>