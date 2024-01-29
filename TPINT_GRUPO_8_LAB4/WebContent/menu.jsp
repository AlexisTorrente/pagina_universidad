<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="javax.servlet.http.HttpSession" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
<link href="css/estilos.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	

<style>
.menuAdmin{
position:fixed;
width:100%;
height:50px;
}
</style>
</head>
<body>
<%
HttpSession sesion = request.getSession();
String nombre="null";
int num=0;		 
if(sesion.getAttribute("Fuser")!=null) {
	nombre=(String)sesion.getAttribute("Fuser");

}else if(nombre=="null"){
	response.sendRedirect("IniciarSesion.jsp");
}


if(sesion.getAttribute("TipoUsuario")!=null){
	 num= (Integer)sesion.getAttribute("TipoUsuario");
}
 if(num==1){ %>
	<div class="menuAdmin">
<ul >
  <li> <img src="img/logo.png" style="width:64px;height:64px; " > </li>
  <li><a class="active" href="inicio.jsp">Inicio</a></li>
  <li><a href="servletCursoAlta?Param=1">Cursos</a></li>
  <li><a href="#docente">Docente</a>
  		<ul class="navdoc">
			   <li><a href="servletDocenteAlta">Registrar Docente</a></li>
		   	   <li><a href="servletDocenteBML?Param=1">Modificar y eliminar Docente</a></li>
		</ul>
</li>	
  <li><a href="#alumnos">Alumnos</a>
  		 <ul class="navalu">
			   <li><a href="servletAlumnoAlta">Registrar alumno</a></li>
		   	   <li><a href="servletAlumnoBML?Param=1">Modificar y eliminar alumno</a></li>
		</ul>
	</li>	
	<li style="float:right"><a>&nbsp;</a></li>
  <li style="float:right"><form method="get" action="menu.jsp">
			 	 <input type="submit" class="logout"  name="btncerrar" value="Cerrar sesión" onclick="return getCerrarSesion();"/>
			</form></li>
    <li class="usuario" style="float:right"><a><%out.println(nombre);  %></a></li>		 

</ul>
</div>	<% 	}else if(num==2){%>
	<div class="menuAdmin">
<ul >
  <li> <img src="img/logo.png" style="width:64px;height:64px; " > </li>
  <li><a class="active" href="servletInicioDocente">Inicio</a></li>
		<li style="float:right"><a>&nbsp;</a></li>
	
  <li style="float:right"><form method="get" action="menu.jsp">
			 	 <input type="submit" class="logout"  name="btncerrar" value="Cerrar sesión" onclick="return getCerrarSesion();"/>
			</form></li>
    <li class="usuario" style="float:right"><a><%out.println(nombre);  %></a></li>		 

</ul>
</div>
	
	
<%}

%>

<%

if(request.getParameter("btncerrar")!=null) {
	
	if(sesion.getAttribute("Fuser")!=null) {
		sesion.removeAttribute("Fuser");
		response.sendRedirect("IniciarSesion.jsp");
	}
}

%>
<script type="text/javascript">
function getCerrarSesion(){
	return confirm("¿Desea cerrar su sesión actual?");
	}

</script>
</body>
</html>