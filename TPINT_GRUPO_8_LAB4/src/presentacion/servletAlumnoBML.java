package presentacion;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Alumno;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;
import negocio.AlumnoNeg;
import negocio.LocalidadNeg;
import negocio.PaisNeg;
import negocio.ProvinciaNeg;
import negocioImpl.AlumnoNegImpl;
import negocioImpl.LocalidadNegImpl;
import negocioImpl.PaisNegImpl;
import negocioImpl.ProvinciaNegImpl;

@WebServlet("/servletAlumnoBML")
public class servletAlumnoBML extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlumnoNegImpl alumnoNeg = new AlumnoNegImpl();
	PaisNegImpl paisNeg = new PaisNegImpl();
	ProvinciaNegImpl provinciaNeg = new ProvinciaNegImpl();
	LocalidadNegImpl localidadNeg = new LocalidadNegImpl();
	
	ArrayList<Alumno> listaAlumnos = null;
	ArrayList<Pais> listaPaises = null;
	ArrayList<Provincia> listaProvincias = null;
	ArrayList<Localidad> listaLocalidades = null;
	ArrayList<Localidad> listaLocalidadesXProvincia = null;
	
	boolean elimino = false;
	boolean modifico = false;
	String legajoBaja = null;
	String legajoModificar = null;
       
    public servletAlumnoBML() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("btnModificar") != null) {
			legajoModificar = (String)request.getParameter("legajoAlumno");
			Alumno alumnoModificar = alumnoNeg.obtenerAlumno(legajoModificar);
			listaLocalidades = localidadNeg.leerTodasLocalidades();
			listaLocalidadesXProvincia = localidadNeg.leerLocalidades(alumnoModificar.getProvincia().getIdProvincia());
			listaAlumnos = alumnoNeg.leerAlumnos();
			listaPaises = paisNeg.leerPaises();
			listaProvincias = provinciaNeg.leerProvincias();
			
			request.setAttribute("alumnoModificar", alumnoModificar);
			request.setAttribute("listaPaises", listaPaises);
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaLocalidadesXProvincia", listaLocalidadesXProvincia);
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnoModificar.jsp");  
		    rd.forward(request, response);
		}
		
		
		if(request.getParameter("btnEliminar") != null) {
			legajoBaja = (String)request.getParameter("legajoAlumno");		
			elimino = alumnoNeg.bajaAlumno(legajoBaja);
			listaAlumnos = alumnoNeg.leerAlumnos();
			listaPaises = paisNeg.leerPaises();
			listaProvincias = provinciaNeg.leerProvincias();
			listaLocalidades = localidadNeg.leerTodasLocalidades();
			
			request.setAttribute("elimino", elimino);
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaPaises", listaPaises);
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaAlumnos", listaAlumnos);
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnoBML.jsp");  
		    rd.forward(request, response);
		}
		
		if(request.getParameter("btnLegajoFiltro") != null) {
			listaAlumnos = alumnoNeg.buscarXlegajo((String)request.getParameter("filtroLegajo"));
			listaPaises = paisNeg.leerPaises();
			listaProvincias = provinciaNeg.leerProvincias();
			listaLocalidades = localidadNeg.leerTodasLocalidades();
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaPaises", listaPaises);
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaAlumnos", listaAlumnos);
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnoBML.jsp");  
		    rd.forward(request, response);
		}
		
		if(request.getParameter("btnDniFiltro") != null) {
			listaAlumnos = alumnoNeg.buscarXdni((String)request.getParameter("filtroDni"));
			listaPaises = paisNeg.leerPaises();
			listaProvincias = provinciaNeg.leerProvincias();
			listaLocalidades = localidadNeg.leerTodasLocalidades();
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaPaises", listaPaises);
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaAlumnos", listaAlumnos);
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnoBML.jsp");  
		    rd.forward(request, response);
		}
		
		if(request.getParameter("btnNombreFiltro") != null) {
			
			listaAlumnos = alumnoNeg.buscarXNombre((String)request.getParameter("filtroNombre"));
			listaPaises = paisNeg.leerPaises();
			listaProvincias = provinciaNeg.leerProvincias();
			listaLocalidades = localidadNeg.leerTodasLocalidades();
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaPaises", listaPaises);
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaAlumnos", listaAlumnos);
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnoBML.jsp");  
		    rd.forward(request, response);
		}
		
		if(request.getParameter("btnPaisFiltro") != null) {
			listaAlumnos = alumnoNeg.buscarXNacionalidad((String)request.getParameter("filtroPais"));
			listaPaises = paisNeg.leerPaises();
			listaProvincias = provinciaNeg.leerProvincias();
			listaLocalidades = localidadNeg.leerTodasLocalidades();
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaPaises", listaPaises);
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaAlumnos", listaAlumnos);
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnoBML.jsp");  
		    rd.forward(request, response);
		}
		
		if(request.getParameter("btnProvinciaFiltro") != null) {
			listaAlumnos = alumnoNeg.buscarXProvincia((String)request.getParameter("filtroProvincia"));
			listaPaises = paisNeg.leerPaises();
			listaProvincias = provinciaNeg.leerProvincias();
			listaLocalidades = localidadNeg.leerTodasLocalidades();
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaPaises", listaPaises);
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaAlumnos", listaAlumnos);
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnoBML.jsp");  
		    rd.forward(request, response);
		}
		
		if(request.getParameter("btnQuitarFiltros") != null) {
			listaAlumnos = alumnoNeg.leerAlumnos();
			listaPaises = paisNeg.leerPaises();
			listaProvincias = provinciaNeg.leerProvincias();
			listaLocalidades = localidadNeg.leerTodasLocalidades();
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaPaises", listaPaises);
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaAlumnos", listaAlumnos);
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnoBML.jsp");  
		    rd.forward(request, response);
		}
		
		if(request.getParameter("Param")!=null) {
			listaAlumnos = alumnoNeg.leerAlumnos();
			listaPaises = paisNeg.leerPaises();
			listaProvincias = provinciaNeg.leerProvincias();
			listaLocalidades = localidadNeg.leerTodasLocalidades();
			request.setAttribute("listaLocalidades", listaLocalidades);
			request.setAttribute("listaPaises", listaPaises);
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaAlumnos", listaAlumnos);
			RequestDispatcher rd = request.getRequestDispatcher("/AlumnoBML.jsp");  
		    rd.forward(request, response);
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ModificarAlumno(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ModificarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		AlumnoNegImpl alumnoNeg = new AlumnoNegImpl();
		
		if(request.getParameter("btnAceptar") != null) {
			
			Pais pais = new Pais();
			pais.setIdPais(String.valueOf(request.getParameter("nacionalidad")));
			Provincia provincia = new Provincia();
			provincia.setIdProvincia(String.valueOf(request.getParameter("provinciaAlumno")));
			Localidad localidad = new Localidad();
			localidad.setIdLocalidad(String.valueOf(request.getParameter("localidadAlumno")));
			

			String oldDateString = request.getParameter("fechaNacimientoAlumno").toString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = format.parse(oldDateString);
			String fecha = (parsed.getYear()+1900)+"/"+(parsed.getMonth()+1)+"/"+parsed.getDate();

			String legajo = request.getParameter("legajoAlumno");
			String nombreCompleto = request.getParameter("nombreAlumno");
			String fechaNacimiento = fecha;
			String direccion = request.getParameter("direccionAlumno");
			String telefono = request.getParameter("telefonoAlumno");
				
			modifico = alumnoNeg.modificarAlumno(legajo, nombreCompleto, fechaNacimiento, pais, provincia, localidad, direccion, telefono);
		
			listaAlumnos = alumnoNeg.leerAlumnos();
			listaPaises = paisNeg.leerPaises();
			listaProvincias = provinciaNeg.leerProvincias();
		}
		
		request.setAttribute("listaPaises", listaPaises);
		request.setAttribute("listaProvincias", listaProvincias);
		request.setAttribute("listaAlumnos", listaAlumnos);
		request.setAttribute("modifico", modifico);
		RequestDispatcher rd = request.getRequestDispatcher("/AlumnoBML.jsp");
		rd.forward(request, response);
	}

}
