package presentacion;

import java.io.IOException;
import java.text.ParseException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import entidades.Localidad;

/**
 * Servlet implementation class servletAlumnoAlta
 */
@WebServlet("/servletAlumnoAlta")
public class servletAlumnoAlta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletAlumnoAlta() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			AgregarAlumno(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void AgregarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		AlumnoNeg alumnoNeg = new AlumnoNegImpl();		
		String proximoLegajo = alumnoNeg.proximoLegajo();
		
		PaisNeg paisNeg = new PaisNegImpl();
		ArrayList<Pais> listaPaises = paisNeg.leerPaises();
		
		ProvinciaNeg provinciaNeg = new ProvinciaNegImpl();
		ArrayList<Provincia> listaProvincias = provinciaNeg.leerProvincias();
		
		LocalidadNeg localidadNeg = new LocalidadNegImpl();
		ArrayList<Localidad> listaLocalidades = localidadNeg.leerTodasLocalidades();
		
		request.setAttribute("agrego", null);
		boolean agrego = false;
		boolean dniExistente = false;
		
		if(request.getParameter("btnAceptar") != null) {
			proximoLegajo = alumnoNeg.proximoLegajo();
			
			Pais pais = new Pais();
			Provincia provincia = new Provincia();
			Localidad localidad = new Localidad();

			String oldDateString = request.getParameter("fechaNacimientoAlumno").toString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = format.parse(oldDateString);
			String fecha = (parsed.getYear()+1900)+"/"+(parsed.getMonth()+1)+"/"+parsed.getDate();

			String legajo = proximoLegajo;
			String dni = request.getParameter("dniAlumno");
			String nombreCompleto = request.getParameter("nombreAlumno");
			String fechaNacimiento = fecha;
			pais.setIdPais(String.valueOf(request.getParameter("nacionalidad")));
			provincia.setIdProvincia(String.valueOf(request.getParameter("provinciaAlumno")));
			localidad.setIdLocalidad(String.valueOf(request.getParameter("localidadAlumno")));
			String direccion = request.getParameter("direccionAlumno");
			String telefono = request.getParameter("telefonoAlumno");
				
			if(!alumnoNeg.existeDni(dni)) {
				agrego = alumnoNeg.altaAlumno(legajo, dni, nombreCompleto, fecha, pais, provincia, localidad, direccion, telefono);
				request.setAttribute("agrego", agrego);
			}
			else {
				dniExistente = true;
				request.setAttribute("agrego", null);
			}
			
			proximoLegajo = alumnoNeg.proximoLegajo();		
			request.setAttribute("dniExistente", dniExistente);
		}
			
		request.setAttribute("listaPaises", listaPaises);	
		request.setAttribute("listaProvincias", listaProvincias);
		request.setAttribute("listaLocalidades", listaLocalidades);
		request.setAttribute("proximoLegajo", proximoLegajo);
	
		RequestDispatcher rd = request.getRequestDispatcher("AlumnoAlta.jsp");  
	    rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
