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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style  type="text/css">
html { 
background: url(img/fondoIniciarSesion.png) no-repeat center center fixed; 
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
}
.cuadroEditarUno{
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
.cuadroEditarDos{
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
String name=(String)sesion.getAttribute("Fuser");
int num=0;		 
if(sesion.getAttribute("Fuser")==null){
		response.sendRedirect("IniciarSesion.jsp");
}else if(name==null){
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
<form action="servletAlumnoBML" method="post">
<div class="FondoG">
<jsp:include page="menu.jsp"></jsp:include>
<div class="contenedor">
<%
Alumno alumno = null;
String legajo = null;
String dni = null;
String nombre = null;
String fecha = null;
String nacionalidad = null;
String provincia = null;
String localidad = null;
String direccion = null;
String telefono = null;
if(request.getAttribute("alumnoModificar") != null){
	alumno = (Alumno)request.getAttribute("alumnoModificar");
	legajo = alumno.getLegajoAlumno();
	dni = alumno.getDniAlumno();
	nombre = alumno.getNombreYApellidoAlumno();
	fecha = alumno.getFechaNacimientoAlumno();
	direccion = alumno.getDireccionAlumno();
	telefono = alumno.getTelefonoAlumno();
	nacionalidad = alumno.getPais().getIdPais();
	provincia = alumno.getProvincia().getIdProvincia();
	localidad = alumno.getLocalidad().getIdLocalidad();
}
%>

<div class="cuadroEditarUno">
<br>
<h2>Modificar alumno</h2><br>
Legajo:<input class="textbox" name="legajoAlumno" value="<%=legajo%>" readonly><br>
Dni: <input class="textbox" value="<%=dni %>" readonly><br>
Nombre completo: <input class="textbox" type="text" name="nombreAlumno" value="<%=nombre%>" maxlength="50" required><br>
Fecha de nacimiento: <input class="textbox" type="date" name="fechaNacimientoAlumno" value="<%=fecha%>" required><br><br>
<input class="button" type="submit" name="btnAceptar" value="Aceptar" onclick="return getConfirmacionModificar();" ><br>
</div>
<div class="cuadroEditarDos">
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
		if(pais.getIdPais().equals(nacionalidad)){
			%>
			<option value="<%= pais.getIdPais()%>" selected="true"><%=pais.getDescripcionPais()%></option>			
			<%
		}
		else{
			%>
			<option value="<%= pais.getIdPais()%>"><%=pais.getDescripcionPais()%></option>
			<%
		}
		%>
		
<%}
	}%>
</select><br>
Provincia: 
<select class="textbox" name="provinciaAlumno" id="provinciaDelAlumno" onchange="cargar_localidades()" required>
<%
ArrayList<Provincia> listaProvincias = null;
if(request.getAttribute("listaProvincias") != null){
	listaProvincias = (ArrayList<Provincia>)request.getAttribute("listaProvincias");
}

if(listaProvincias != null){
	
	for(Provincia prov: listaProvincias){	
		if(prov.getIdProvincia().equals(provincia)){
			%>
			<option value="<%= prov.getIdProvincia()%>" selected="true"><%=prov.getDescripcionProvincia()%></option>			
			<%
		}
		else{
			%>
			<option value="<%= prov.getIdProvincia()%>"><%=prov.getDescripcionProvincia()%></option>
			<%
		}
		%>
		
<%}
	}%>
</select><br>
Localidad:
<select class="textbox" name="localidadAlumno" id="localidadDelAlumno" required>
<%
ArrayList<Localidad> listaLocalidades = null;
if(request.getAttribute("listaLocalidadesXProvincia") != null){
	listaLocalidades = (ArrayList<Localidad>)request.getAttribute("listaLocalidadesXProvincia");
}

if(listaLocalidades != null){
	
	for(Localidad loc: listaLocalidades){		
		if(loc.getIdLocalidad().equals(localidad)){
			%>
			<option value="<%= loc.getIdLocalidad()%>"  selected="true"><%=loc.getDescripcionLocalidad()%></option>			
			<%
		}
		else{
			%>
			<option value="<%= loc.getIdLocalidad()%>"><%=loc.getDescripcionLocalidad()%></option>
			<%
		}
		%>
		
<%}
	}%>

</select><br>
Direccion: <input class="textbox" type="text" name="direccionAlumno" value="<%=direccion%>" maxlength="20" required><br>
Telefono: <input class="textbox" type="text" name="telefonoAlumno" value="<%=telefono%>" pattern="[\+]\d{12}" title="Debe ser formato: +54 11 12345678 (todo junto)" required required><br>
</div>


<select name="localidadAlumno2" id="localidadDelAlumno2">
<%
ArrayList<Localidad> listaLocalidades2 = null;
if(request.getAttribute("listaLocalidades") != null){
	listaLocalidades2 = (ArrayList<Localidad>)request.getAttribute("listaLocalidades");
}

if(listaLocalidades2 != null){
	
	for(Localidad loc: listaLocalidades2){		
			%>
			<option value="<%= loc.getProvincia().getIdProvincia()%>" data-uid="<%=loc.getIdLocalidad()%>"><%=loc.getDescripcionLocalidad()%></option>				
<%}
	}%>

</select><br>


<script type="text/javascript">
function getConfirmacionModificar(){
	return confirm("¿Desea confirmar la modificacion?");
	}

function myOnLoad() {
		var earrings = document.getElementById('localidadDelAlumno2');
		earrings.style.visibility = 'hidden';	
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
	 var inde = document.getElementById("provinciaDelAlumno").value;

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

</div>
</div>

</form>
</body>
</html>
