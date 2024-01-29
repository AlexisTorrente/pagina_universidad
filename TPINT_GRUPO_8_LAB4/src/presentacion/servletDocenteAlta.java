package presentacion;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.DDIV;

import dao.ProvinciaDao;
import daoImpl.LocalidadDaoImpl;
import daoImpl.PaisDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;
import negocio.LocalidadNeg;
import negocio.PaisNeg;
import negocio.ProvinciaNeg;
import negocioImpl.DocenteNegImpl;
import negocioImpl.LocalidadNegImpl;
import negocioImpl.PaisNegImpl;
import negocioImpl.ProvinciaNegImpl;
import negocioImpl.UsuarioNegImpl;

@WebServlet("/servletDocenteAlta")
public class servletDocenteAlta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public servletDocenteAlta() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			llamarAgregarDocente(request,response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void llamarAgregarDocente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		DocenteNegImpl nd = new DocenteNegImpl();
		UsuarioNegImpl nu = new UsuarioNegImpl();
		PaisNeg negPais = new PaisNegImpl();
		ProvinciaNeg negProvincia = new ProvinciaNegImpl();
		LocalidadNeg negLocalidad = new LocalidadNegImpl();
		
	
		
		
		
		request.setAttribute("agrego", null);
		boolean agrego=false;
		
		ArrayList<Pais> listaPaises = new ArrayList<Pais>();
		listaPaises = negPais.leerPaises();
		ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();
		listaProvincias = negProvincia.leerProvincias();
		ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
		listaLocalidades = negLocalidad.leerTodasLocalidades();

		if(request.getParameter("btnAceptar") != null) {
			
			Pais pais = new Pais();
			Provincia prov = new Provincia();
			Localidad loc = new Localidad();
			
			
			String oldDateString = request.getParameter("fechaNacimientoDocente").toString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = format.parse(oldDateString);
			String fecha = (parsed.getYear()+1900)+"/"+(parsed.getMonth()+1)+"/"+parsed.getDate();

			
			String legajo = nd.SolicitarProximoLegajoDocente();
			String dni = request.getParameter("dniDocente");
			String nombreCompleto = request.getParameter("nombreDocente");
			String correo = request.getParameter("CorreoDocente");
			String clave = request.getParameter("claveDocente");
			String fechaNacimiento = fecha;
			pais.setIdPais(String.valueOf(request.getParameter("nacionalidadDocente")));
			prov.setIdProvincia(String.valueOf(request.getParameter("provinciaDocente")));
			loc.setIdLocalidad(String.valueOf(request.getParameter("localidadDocente")));
			String direccion = request.getParameter("direccionDocente");
			String telefono = request.getParameter("telefonoDocente");
			
			if(nd.verificarDni(dni) && nu.VerificarCorreo(correo)) {
				
				if(nu.AgregarUsuario(correo, clave) && nd.AgregarDocente(legajo, dni, nombreCompleto, correo, fechaNacimiento, pais, prov, loc, direccion, telefono)) {
					agrego = true;
				}
			}else {
				agrego = false;
			}
			
			request.setAttribute("agrego", agrego);
			
			
							
		}
		
		/// CONTINUAR APARTIR DE ACA
		

		String proximoLegajo = nd.SolicitarProximoLegajoDocente();
		
		
		//REQUEST DISPATCHER
		request.setAttribute("pLegajo", proximoLegajo);
		request.setAttribute("listaPaises", listaPaises);	
		request.setAttribute("listaProvincias", listaProvincias);
		request.setAttribute("listaLocalidades", listaLocalidades);
		
		RequestDispatcher rd = request.getRequestDispatcher("/DocenteAlta.jsp");  
        rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

