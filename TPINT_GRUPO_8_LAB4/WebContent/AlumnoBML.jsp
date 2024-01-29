<%@ page import = "entidades.Alumno" %>
<%@ page import = "entidades.Pais" %>
<%@ page import = "entidades.Provincia" %>
<%@ page import = "entidades.Localidad" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import="javax.servlet.http.HttpSession" %> 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
	
<script type="text/javascript">
$(document).ready( function () {
    $('#TablaAlumnos').DataTable();
} );
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
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

</style>
</head>

<body class="body" onLoad="myOnLoad()">

<jsp:include page="menu.jsp"></jsp:include>

<form action="servletAlumnoBML" method="get" name="FormFiltro" >
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
			response.sendRedirect("InicioDocente.jsp");
			
		}
	}else{
		response.sendRedirect("inicio.jsp");

	}
%>

<%
	ArrayList<Alumno> listaAlumnos = null;
	if(request.getAttribute("listaAlumnos") != null){
	listaAlumnos = (ArrayList<Alumno>)request.getAttribute("listaAlumnos");
	}
%>
<br>

<%
	String mensaje = "";
	String mensajeModifico ="";
	if(request.getAttribute("mensaje") != null){
		mensaje = (String)request.getAttribute("mensaje");
	}
	if(request.getAttribute("mensajeModifico") != null){
		mensajeModifico = (String)request.getAttribute("mensajeModifico");
	}
%>
<%
	String legajo = null;
	if(request.getAttribute("legajoBaja") != null){
		legajo = (String)request.getAttribute("legajoBaja");
	}
%>
<br><br><br><br><br>
<h2>Listado de alumnos</h2>
Filtrar por legajo: <input class="textbox" type="number" name="filtroLegajo"> <input class="button" type ="submit" value="Aceptar" name="btnLegajoFiltro" onclick="return getConfirmacionFiltroLegajo()"><br>
Filtrar por dni: <input class="textbox" type="number" name="filtroDni"> <input class="button" type ="submit" value="Aceptar" name="btnDniFiltro" onclick="return getConfirmacionFiltroDni()"><br>
Buscar por nombre: <input class="textbox" type="text" name="filtroNombre"> <input class="button" type ="submit" value="Aceptar" name="btnNombreFiltro" onclick="return getConfirmacionFiltroNombre()"><br>
Filtrar por nacionalidad: <select class="textbox" name="filtroPais">
<%
ArrayList<Pais> listaPaises = null;
if(request.getAttribute("listaPaises") != null){
	listaPaises = (ArrayList<Pais>)request.getAttribute("listaPaises");
}

if(listaPaises != null){
	
	for(Pais pais: listaPaises){		
		%>
		<option value="<%= pais.getIdPais()%>"><%=pais.getDescripcionPais()%></option>
<%}
	}%>
</select>
<input class="button" type ="submit" value="Aceptar" name="btnPaisFiltro"><br>
Filtrar por provincia: <select class="textbox" name="filtroProvincia">
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
<input class="button" type ="submit" value="Aceptar" name="btnProvinciaFiltro"><br>
<input class="button" type ="submit" value="Quitar filtros" name="btnQuitarFiltros"><br><br>

</form>

<table id="TablaAlumnos" border="1" class="display">
<thead>
<tr class="encabezadoLista"> 
<th>Legajo</th><th>DNI</th><th>Nombre y Apellido</th><th>Fecha Nacimiento</th><th>Nacionalidad</th><th>Provincia</th><th>Localidad</th><th>Direccion</th><th>Telefono</th><th></th><th></th>
</tr>
</thead>
<tbody>
<%
	if(listaAlumnos != null){
		
		for(Alumno alumno : listaAlumnos){
		%>
<tr>
		<form action="servletAlumnoBML" method="get">
		<td><%=alumno.getLegajoAlumno() %><input type="hidden" name="legajoAlumno" value="<%=alumno.getLegajoAlumno() %>"></td>
		<td><%=alumno.getDniAlumno() %></td>
		<td><%=alumno.getNombreYApellidoAlumno() %></td>
		<td><%=alumno.getFechaNacimientoAlumno() %></td>
		<td><%=alumno.getPais().getDescripcionPais() %></td>
		<td><%=alumno.getProvincia().getDescripcionProvincia() %></td>
		<td><%=alumno.getLocalidad().getDescripcionLocalidad() %></td>
		<td><%=alumno.getDireccionAlumno() %></td>
		<td><%=alumno.getTelefonoAlumno() %></td>
		<td><input type ="submit" value="Eliminar" class="buttonTabla" name="btnEliminar"  onclick="return getConfirmacionBaja();"></td>
		<td><input type ="submit" value="Modificar" class="buttonTabla" name="btnModificar"></td>
		</form>
</tr>
		<%		
		}	
	}
%>

</tbody>
</table>

<%
boolean elimino;
if(request.getAttribute("elimino")!=null){
	elimino = (boolean)request.getAttribute("elimino");
if(elimino){
	%><script>alert("Se dio de baja al alumno con éxito.")</script><%
}else{
%><script>alert("No se pudo dar de baja al alumno.")</script><% 	
}
} 
%>
<%
boolean modifico;
if(request.getAttribute("modifico")!=null){
	modifico = (boolean)request.getAttribute("modifico");
if(modifico){
	%><script>alert("Se modifico al alumno con éxito.")</script><%
}else{
%><script>alert("No se pudo modificar al alumno.")</script><% 	
}
} 
%>

<script type="text/javascript">

function getConfirmacionBaja(){
	return confirm("¿Desea dar de baja al alumno?");
	}

function getConfirmacionFiltroNombre(){
	var a = document.forms["FormFiltro"]["filtroNombre"].value;
	if (a == null || a == "") {
	      alert("Debe rellenar el campo nombre");
	      return false;
	    }
	return confirm("¿Desea aplicar el filtro?");
	}

function getConfirmacionFiltroDni(){
	var a = document.forms["FormFiltro"]["filtroDni"].value;
	if (a == null || a == "") {
	      alert("Debe rellenar el campo dni");
	      return false;
	    }
	return confirm("¿Desea aplicar el filtro?");
	}

function getConfirmacionFiltroLegajo(){
	var a = document.forms["FormFiltro"]["filtroLegajo"].value;
	if (a == null || a == "") {
	      alert("Debe rellenar el campo legajo");
	      return false;
	    }
	return confirm("¿Desea aplicar el filtro?");
	}

</script>

</body>
</html>