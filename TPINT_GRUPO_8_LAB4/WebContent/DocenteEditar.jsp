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

<body onLoad="myOnLoad()">
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
			response.sendRedirect("inicio.jsp");
			
		}
	}else{
		response.sendRedirect("inicio.jsp");

	}
%>
<%String legajo="";
String dni = "";
String nombreCompleto = "";
String correo = "";
String fechaNacimiento ="";
String pais = "";
String provincia = "";
String localidad = "";
String direccion = ""; 
String telefono = "";
String clave = "";
int idU = -1;

if(request.getAttribute("legajo")!=null){
	legajo = (String)request.getAttribute("legajo");
}

if(request.getAttribute("dni")!=null){
	dni = (String)request.getAttribute("dni");
}

if(request.getAttribute("nombreCompleto")!=null){
	nombreCompleto = (String)request.getAttribute("nombreCompleto");
}

if(request.getAttribute("correo")!=null){
	correo = (String)request.getAttribute("correo");
}

if(request.getAttribute("fechaNacimiento")!=null){
	fechaNacimiento = (String)request.getAttribute("fechaNacimiento");
}

if(request.getAttribute("pais")!=null){
	pais = (String)request.getAttribute("pais");
}

if(request.getAttribute("provincia")!=null){
	provincia = (String)request.getAttribute("provincia");
}

if(request.getAttribute("localidad")!=null){
	localidad = (String)request.getAttribute("localidad");
}

if(request.getAttribute("direccion")!=null){
	direccion = (String)request.getAttribute("direccion");
}

if(request.getAttribute("telefono")!=null){
	telefono = (String)request.getAttribute("telefono");
}

if(request.getAttribute("claveUsuario")!=null){
	clave = (String)request.getAttribute("claveUsuario");
}

if(request.getAttribute("idUsuario")!=null){
	idU = (int)request.getAttribute("idUsuario");
}


%>

<form action="servletDocenteEditar" method="post" >


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
<div class="cuadroEditarUno">
<br>
<h2>Modificar Docente</h2> <br>
<input type="hidden" name="idUser" value="<%=idU%>"> 
Legajo: <input type="text" value="<%=legajo%>" class="textbox" name="legajoDocente" readonly> <br> <br>
DNI: <input type="text" value="<%=dni%>" class="textbox" name="dniDocente" readonly> <br>  <br>
Correo Electronico: <input type="text" value="<%=correo%>" class="textbox" name="CorreoDocente" readonly> <br> <br>
Nombre completo:     <input type="text" placeholder="Ingrese Nombre completo" maxlength="50" name="nombreDocente" value="<%=nombreCompleto %>" required class="textbox"><br><br>
Contraseña: <input type="password" placeholder="Ingrese contraseña"  maxlength="20" name="claveDocente" value="<%=clave %>"id="campoClave" required class="textbox"> 
<i class="far fa-eye" id="togglePassword" style="margin-left: -30px; cursor: pointer;" onclick="moClave()"></i><br> <br>
Fecha de nacimiento: <input type="date"  name="fechaNacimientoDocente" value="<%=fechaNacimiento %>" required class="textbox"><br> <br>
<input type="submit" value="Aceptar" name="btnAceptarEditar" onclick="return getConfirmacionModificacion();" class="button">
</div>
<div class="cuadroEditarDos">
<br><br><br><br>
Nacionalidad:
<select name="nacionalidadDocente" id="paisDelDocente" required class="textbox">
<%
ArrayList<Pais> listaPaises = null;
if(request.getAttribute("listaPaises") != null){
	listaPaises = (ArrayList<Pais>)request.getAttribute("listaPaises");
}

if(listaPaises != null){
	
	for(Pais paisDocente: listaPaises){		
		%>
		<option value="<%= paisDocente.getIdPais()%>"><%=paisDocente.getDescripcionPais()%></option>
<%}
	}%>
</select><br> <br>
Provincia:
<select name="provinciaDocente" value="<%=provincia %>" id="provinciaDelDocente" onchange="cargar_localidades()"required class="textbox">
<%
ArrayList<Provincia> listaProvincias = null;
if(request.getAttribute("listaProvincias") != null){
	listaProvincias = (ArrayList<Provincia>)request.getAttribute("listaProvincias");
}

if(listaProvincias != null){
	
	for(Provincia provinciaDocente: listaProvincias){		
		%>
		<option value="<%= provinciaDocente.getIdProvincia()%>"><%=provinciaDocente.getDescripcionProvincia()%></option>
<%}
	}%>
</select><br> <br>
Localidad:
<select name="localidadDocente" value=<%=localidad %> id="localidadDelDocente" required class="textbox">
<%
ArrayList<Localidad> listaLocalidadesDocente = null;
if(request.getAttribute("listaLocalidadesDocente") != null){
	listaLocalidadesDocente = (ArrayList<Localidad>)request.getAttribute("listaLocalidadesDocente");
}

if(listaLocalidadesDocente != null){
	
	for(Localidad localidadDocente : listaLocalidadesDocente){		
		%>
		<option value="<%= localidadDocente.getIdLocalidad()%>"><%=localidadDocente.getDescripcionLocalidad()%></option>
<%}
	}%>
</select><br> <br>
Direccion: <input type="text" placeholder="Ingrese Dirección" name="direccionDocente" maxlength="40" value="<%=direccion %>" required class="textbox"><br> <br>
Telefono: <input type="text" placeholder="Ingrese número telefonico" name="telefonoDocente"  value="<%=telefono %>" pattern="[\+]\d{12}" title="Debe ser formato: +54 11 12345678 (todo junto)" required class="textbox"><br><br>
</div>




<script>
var temp = <%=pais%>;
var mySelect = document.getElementById('paisDelDocente');

for(var i, j = 0; i = mySelect.options[j]; j++) {
    if(i.value == temp) {
        mySelect.selectedIndex = j;
        break;
    }
}
</script>

<script>
var temp = <%=provincia%>;
var mySelect = document.getElementById('provinciaDelDocente');

for(var i, j = 0; i = mySelect.options[j]; j++) {
    if(i.value == temp) {
        mySelect.selectedIndex = j;
        break;
    }
}
</script>

<script>
var temp = <%=localidad%>;
var mySelect = document.getElementById('localidadDelDocente');

for(var i, j = 0; i = mySelect.options[j]; j++) {
    if(i.value == temp) {
        mySelect.selectedIndex = j;
        break;
    }
}
</script>

<script type="text/javascript">
function getConfirmacionModificacion(){
	return confirm("¿Desea confirmar los cambios?");
	}

</script>

<script type="text/javascript">
function getConfirmacionVolver(){
	return confirm("¿Desea volver?");
	}

</script>

<select name="localidades" id="localidadDelDocente2">
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
</select>

<script>
function myOnLoad() {
		var earrings = document.getElementById('localidadDelDocente2');
		earrings.style.visibility = 'hidden';
	
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