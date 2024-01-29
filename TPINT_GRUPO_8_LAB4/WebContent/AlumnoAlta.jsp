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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="css/estilos.css" type="text/css" rel="stylesheet"/>

<style type="text/css">
html { 
background: url(img/fondoIniciarSesion.png) no-repeat center center fixed; 
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
}

.cuadroAltaUno{
position: absolute;
top: 38%;
left: 30%;
transform: translate(-50%, -21%);
width: 480px;
height: 500px;
padding: 15px 30px;
box-sizing: border-box;
background: rgba(0,0,0,0.1);           
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
width: 60%; 
border: none;
border-bottom:2px solid #fff;
outline: none;
height: 40px;
color: black;
font-size:16px;
background-color:transparent;
}
              
.cuadroAltaDos{
position: absolute;
top: 38%;
left: 65.3%;
transform: translate(-50%, -21%);
width: 480px;
height: 500px;
padding: 15px 30px;
box-sizing: border-box;
background: rgba(0,0,0,0.1);           
}
</style>

</head>

<body onLoad="myOnLoad()">
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
		response.sendRedirect("InicioDocente.jsp");
		
	}
}else{
	response.sendRedirect("inicio.jsp");

}
%>
<form method="get" action="servletAlumnoAlta">


<%
	String proximoLegajo = "";
	if(request.getAttribute("proximoLegajo") != null){
		proximoLegajo = (String)request.getAttribute("proximoLegajo");
	}
%>


<div><!-- -->
<jsp:include page="menu.jsp"></jsp:include>

<div class="cuadroAltaUno">
<br>
<h2>Alta alumnos</h2>
<br>
Legajo: <input type="text" value="<%=proximoLegajo %>" readonly class="textbox">
<br><br>
Dni: <input class="textbox" type="text" pattern="^[1-9]\d*$" title="Se debe rellenar con números" minlength="7" maxlength="10" name="dniAlumno" placeholder="Ingrese DNI" required>
<br><br>
Nombre completo: <input class="textbox" type="text" name="nombreAlumno" placeholder="Ingrese nombre y apellido" maxlength="50" required>
<br><br>
Fecha de nacimiento: <input class="textbox" type="date" name="fechaNacimientoAlumno" placeholder="Ingrese fecha" required>
<br><br><br>
<input class="button" type="submit" value="Aceptar" name="btnAceptar" onclick="return getConfirmacionAlta();">
<br><br>
</div>
<div class="cuadroAltaDos">
<br><br><br><br>
Nacionalidad:
<select class="textbox" name="nacionalidad" required>
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
<br><br>
Provincia:
<select class="textbox" name="provinciaAlumno" id="provinciaDelAlumno" onchange="cargar_localidades()" required>
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
<br><br>
Localidad:
<select class="textbox" name="localidadAlumno" id="localidadDelAlumno" required></select>
<br><br>
Direccion: <input class="textbox" type="text" name="direccionAlumno" placeholder="Ingrese direccion"  maxlength="20" required>
<br><br>
Telefono: <input class="textbox" type="tel" name="telefonoAlumno" placeholder="Ingrese telefono" pattern="[\+]\d{12}" title="Debe ser formato: +54 11 12345678 (todo junto)" required>
<br><br>
</div>

<select name="localidades" id="localidadDelAlumno2">
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
</div><!--  -->

<%
boolean agrego;
if(request.getAttribute("agrego")!=null){
	agrego = (boolean)request.getAttribute("agrego");
if(agrego){
	%><script>alert("Se agregó al alumno con éxito.")</script><%
}else{
%><script>alert("No se pudo agregar al alumno.")</script><% 	
}
} 
%>
<%
boolean dniExistente;
if(request.getAttribute("dniExistente")!=null){
	dniExistente = (boolean)request.getAttribute("dniExistente");
if(dniExistente){
	%><script>alert("Error: DNI existente.")</script><%
}
}
%>

<script type="text/javascript">
function getConfirmacionAlta(){
	return confirm("¿Desea dar de alta al alumno?");
	}

function myOnLoad() {
		var earrings = document.getElementById('localidadDelAlumno2');
		earrings.style.visibility = 'hidden';
		 cargar_localidades();
	
	}

function cargar_localidades() {
	document.getElementById("localidadDelAlumno").options.length = 0;
	
	var x = document.getElementById("localidadDelAlumno2");
	var array = new Array();
	var a = new Array();
	var b = new Array();
	for (i = 0; i < x.length; i++) { 
		
		array.push(x.options[i].text);
		a.push(x.options[i].value);
		b.push(x.options[i].getAttribute('data-uid'));
		
}
	 addOptions("localidadAlumno", array, a,b);
	}

function addOptions(domElement, array, a,b) {
	 var select = document.getElementsByName(domElement)[0];
	 var inde = document.getElementById('provinciaDelAlumno').value;

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

</form>
</body>
</html>