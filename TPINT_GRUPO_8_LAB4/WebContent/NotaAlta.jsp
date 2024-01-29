<%@page import="entidades.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="javax.servlet.http.HttpSession" %> 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seleccion de alumnos</title>
</head>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#tablaAlumno').DataTable();
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

.cuadorCursoAlt{
          
    margin:0 auto;
	margin:40px auto;
	text-align: center;
          width: 589px;
            height: 780px;
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
 .buttonAgregar{
    border: none;
    outline: none;
    height: 30px;
    width:140px;
    font-size: 16px;
    font-weight:bold;
    color: white;
    background: #46468c;
    border-radius: 20px;
}
.buttonAgregar:hover{
	background-color:#575796;
	cursor:pointer;
	transition:0.2s;
}
        .textbox{
            width: 60%;
            
            border: none;
            width: 53px; 
            outline: none;
            height: 40px;
            color: black;
            font-size:16px;
            background-color:transparent;

        }
</style>

<%int proximoId=-1;
if(request.getAttribute("pId")!=null){
	proximoId = (int)request.getAttribute("pId");
}
%>
<%String mate="";
String Legajo;
if(request.getAttribute("mat")!=null){
	mate = request.getAttribute("mat").toString();
}
%>
<body onLoad="myOnLoad()">
 <jsp:include page="menu.jsp"></jsp:include>
 
 <br>
 <br>
 <%
HttpSession sesion = request.getSession();
String nombre=(String)sesion.getAttribute("Fuser");
		 
if(sesion.getAttribute("Fuser")==null){
		response.sendRedirect("IniciarSesion.jsp");
}else if(nombre==null){
		response.sendRedirect("IniciarSesion.jsp");
}
%>
<form method="get" action="servletNotaAlta" name="FormFiltro">
<dir class="cuadorCursoAlt" >
<h2>El curso a sido cargado</h2>
<br> Id del curso: <%=proximoId %>
<br>Materia : <input type="text" name="mate" class="textbox" value="<%=mate %>"readonly onmousedown="return false" >
<br>

<h2>Seleccione los alumnos que estaran en el curso</h2>
<br>Buscar por legajo: <input type="number" name="txtLegajoAlumno" placeholder="Ingrese Legajo" > 
<input type="submit" value="Aceptar" name="btnFiltroLegajo" class="button" onclick="return getConfirmacionFiltroLegajo()">
<br>
<br>
Buscar por nombre: <input type="text" name="txtNombreAlumno"placeholder="Ingrese un nombre" > 
<input type="submit" value="Aceptar" name="btnFiltroNombre" class="button" onclick="return getConfirmacionFiltroNombre()" >
<br>
<br>
<input type="submit" value="Borrar filtro" name="btnBorrarFiltro" class="button" >

<table id="tablaAlumno"  border="1" >
<thead>
<tr class="encabezadoLista">
<th></th>
	<th>Legajo</th>
	<th>Dni</th>
	<th>Nombre y apellido </th>
	<th>Telefono</th>
</tr>
</thead>
<% ArrayList<Alumno> listaAlum= null;
if(request.getAttribute("ListaA")!=null)
{
listaAlum=(ArrayList<Alumno>) request.getAttribute("ListaA");
}
if(listaAlum!=null){
for(Alumno alum : listaAlum){
%>
	<tr>
	<td><input type="checkbox" name="legajoAlumno" value="<%=alum.getLegajoAlumno() %>"></td>
		<td> <%=alum.getLegajoAlumno()%></td>
		<td> <%=alum.getDniAlumno() %></td>
		<td> <%=alum.getNombreYApellidoAlumno() %></td>
		<td> <%=alum.getTelefonoAlumno() %></td>
		
	</tr>
	<% } 
}
%>

<br>
<br>

</table>

<br>
<input type="submit" name="btAgregar" value="Agregar alumno" class="buttonAgregar" onclick="return getConfirmacionAlta()">
<br>

<%
	ArrayList<String> listaMensajes=null;
	if(request.getAttribute("listaMensajes")!=null){
		
		listaMensajes = (ArrayList<String>)request.getAttribute("listaMensajes");
	}
	if (listaMensajes != null){
		
		for(String mensaje : listaMensajes){
				
	%>
			<%= mensaje%><br>
<%}
}
%>
<br><a href="servletNotaAlta?Param=1">Volver al alta curso</a>
</dir>
<script type="text/javascript">
function getConfirmacionAlta(){
	return confirm("¿Desea agregar a estos alumnos?");
	}
</script>
<script type="text/javascript">
function getConfirmacionFiltroNombre(){
	var a = document.forms["FormFiltro"]["txtNombreAlumno"].value;
	if (a == null || a == "") {
	      alert("Debe rellenar el campo nombre");
	      return false;
	    }
	return confirm("¿Desea aplicar el filtro?");
	}
</script>
<script type="text/javascript">
function getConfirmacionFiltroLegajo(){
	var a = document.forms["FormFiltro"]["txtLegajoAlumno"].value;
	if (a == null || a == "") {
	      alert("Debe rellenar el campo legajo");
	      return false;
	    }
	return confirm("¿Desea aplicar el filtro?");
	}

</script>
</form>
</body>
</html>