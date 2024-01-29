<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "javax.servlet.http.HttpSession" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menú</title>
<link href="css/estilos.css" type="text/css" rel="stylesheet"/>

</head>
<body>

 <% 
 HttpSession sesion = request.getSession();
 String nombre=(String)sesion.getAttribute("Fuser");
		 
 if(sesion.getAttribute("Fuser")==null){
		response.sendRedirect("IniciarSesion.jsp");
 }else if(nombre==null){
		response.sendRedirect("IniciarSesion.jsp");

 } %>
 
<div class="menuAdmin">
<ul >
  <li> <img src="img/logo.png" style="width:64px;height:64px; " > </li>
  <li><a class="active" href="#home">Cursos</a></li>
  <li><a href="#news">Docente</a></li>
  		<ul class="navalu">
		   <li><a href="DocenteAlta.jsp">Registrar docente</a></li>
		   <li><a href="servletDocenteBML?Param=1">Modificar y eliminar docente</a></li>
		</ul>
  <li><a href="#contact">Alumnos</a>
  		 <ul class="navalu">
			   <li><a href="AlumnoAlta.jsp">Registrar alumno</a></li>
		   	   <li><a href="AlumnoBML.jsp">Modificar y eliminar alumno</a></li>
		</ul>
	</li>	
  <li style="float:right"><form method="get" action="Menu Administrador.jsp">
			 	 <input type="submit" class="logout"  name="btncerrar" value="Cerrar sesión"/>
			</form></li>
    <li class="usuario" style="float:right"><a><%out.println(nombre); %></a></li>		 

</ul>
</div>
	<% 
	if(request.getParameter("btncerrar")!=null) {
		
		if(sesion.getAttribute("Fuser")!=null) {
			sesion.removeAttribute("Fuser");
			
			response.sendRedirect("IniciarSesion.jsp");
		}
	}
	%>


</body>
</html>