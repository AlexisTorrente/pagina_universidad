<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <%@ page import="javax.servlet.http.HttpSession" %> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0">
<title>Menu Administrador</title>
<link href="css/estilos.css" type="text/css" rel="stylesheet"/>
<script src="https://kit.fontawesome.com/eb496ab1a0.js" crossorigin="anonymous">
</script>

<style type="text/css">
body{
	margin:0;
	padding:0;
	background-color:#ebebeb;
}
.fondo {
    padding:0;
    margin: 0;
	width:100%;
	height:100vh;
    background: url(img/inicio.png);
    background-size: cover;
    background-position:center ;
    background-repeat: no-repeat;
 
}

.contenedor1{
	margin:auto;
	margin-top:50;
	width:1160px;
	height:500px;
	background-color:;
}
.texto{
  /*  position: absolute; 
    top: 35%;
    left: 35%;*/
  /*  transform: translate(-50%, -50%);*/
    width:500px;
    height:230px; 
    margin:120; 
    margin-top:50;
    font-size:22px;
   /* background-color:#8c8646;*/
    
}


.contenedor2{
	margin:auto;
	margin-top:50;
	width:1160px;
	height:500px;
	/*background-color:#cf27bb;*/
}


.textoimagen{
	float:right;
    width:500px;
    height:330px; 
    margin:120; 
    margin-top:150;
    font-size:22px;
   /* background-color:#cf7827;*/
    font-family:sans-serif;
}

.cajita1{
	display:grid;
	grid-template-columns:repeat(3, 1fr);
	grid-gap: 50px;
	padding: 45px 0px;
}
.grupo3 div a{
color: #f7f7f7; 
text-decoration:none;
width:60px;
line-height:30px;
margin-bottom:25px;
}

@media screen and (max-width:1000px){
	.cajita1{
	display:grid;
	grid-template-columns:repeat(3, 1fr);
	grid-gap: 50px;
	padding: 45px 0px;
}
.grupo3 div a{
color: #f7f7f7; 
text-decoration:none;
width:60px;
line-height:30px;
margin-bottom:25px;
}
}
</style>

</head>
 
<body>

<div class="fondo">
<div class="header">
 <jsp:include page="menu.jsp"></jsp:include>
</div>
 <br> <br> <br> <br> 
 <%

HttpSession sesion = request.getSession();
String nombre=(String)sesion.getAttribute("Fuser");
int num=0;		 
if(sesion.getAttribute("Fuser")==null){
		response.sendRedirect("IniciarSesion.jsp");
}else if(nombre==null){
		response.sendRedirect("IniciarSesion.jsp");
}
if(sesion.getAttribute("TipoUsuario")!=null){
num= (Integer)sesion.getAttribute("TipoUsuario");

	if(num==2){
		response.sendRedirect("servletInicioDocente");
		
	}
}else{
	response.sendRedirect("inicio.jsp");

}
%>

 <div class="contenedor1">
  <br> <br> <br> <br> 
 
 <div class="titulo" style="width:600px;height:100px; margin:120; margin-top:50;">
 <h1 style="font-family:sans-serif;">&nbsp;CAMPUS VIRTUAL GLOBAL-UNT-FACULTAD REGIONAL SACACHISPAS</h1> <br>
 
 </div>
  <div class="texto"> <br>
  
<p>&nbsp;&nbsp;El Campus Virtual de la UNT es un Sistema de Gestión de Contenido Educativo 
 el cual integra sustantivamente las nuevas tecnologías a los procesos educativos 
aprovechando las potencialidades que estas ofrecen. <br>
&nbsp;&nbsp; Nuestro propósito es que los estudiantes puedan encontrar respuestas
a las inquietudes y necesidades académicas extendiendo las fronteras
 de espacio y tiempo.</p>

	</div>



</div>
</div>
<div class="contenedor2">
<br> <br> <br> 
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="img/UNT.png" style="height:350px;width:400;" > 
   
<div class="textoimagen">
<br><br><p>&nbsp;&nbsp;Universidad Nacional Tecnologica (UNT) es una facultad
 orientada a las tecnologías, en ella podemos encontrar grandes mentes como 
 <b> Rocha Felipe, Torrente Alexis, Paez Agustín, Madoery Francisco</b>
 y <b>Sosa Gastón</b>.<br>
 &nbsp;&nbsp;Está facultad fue fundada en 1823 en honor a Tamara Herrera y 
 Claudio Fernandez, grandes profesores de programación que brindaron las herramientas
 y el conocimiento a miles de alumnos.</p>
</div>   
   
<br> <br> <br> 
</div>
<div class="footer" style="background-color:#0F0E2B;width:100%; height:220px;font-family:Arial;">

<div class="cajita1">
	<div class="grupo1" style="width:200px; height:60px;margin:0px auto;float:left;">
			 &nbsp; &nbsp; &nbsp;<img src="img/logo.png" alt="logo unt" style="width:100px; height:100px;">
			<h2 style="color:#f7f7f7;">UNT - GAFFA</h2>
			
		
	</div>
	
	<div class="grupo2"><br>
	<h2 style="color: #49a3f2;">SOBRE NOSOTROS</h2><br>
	<p style="color:#f7f7f7;"> &nbsp;lorem ipsum dolor sit amet </p>
	<p style="color:#f7f7f7;"> &nbsp;lorem ipsum dolor sit amet </p>

	</div>
	<div class="grupo3"><br>
	<h2 style="color:#49a3f2;">NUESTRAS REDES</h2><br>
	<div class="redes" >
		<a href="https://frgp.cvg.utn.edu.ar/" class="fa fa-facebook"></a>
		<a href="https://www.instagram.com/gaston.sosa21/" class="fa fa-instagram"></a>
		<a href="https://twitter.com/TheRock" class="fa fa-twitter"></a>
		<a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" class="fa fa-youtube"></a>
	</div>
    </div>
 </div>
	<div class="grupo4" style="color:#5b58c7; background-color:#0F0E2B;text-align:center;">
		<small>&copy;2021 <b> UNT_GAFFA</b> - Todos los derechos reservados</small>
	</div>


</div>
</body>
</html>