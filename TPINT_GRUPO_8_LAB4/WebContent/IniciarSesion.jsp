<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/estilos.css" type="text/css" rel="stylesheet"/>
</head>
<style  type="text/css">

body{
	margin:0;
	padding:0;
}
.fondo {
    padding:0;
    margin: 0;
	width:100%;
	height:100vh;
    background: url(img/fondoIniciarSesion.png);
    background-size: cover;
    background-position:center ;
    background-repeat: no-repeat;
}

.iniciar_sesion{
    padding:0;
    margin: 0;
	margin-top:0px;
	width:400px;
	height:100px;
    background-position:center ;
    
}

.arriba{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

.btnAceptar:hover{
	cursor:pointer;
	background-color:#470508;
	font-weight:bold;

}


</style>
<body  class="fondo" >

<center><br><br><br><br><br>
<div class="iniciar_sesion">  
<fieldset>
   <img src="img/login.jpg" style="width:400px;height:530px; opacity:0.9;" > 
   
 <div class="arriba">
    
    <img src="img/logo.png" style="width:100px;height:100px;" > 
 
<br><h1>Iniciar Sesión </h1>
	<form method="get" action="servletIniciarSesion">
		<br><br><input type="text" name="txtCorreo" placeholder="Correo electrónico" style="width: 260px "required/><br><br>
		<input type="password" name="txtContrasenia" placeholder="Contraseña" style="width: 260px; "required/><br><br><br>
		<input class="btnAceptar" type="submit" name="btnAceptar" value="Aceptar" style="width: 200px; height:30px; background-color:#80090f; color:white; "/><br><br><br>		
	<% 
	
	int us=0;	
	if(request.getAttribute("DevolverUsuario")!=null)
	{
		 us= Integer.parseInt(request.getAttribute("DevolverUsuario").toString());

		if( us==1){
			%> <meta http-equiv="refresh" content="0; url=inicio.jsp"> <%
			
		}
		else {
			if(us==2){
				%><meta http-equiv="refresh" content="0; url=servletInicioDocente"> <%
			}
			else{
			%><p style="color:white;"> El mail o la contraseña son incorrectos </p> <%
			}
		}
		
	}	%>
	
	
		<br><br><a class="olvi" href="inicio.jsp" style="color:#cc181b;text-decoration: none;">¿Olvidaste tu contraseña?</a><br><br> 
 
</form>		
 
</div>
</fieldset>
</div>

</center>

</body>
</html>