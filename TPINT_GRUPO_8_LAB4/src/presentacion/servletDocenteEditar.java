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

import entidades.Docente;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;
import entidades.Usuario;
import negocio.LocalidadNeg;
import negocio.PaisNeg;
import negocio.ProvinciaNeg;
import negocioImpl.DocenteNegImpl;
import negocioImpl.LocalidadNegImpl;
import negocioImpl.PaisNegImpl;
import negocioImpl.ProvinciaNegImpl;
import negocioImpl.UsuarioNegImpl;


@WebServlet("/servletDocenteEditar")
public class servletDocenteEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DocenteNegImpl nd = new DocenteNegImpl();
	UsuarioNegImpl nu = new UsuarioNegImpl();
	PaisNeg negPais = new PaisNegImpl();
	ProvinciaNeg negProvincia = new ProvinciaNegImpl();
	LocalidadNeg negLocalidad = new LocalidadNegImpl();
	
    public servletDocenteEditar() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Pais> listaPaises = new ArrayList<Pais>();
		listaPaises = negPais.leerPaises();
		ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();
		listaProvincias = negProvincia.leerProvincias();
		ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
		listaLocalidades = negLocalidad.leerTodasLocalidades();
		
		
		
		Docente docente = new Docente();
		Usuario user = new Usuario();
		
		if(request.getAttribute("docenteEditar")!=null) {
		docente = (Docente)request.getAttribute("docenteEditar");
		}
		
		if(request.getAttribute("usuarioEditar")!=null) {
		user = (Usuario)request.getAttribute("usuarioEditar");
		}
		
		ArrayList<Localidad> LocalidadesDocente = new ArrayList<Localidad>();
		LocalidadesDocente = negLocalidad.leerLocalidades(docente.getProvincia().getIdProvincia());
	
		
		String legajo = docente.getLegajoDocente().toString();
		String dni = docente.getDniDocente();
		String nombreCompleto = docente.getNombreYApellidoDocente();
		String correo = docente.getCorreoDocente();
		String fechaNacimiento = docente.getFechaNacimientoDocente();
		String pais = docente.getPais().getIdPais();
		String prov = docente.getProvincia().getIdProvincia();
		String loc = docente.getLocalidad().getIdLocalidad();
		String direccion = docente.getDireccionDocente();
		String telefono = docente.getTelefonoDocente();
		
		int idUsuario = user.getIdUsuario();
		String claveUsuario = user.getClaveUsuario();
		
		
		//REQUEST DISPATCHER
				request.setAttribute("legajo", legajo);
				request.setAttribute("dni", dni);
				request.setAttribute("nombreCompleto", nombreCompleto);
				request.setAttribute("correo", correo);
				request.setAttribute("fechaNacimiento", fechaNacimiento);
				request.setAttribute("pais", pais);
				request.setAttribute("provincia", prov);
				request.setAttribute("localidad", loc);
				request.setAttribute("direccion", direccion);
				request.setAttribute("telefono", telefono);
				request.setAttribute("listaPaises", listaPaises);	
				request.setAttribute("listaProvincias", listaProvincias);
				request.setAttribute("listaLocalidades", listaLocalidades);
				request.setAttribute("listaLocalidadesDocente", LocalidadesDocente);
				
				request.setAttribute("idUsuario", idUsuario);
				request.setAttribute("claveUsuario", claveUsuario);
				
				RequestDispatcher rd = request.getRequestDispatcher("/DocenteEditar.jsp");  
		        rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		

		boolean modifico=false;
		boolean cond1 = false;
		boolean cond2 = false;


	
			
			Pais pais = new Pais();
			Provincia prov = new Provincia();
			Localidad loc = new Localidad();
			
			pais.setIdPais(String.valueOf(request.getParameter("nacionalidadDocente")));
			prov.setIdProvincia(String.valueOf(request.getParameter("provinciaDocente")));
			loc.setIdLocalidad(String.valueOf(request.getParameter("localidadDocente")));
			
			
			String oldDateString = request.getParameter("fechaNacimientoDocente").toString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed;
			String fecha="";
			try {
				parsed = format.parse(oldDateString);
				fecha = (parsed.getYear()+1900)+"/"+(parsed.getMonth()+1)+"/"+parsed.getDate();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Docente docente = new Docente();
			Usuario usuario = new Usuario();

			
			docente.setLegajoDocente(request.getParameter("legajoDocente"));
			docente.setDniDocente(request.getParameter("dniDocente"));
			docente.setNombreYApellidoDocente(request.getParameter("nombreDocente"));
			docente.setCorreoDocente(request.getParameter("CorreoDocente"));
			docente.setFechaNacimientoDocente(fecha);
			docente.setPais(pais);
			docente.setProvincia(prov);
			docente.setLocalidad(loc);
			docente.setDireccionDocente(request.getParameter("direccionDocente"));
			docente.setTelefonoDocente(request.getParameter("telefonoDocente"));
			
			usuario.setClaveUsuario(request.getParameter("claveDocente"));
			usuario.setCorreoUsuario(request.getParameter("CorreoDocente"));
			usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUser")));
			
			//if(nd.verificarDni(request.getParameter("dniDocente").toString()) && nu.VerificarCorreo(request.getParameter("CorreoDocente").toString())) {
				
				if(nu.llamarActualizarUsuario(usuario)){
					cond1 = true;
				} 
				
				if(nd.llamarActualizarDocente(docente)) {
					cond2 = true;
				}
			//}
			
			if(cond1 && cond2 ) {
				modifico = true;
			}
			
			request.setAttribute("modifico", modifico);
			
		
		//REQUEST DISPATCHER
		
		RequestDispatcher rd = request.getRequestDispatcher("/servletDocenteBML?Param=2");
        rd.forward(request, response);
	}

}
