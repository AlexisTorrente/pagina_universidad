package presentacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlumnoDao;
import dao.CarreraDao;
import dao.MateriaDao;
import daoImpl.AlumnoDaoImpl;
import daoImpl.CarreraDaoImpl;
import daoImpl.CursosDaoImpl;
import daoImpl.MateriaDaoImpl;
import entidades.Alumno;
import entidades.Carrera;
import entidades.Curso;
import entidades.Docente;
import entidades.Materia;
import entidades.Notas;
import negocio.AlumnoNeg;
import negocio.CarreraNeg;
import negocio.CursoNeg;
import negocio.MateriaNeg;
import negocio.NotasNeg;
import negocioImpl.AlumnoNegImpl;
import negocioImpl.CarreraNegImpl;
import negocioImpl.CursoNegImpl;
import negocioImpl.MateriaNegImpl;
import negocioImpl.NotasNegImpl;

/**
 * Servlet implementation class servletNotaAlta
 */
@WebServlet("/servletNotaAlta")
public class servletNotaAlta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AlumnoNeg alum= new AlumnoNegImpl();
	CarreraNeg car= new CarreraNegImpl();
	MateriaNeg mat=new MateriaNegImpl();
	CursoNeg cu= new CursoNegImpl();
	NotasNeg nn=new NotasNegImpl();
	
    public servletNotaAlta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param")!=null) {
			
			ArrayList<Alumno> listaA= alum.leerAlumnos();
			ArrayList<Carrera> listaC= car.leerCarrera();
			ArrayList<Materia> listaM= mat.leerMaterias();
			

			request.setAttribute("ListaA", listaA);
			request.setAttribute("ListaC", listaC);
			request.setAttribute("ListaM", listaM);
			int idCurso = cu.proximoId();
			
			request.setAttribute("pId", idCurso);
			RequestDispatcher rd = request.getRequestDispatcher("/CursoAlta.jsp");  
		    rd.forward(request, response);
			} 
		agregarAlumno(request, response);
	}

	private void agregarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Alumno> listaA= alum.leerAlumnos();
		String Lalum;
		String Nombre;
		ArrayList<String> listaMensajes= new ArrayList<String>();
		Nombre=request.getParameter("txtNombreAlumno").toString();
		Lalum=request.getParameter("txtLegajoAlumno").toString();
	/*----------------------------------------------------------------------------*/	
		if(request.getParameter("btnFiltroLegajo") != null) {
			listaA= alum.buscarXlegajo(Lalum);
			String mate= String.valueOf(request.getParameter("mate"));
			int idCurso = cu.proximoId()-1;
			request.setAttribute("ListaA", listaA);
			request.setAttribute("pId", idCurso);
			request.setAttribute("mat", mate);
			RequestDispatcher rd = request.getRequestDispatcher("/NotaAlta.jsp");  
		    rd.forward(request, response);
		}
	/*----------------------------------------------------------------------------*/		
		if(request.getParameter("btnFiltroNombre") != null) {
			listaA= alum.buscarXNombre(Nombre);
			String mate= String.valueOf(request.getParameter("mate"));
			int idCurso = cu.proximoId()-1;
			request.setAttribute("ListaA", listaA);
			request.setAttribute("pId", idCurso);
			request.setAttribute("mat", mate);
			RequestDispatcher rd = request.getRequestDispatcher("/NotaAlta.jsp");  
		    rd.forward(request, response);
		}
	/*----------------------------------------------------------------------------*/	
		if(request.getParameter("btnBorrarFiltro") != null) {
			listaA= alum.leerAlumnos();
			String mate= String.valueOf(request.getParameter("mate"));
			int idCurso = cu.proximoId()-1;
			request.setAttribute("ListaA", listaA);
			request.setAttribute("pId", idCurso);
			request.setAttribute("mat", mate);
			RequestDispatcher rd = request.getRequestDispatcher("/NotaAlta.jsp");  
		    rd.forward(request, response);
		}
	/*----------------------------------------------------------------------------*/	
		if(request.getParameter("btAgregar") != null) {
			
			Curso cur= new Curso();
			Materia mat=new Materia();
			boolean agrego = false;
			String mensaje="";
			
			
			
			
            cur.setIdCurso(Integer.toString(cu.proximoId()-1));
            mat.setIdMateria(String.valueOf(request.getParameter("mate")));
            String[] alumnos = request.getParameterValues("legajoAlumno");
            List<String> listaAlumnos = Arrays.asList(alumnos);
            
            for(String alu : listaAlumnos) {
            	
            	Alumno alum1= alum.obtenerAlumno(alu);
            	agrego = nn.agregarAlumnoN(cur,mat,alum1);
            	
            	if(agrego) {
    				
    				mensaje="Se agregó correctamente al alumno: '"+ alum1.getNombreYApellidoAlumno() + "', Legajo: "+ alum1.getLegajoAlumno();
    				String mate= String.valueOf(request.getParameter("mate"));
    				int idCurso = cu.proximoId()-1;
    				
    				
    				
    				request.setAttribute("ListaA", listaA);
    				request.setAttribute("pId", idCurso);
    				request.setAttribute("mat", mate);

    				
    				
    			}
    			
    			else {
    				
    				mensaje="No se pudo agregar al alumno: '"+ alum1.getNombreYApellidoAlumno() + "', Legajo: "+ alum1.getLegajoAlumno();
    				String mate= String.valueOf(request.getParameter("mate"));
    				int idCurso = cu.proximoId()-1;
    				
    				
    				
    				request.setAttribute("ListaA", listaA);
    				request.setAttribute("pId", idCurso);
    				request.setAttribute("mat", mate);
    				
    				
    				
    			}	
            	
            	
            	listaMensajes.add(mensaje);
            	
            }
            
            request.setAttribute("listaMensajes", listaMensajes);
            RequestDispatcher rd = request.getRequestDispatcher("/NotaAlta.jsp");  
		    rd.forward(request, response);		
						
			
		}
	/*----------------------------------------------------------------------------*/	
}
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
