<%@page import="entidades.Alumno"%>
<%@page import="entidades.Materia"%>
<%@page import="entidades.Carrera"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="javax.servlet.http.HttpSession" %> 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta curso</title>
<style type="text/css">
.Fondo{
   padding:0;
    margin: 0 auto;
	width:100%;
	height:100vh;
	background:url(img/fondoIniciarSesion.png);
	 background-size: cover;
    background-position:center ;
    background-repeat: no-repeat;
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
        .cuadroAltaUno{
            margin:0 auto;
	margin:40px auto;
	text-align: center;
            width: 480px;
            height: 400px;
            padding: 15px 30px;
            box-sizing: border-box;
            background: rgba(0,0,0,0.1); 
                 
        }

</style>
</head>
<body class="Fondo">
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
			response.sendRedirect("InicioDocente.jsp");
			
		}
	}else{
		response.sendRedirect("inicio.jsp");

	}
%>
<form method="get" action="servletCursoAlta">

<div>
<%int proximoId=-1;
if(request.getAttribute("pId")!=null){
	proximoId = (int)request.getAttribute("pId");
}
%>
<br><br><br><br><br>
<div class="cuadroAltaUno" >



<h2>Alta Curso</h2>
<br>
Id del curso: <%=proximoId %>
<br><br>Materia : 
<select name="Materia">
<%
ArrayList<Materia> listaMaterias = null;
if(request.getAttribute("ListaM") != null){
	listaMaterias = (ArrayList<Materia>)request.getAttribute("ListaM");
}

if(listaMaterias != null){
	
	for(Materia mat: listaMaterias){		
		%>
		<option value="<%= mat.getIdMateria()%>"><%=mat.getDescripcion()%></option>
<%}
	}%>
</select>
<br><br>Carrera :  
<select name="Carrera">
<%
ArrayList<Carrera> listaCarrera = null;
if(request.getAttribute("ListaC") != null){
	listaCarrera = (ArrayList<Carrera>)request.getAttribute("ListaC");
}

if(listaCarrera != null){
	
	for(Carrera cat: listaCarrera){		
		%>
		<option value="<%= cat.getIdCarrera()%>"><%=cat.getDescripcionCarrera()%></option>
<%}
	}%>
</select>
<br><br>Legajo del docente : <input type="text" name="txtLegajoDocente" pattern="^[1-9]\d*$" title="Se debe rellenar con números"   placeholder="Ingrese un legajo" required>
<br><br>Semestre del curso : 
<select name="Semestre">
  <option value="Primer semestre"selected>Primer semestre</option>
  <option value="Segundo semestre" >Segundo semestre</option>
</select>
<br><br>Año  : <input type="text" name="txtAnio" pattern="^[1-9]\d*$" title="Se debe rellenar con números"   placeholder="Ingrese un año" required>

<br><br><input type="submit" name="btAceptar" value="Aceptar" onclick="return getConfirmacionAlta();" class="button">

<br>
<br>
<%
	String error;
	if(request.getAttribute("error")!=null){
	error = request.getAttribute("error").toString();
	%>
	<%=error%>
<%} 
%>
</div>
</div>

<script type="text/javascript">
function getConfirmacionAlta(){
	return confirm("¿Desea dar de alta el curso?");
	}

</script>
</form>
</body>
</html>