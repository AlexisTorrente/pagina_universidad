package presentacion;

import java.io.IOException;
import java.util.ArrayList;

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
import negocio.DocenteNeg;
import negocio.LocalidadNeg;
import negocio.PaisNeg;
import negocio.ProvinciaNeg;
import negocio.UsuarioNeg;
import negocioImpl.DocenteNegImpl;
import negocioImpl.LocalidadNegImpl;
import negocioImpl.PaisNegImpl;
import negocioImpl.ProvinciaNegImpl;
import negocioImpl.UsuarioNegImpl;

/**
 * Servlet implementation class servletDocenteBML
 */
@WebServlet("/servletDocenteBML")
public class servletDocenteBML extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DocenteNeg dn = new DocenteNegImpl();
	UsuarioNeg un = new UsuarioNegImpl();
	PaisNeg negPais = new PaisNegImpl();
	ProvinciaNeg negProvincia = new ProvinciaNegImpl();
	LocalidadNeg negLocalidad = new LocalidadNegImpl();
    
    public servletDocenteBML() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnEliminar")!=null)
		{
			String legajo = String.valueOf(request.getParameter("legajoDocente"));
			boolean elimino = dn.llamarBajaDocente(legajo);
			
			if(elimino) {
			
			ArrayList<Docente> listaDeDocentes = dn.obtenerDocentes();
			ArrayList<Pais> listaPaises = new ArrayList<Pais>();
			listaPaises = negPais.leerPaises();
			ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();
			listaProvincias = negProvincia.leerProvincias();
			ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
			listaLocalidades = negLocalidad.leerTodasLocalidades();
			
			request.setAttribute("listaPaises", listaPaises);	
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaLocalidades", listaLocalidades);	
			request.setAttribute("listaDeDocentes", listaDeDocentes);
			request.setAttribute("eliminoDocente", elimino);
			
			RequestDispatcher rd = request.getRequestDispatcher("/DocenteBML.jsp");  
	        rd.forward(request, response);
			}
		}
		
		if(request.getParameter("Param")!=null) {
		
		ArrayList<Docente> listaDeDocentes = dn.obtenerDocentes();
		boolean modi;
		ArrayList<Pais> listaPaises = new ArrayList<Pais>();
		listaPaises = negPais.leerPaises();
		ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();
		listaProvincias = negProvincia.leerProvincias();
		ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
		listaLocalidades = negLocalidad.leerTodasLocalidades();
		
			if(request.getAttribute("modifico")!=null) {
				modi = (boolean)request.getAttribute("modifico");
				request.setAttribute("modificoDocente", modi);
				
			}
			
		request.setAttribute("listaPaises", listaPaises);	
		request.setAttribute("listaProvincias", listaProvincias);
		request.setAttribute("listaLocalidades", listaLocalidades);	
		request.setAttribute("listaDeDocentes", listaDeDocentes);
		
		RequestDispatcher rd = request.getRequestDispatcher("/DocenteBML.jsp");  
        rd.forward(request, response);
		}
		
		if(request.getParameter("btnModificar")!=null) {
			Pais pais = new Pais();
			Provincia prov = new Provincia();
			Localidad loc = new Localidad();
			
			String legajo = String.valueOf(request.getParameter("legajoDocente").toString());
			String dni = String.valueOf(request.getParameter("dniDocente"));
			String nombreCompleto = String.valueOf(request.getParameter("nyaDocente"));
			String correo = String.valueOf(request.getParameter("correoDocente"));
			String fechaNacimiento = String.valueOf(request.getParameter("fnDocente"));
			pais.setIdPais(String.valueOf(request.getParameter("paisDocente")));
			prov.setIdProvincia(String.valueOf(request.getParameter("provinciaDocente")));
			loc.setIdLocalidad(String.valueOf(request.getParameter("localidadDocente")));
			String direccion = String.valueOf(request.getParameter("direccionDocente"));
			String telefono = String.valueOf(request.getParameter("telefonoDocente"));
			
			Docente doc = new Docente();
			
			doc.setLegajoDocente(legajo);
			doc.setDniDocente(dni);
			doc.setNombreYApellidoDocente(nombreCompleto);
			doc.setCorreoDocente(correo);
			doc.setFechaNacimientoDocente(fechaNacimiento);
			doc.setPais(pais);
			doc.setProvincia(prov);
			doc.setLocalidad(loc);
			doc.setDireccionDocente(direccion);
			doc.setTelefonoDocente(telefono);
			
			
			Usuario u = un.llamarObtenerUsuario(correo);
			
			
			
			request.setAttribute("docenteEditar", doc);
			request.setAttribute("usuarioEditar", u);
			RequestDispatcher rd = request.getRequestDispatcher("/servletDocenteEditar");  
	        rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarProvincia")!=null) {
			String idProvincia = String.valueOf(request.getParameter("provincias"));
			ArrayList<Docente> listaDeDocentes = dn.obtenerDocentesProvincia(idProvincia);
			ArrayList<Pais> listaPaises = new ArrayList<Pais>();
			listaPaises = negPais.leerPaises();
			ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();
			listaProvincias = negProvincia.leerProvincias();
			ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
			listaLocalidades = negLocalidad.leerTodasLocalidades();
			
			request.setAttribute("listaDeDocentes", listaDeDocentes);
			request.setAttribute("listaPaises", listaPaises);	
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaLocalidades", listaLocalidades);	
			
			RequestDispatcher rd = request.getRequestDispatcher("/DocenteBML.jsp");  
	        rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarLocalidad")!=null) {
			String idLocalidad = String.valueOf(request.getParameter("localidades"));
			ArrayList<Docente> listaDeDocentes = dn.obtenerDocentesLocalidad(idLocalidad);
			ArrayList<Pais> listaPaises = new ArrayList<Pais>();
			listaPaises = negPais.leerPaises();
			ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();
			listaProvincias = negProvincia.leerProvincias();
			ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
			listaLocalidades = negLocalidad.leerTodasLocalidades();
			
			request.setAttribute("listaDeDocentes", listaDeDocentes);
			request.setAttribute("listaPaises", listaPaises);	
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaLocalidades", listaLocalidades);	
			
			RequestDispatcher rd = request.getRequestDispatcher("/DocenteBML.jsp");  
	        rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarDni")!=null) {
			String dni = String.valueOf(request.getParameter("dni"));
			ArrayList<Docente> listaDeDocentes = dn.obtenerDocentesDni(dni);
			ArrayList<Pais> listaPaises = new ArrayList<Pais>();
			listaPaises = negPais.leerPaises();
			ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();
			listaProvincias = negProvincia.leerProvincias();
			ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
			listaLocalidades = negLocalidad.leerTodasLocalidades();
			
			request.setAttribute("listaDeDocentes", listaDeDocentes);
			request.setAttribute("listaPaises", listaPaises);	
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaLocalidades", listaLocalidades);	
			
			RequestDispatcher rd = request.getRequestDispatcher("/DocenteBML.jsp");  
	        rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarNombre")!=null) {
			String nombre = String.valueOf(request.getParameter("nombre"));
			ArrayList<Docente> listaDeDocentes = dn.obtenerDocentesNombre(nombre);
			ArrayList<Pais> listaPaises = new ArrayList<Pais>();
			listaPaises = negPais.leerPaises();
			ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();
			listaProvincias = negProvincia.leerProvincias();
			ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
			listaLocalidades = negLocalidad.leerTodasLocalidades();
			
			request.setAttribute("listaDeDocentes", listaDeDocentes);
			request.setAttribute("listaPaises", listaPaises);	
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaLocalidades", listaLocalidades);	
			
			RequestDispatcher rd = request.getRequestDispatcher("/DocenteBML.jsp");  
	        rd.forward(request, response);
		}
		
		if(request.getParameter("btnFiltrarLegajo")!=null) {
			String legajo = String.valueOf(request.getParameter("legajo"));
			ArrayList<Docente> listaDeDocentes = dn.obtenerDocentesLegajo(legajo);
			ArrayList<Pais> listaPaises = new ArrayList<Pais>();
			listaPaises = negPais.leerPaises();
			ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();
			listaProvincias = negProvincia.leerProvincias();
			ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
			listaLocalidades = negLocalidad.leerTodasLocalidades();
			
			request.setAttribute("listaDeDocentes", listaDeDocentes);
			request.setAttribute("listaPaises", listaPaises);	
			request.setAttribute("listaProvincias", listaProvincias);
			request.setAttribute("listaLocalidades", listaLocalidades);	
			
			RequestDispatcher rd = request.getRequestDispatcher("/DocenteBML.jsp");  
	        rd.forward(request, response);
		}
			
		}
        
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
