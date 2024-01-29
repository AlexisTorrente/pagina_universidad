<%@page import="entidades.Curso"%>
<%@page import="entidades.Materia"%>
<%@page import="entidades.Docente"%>
<%@page import="entidades.Carrera"%>
<%@page import="entidades.Alumno"%>
<%@page import="entidades.Notas"%>
<%@page import="entidades.EstadoAcademico"%>
<%@page import="java.util.ArrayList"%>
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
    $('#tablaAlumnoCalificar').DataTable();
} );
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.FondoG{
   padding:0;
    margin: 0;
	width:100%;
	height:100vh;
	background:url(img/fondoIniciarSesion.png);
	background-size: cover;
    background-position:center ;
    background-repeat: repeat;
}
.Filtros{
	margin-left:20px;
	width:95%;
	font-weight:bold;
	font-family: Arial;
}
.ddl{
background:none;
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
.textbox{
    width: 15%;         
    border: none;
    border-bottom:2px solid #fff;
    outline: none;
    height: 40px;
    color: black;
    background-color:transparent;
}

</style>
</head>
<body class="FondoG">

 <jsp:include page="menu.jsp"></jsp:include>
 <%
HttpSession sesion = request.getSession();
String nombre=(String)sesion.getAttribute("Fuser");
		 
if(sesion.getAttribute("Fuser")==null){
		response.sendRedirect("IniciarSesion.jsp");
}else if(nombre==null){
		response.sendRedirect("IniciarSesion.jsp");
}
%>
<% 
Curso cur= (Curso)request.getAttribute("curso");
Materia mat= cur.getMateria();

%>
 <br><br><br><br>

<form action="servletAlumnoCalificar?idCurso=<%=cur.getIdCurso()%>" method="post" name="formAlumnoCalificar">


<div class="Filtros">
<a style="float:right" href="servletCursoDetalle?idCurso=<%=cur.getIdCurso()%>">Volver</a>
<h1>Curso <%=mat.getDescripcion()%></h1>
<h3><%=cur.getSemestreCurso()%>, <%=cur.getAnioCurso()%></h3>
<br>
<br>
Buscar por legajo: <input type="number" class="textbox" name="txtLegajoAlumno"> <input type="submit" value="Aceptar" class="button" name="btnFiltroLegajo" onclick="return getConfirmacionFiltroLegajo()"><br> <br>
Filtrar por estado académico: 
<select class="ddl" name="listaEstadosAcademicos">
	<%ArrayList<EstadoAcademico> estadosAcademicos= null;

	if(request.getAttribute("estadosAcademicos")!=null)
	{
			estadosAcademicos=(ArrayList<EstadoAcademico>) request.getAttribute("estadosAcademicos");
		}
	
	if(estadosAcademicos != null)		
	{
	
		for(EstadoAcademico ea : estadosAcademicos){%>
		
		
			<option class="ddl" value="<%=ea.getIdEstadoAcademico()%>"><%=ea.getDescripcionEstadoAcademico()%></option>
	
	
		<%}
	} %>
</select> <input type="submit" value="Aceptar" class="button" name="btnFiltroEstadoAcademico"><br><br>
<input type="submit" value="Eliminar filtros" class="button" name="btnEliminarFiltros">
</div>
<br>
<br>
<table id="tablaAlumnoCalificar" border="1">
<thead>
<tr>
	<th>Legajo</th>
	<th>Nombre y apellido</th>
	<th>Primer parcial</th>
	<th>Segundo parcial</th>
	<th>Primer recuperatorio</th>
	<th>Segundo recuperatorio</th>
	<th>Estado académico</th>
</tr>
</thead>
<tbody>
<%
int cont=1;

ArrayList<Notas> detalleCurso= null;

if(request.getAttribute("detalleCurso")!=null)
{
		detalleCurso=(ArrayList<Notas>) request.getAttribute("detalleCurso");
	}

if(detalleCurso != null)		
{
	for(Notas not : detalleCurso){
	
	Alumno alu= not.getAlumno();
	EstadoAcademico ea=not.getEstadoAcademico();
	%>
	<tr>
		<td><%=alu.getLegajoAlumno() %></td>
		<td> <%=alu.getNombreYApellidoAlumno() %></td>
		<td><input type="number" min="0" max="10" width="100%" name="txtNotaPrimerParcial<%=cont %>" value="<%=not.getNotaPrimerParcial() %>" required> </td>
		<td><input type="number" min="0" max="10" name="txtNotaSegundoParcial<%=cont %>" value="<%=not.getNotaSegundoParcial() %>" required> </td>
		<td><input type="number" min="0" max="10" name="txtNotaPrimerRecuperatorio<%=cont %>" value="<%=not.getNotaPrimerRecuperatorio() %>"> </td>
		<td><input type="number" min="0" max="10" name="txtNotaSegundoRecuperatorio<%=cont %>" value="<%=not.getNotaSegundoRecuperatorio() %>"> </td>
		<td><%=ea.getDescripcionEstadoAcademico() %></td>
		
		<% 
		cont++;
		}
}%>
</table>
<input type="submit" name="btnCalificar" class="button" value="Calificar" onclick="return getConfirmacionCalificar();">
<%
boolean califico= (boolean)request.getAttribute("califico");

if(califico){
%>
	Se califico a los alumnos con éxito
<%
} %>
<script type="text/javascript">
function getConfirmacionFiltroLegajo(){
	var a = document.forms["formAlumnoCalificar"]["txtLegajoAlumno"].value;
	if (a == null || a == "") {
	      alert("Debe rellenar el campo legajo");
	      return false;
	    }
	return confirm("¿Desea aplicar el filtro?");
	}
	
function getConfirmacionCalificar(){
	return confirm("¿Desea calificar a los alumnos?");
	}
</script>
</form>
</body>
</html>