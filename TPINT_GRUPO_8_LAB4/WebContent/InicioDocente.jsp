<%@page import="entidades.Curso"%>
<%@page import="entidades.Materia"%>
<%@page import="entidades.Docente"%>
<%@page import="entidades.Carrera"%>
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
    $('#tablaCursosDocente').DataTable();
} );
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio docente</title>
<style type="text/css">
.FondoG{
   padding:0;
    margin: 0;
	width:100%;
	height:100vh;
	background:url(img/fondoIniciarSesion.png);
	background-size: cover;
    background-position:center ;
    background-repeat:repeat;

}
.contenedor{
	margin:0 auto;
	width:600px;
	height:400px;
	text-align:center;
}
.filtros{
	font-size:20px;
}
.body{
height:100%;
width:100%;
font-family:Arial;
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
    padding:5px;
}
.button:hover{
	background-color:#575796;
	cursor:pointer;
	transition:0.2s;
}
.textbox{
    width: 100px;         
    border: none;
    border-bottom:2px solid #fff;
    outline: none;
    height: 40px;
    color: black;
    font-size:16px;
    background-color:transparent;

}
</style>
</head>
<body class="body FondoG">
<div>
 <jsp:include page="menu.jsp"></jsp:include>
 <br><br><br> <br><br><br>
 
 <%
HttpSession sesion = request.getSession();
String nombre=(String)sesion.getAttribute("Fuser");
		 
if(sesion.getAttribute("Fuser")==null){
		response.sendRedirect("IniciarSesion.jsp");
}else if(nombre==null){
		response.sendRedirect("IniciarSesion.jsp");
}

%>
<form method="post" action="servletInicioDocente" name="formInicioDocente">

<div class="contenedor">
<div class="filtros">
<h1>Mis cursos</h1><br>
Buscar por año: <input type="number" class="textbox" name="txtAnioCurso"> <input type="submit" value="Aceptar" class="button" name="btnAnioCurso" onclick="return getConfirmacionFiltroAnio()"> <br><br>
<input type="submit" value="Eliminar filtros" class="button" width="150px" name="btnEliminarFiltros"> <br><br>
</div>
<table id="tablaCursosDocente" border="1">
<thead>
<tr>
	<th></th>
	<th>Materia</th>
	<th>Carrera</th>
	<th>Semestre</th>
	<th>Año</th>
</tr>
</thead>
<tbody>
<%
ArrayList<Curso> listaCur= null;

if(request.getAttribute("listaCursos")!=null)
{
		listaCur=(ArrayList<Curso>) request.getAttribute("listaCursos");
	}

if(listaCur != null)		
{
	for(Curso cur : listaCur){
	
	Materia mat=cur.getMateria();
	Docente doc=cur.getDocente();
	Carrera car=cur.getCarrera();
	
	%>
		<tr>
			<td><a href="servletCursoDetalle?idCurso=<%=cur.getIdCurso()%>">Ver curso</a></td>
			<td> <%= mat.getDescripcion() %></td>
			<td> <%=car.getDescripcionCarrera() %></td>
			<td> <%=cur.getSemestreCurso() %></td>
			<td> <%=cur.getAnioCurso() %></td>
			
	</tr>
	<% } 
}
%>
	</tbody>
</table>
</div>
<script type="text/javascript">
function getConfirmacionFiltroAnio(){
	var a = document.forms["formInicioDocente"]["txtAnioCurso"].value;
	if (a == null || a == "") {
	      alert("Debe rellenar el campo año");
	      return false;
	    }
	return confirm("¿Desea aplicar el filtro?");
	}
</script>
</form>
</div>
</body>
</html>