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
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
</head>
<style  type="text/css">

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
<body onLoad="myOnLoad()">
 <jsp:include page="menu.jsp"></jsp:include>
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
<form method="get" action="servletDocenteAlta">
<%String proximoLegajo="-1";
if(request.getAttribute("pLegajo")!=null){
	proximoLegajo = (String)request.getAttribute("pLegajo");
}
%>

<script>
function moClave() {
  var x = document.getElementById("campoClave");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
</script>

<div class="cuadroAltaUno">
<br>
<h2>Alta Docente</h2> <br>
Legajo: <input type="text" value="<%=proximoLegajo %>" readonly class="textbox"> <br> <br>
DNI: <input type="text" pattern="^[1-9]\d*$" title="Se debe rellenar con números" minlength="7" maxlength="10" placeholder="Ingrese DNI" name="dniDocente" required class="textbox"><br><br>
Correo Electronico: <input type="email" placeholder="Ingrese Correo Electronico" name="CorreoDocente" maxlength="50" required class="textbox"><br><br>
Nombre completo:   <input type="text" placeholder="Ingrese Nombre completo" name="nombreDocente" maxlength="50" required class="textbox"><br><br>
Contraseña: <input type="password" placeholder="Ingrese contraseña"  maxlength="20" name="claveDocente" id="campoClave" required class="textbox">
<i class="far fa-eye" id="togglePassword" style="margin-left: -30px; cursor: pointer;" onclick="moClave()"></i><br><br>
Fecha de nacimiento: <input type="date"  name="fechaNacimientoDocente" required class="textbox"><br><br><br>
<input type="submit" value="Aceptar" name="btnAceptar" onclick="getConfirmacionAlta();" class="button">
</div>
<div class="cuadroAltaDos">
<br><br><br><br>
Nacionalidad:
<select name="nacionalidadDocente" required class="textbox">
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
</select><br><br>
Provincia:
<select name="provinciaDocente" id="provinciaDelDocente" onchange="cargar_localidades()" required class="textbox">
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
</select><br><br>
Localidad:
<select name="localidadDocente" id="localidadDelDocente" required class="textbox">
</select><br><br>
Direccion: <input type="text" placeholder="Ingrese Dirección" name="direccionDocente" maxlength="40" required class="textbox"><br><br>
Telefono: <input type="tel" placeholder="Ingrese número telefonico" name="telefonoDocente" pattern="[\+]\d{12}" title="Debe ser formato: +54 11 12345678 (todo junto)" required class="textbox"><br>
<br><br><br><br><br>
<%
	boolean agregado;
	if(request.getAttribute("agrego")!=null){
	agregado = (boolean)request.getAttribute("agrego");
	if(agregado){
		%><script>alert("Docente agregado correctamente en la base de datos.")</script><% 
	}else{
		%><script>alert("No se pudo agregar al docente en la base de datos.")</script><%
	}
} 
%>
</div>
<script type="text/javascript">
function getConfirmacionAlta(){
	return confirm("¿Desea dar de alta al docente?");
	}

</script>


<select name="localidades" id="localidadDelDocente2">
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

<script>
function myOnLoad() {
		var earrings = document.getElementById('localidadDelDocente2');
		earrings.style.visibility = 'hidden';
		 cargar_localidades();
	
	}
</script>

<script>
function cargar_localidades() {
	document.getElementById("localidadDelDocente").options.length = 0;
	
	var x = document.getElementById("localidadDelDocente2");
	var array = new Array();
	var a = new Array();
	var b = new Array();
	for (i = 0; i < x.length; i++) { 
		
		array.push(x.options[i].text);
		a.push(x.options[i].value);
		b.push(x.options[i].getAttribute('data-uid'));
		
}

	

	 addOptions("localidadDocente", array, a,b);
	}
</script>

<script>
function addOptions(domElement, array, a,b) {
	 var select = document.getElementsByName(domElement)[0];
	 var inde = document.getElementById('provinciaDelDocente').value;

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