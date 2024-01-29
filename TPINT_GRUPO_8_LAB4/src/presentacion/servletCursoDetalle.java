package presentacion;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Alumno;
import entidades.Curso;
import entidades.EstadoAcademico;
import entidades.Notas;
import negocio.AlumnoNeg;
import negocio.CursoNeg;
import negocio.EstadoAcademicoNeg;
import negocioImpl.AlumnoNegImpl;
import negocioImpl.CursoNegImpl;
import negocioImpl.EstadoAcademicoNegImpl;

/**
 * Servlet implementation class servletCursoDetalle
 */
@WebServlet("/servletCursoDetalle")
public class servletCursoDetalle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CursoNeg cneg= new CursoNegImpl();
	AlumnoNeg aneg= new AlumnoNegImpl();
	EstadoAcademicoNeg eaneg= new EstadoAcademicoNegImpl();
    public servletCursoDetalle() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Curso cur= cneg.obtenerCurso(request.getParameter("idCurso"));
		ArrayList<EstadoAcademico> ea= eaneg.obtenerTodosLosEstadosAcademicos();
		ArrayList<Notas> detalleCurso= cneg.obtenerAlumnosCurso(cur);
			
		request.setAttribute("estadosAcademicos", ea);
		request.setAttribute("detalleCurso", detalleCurso);
		request.setAttribute("curso", cur);
		
		RequestDispatcher rd = request.getRequestDispatcher("/CursoDetalle.jsp");  
        rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Curso cur= cneg.obtenerCurso(request.getParameter("idCurso"));
		Alumno alu = aneg.obtenerAlumno((String)request.getParameter("txtLegajoAlumno"));
		ArrayList<EstadoAcademico> ea= eaneg.obtenerTodosLosEstadosAcademicos();
		EstadoAcademico listaEA = eaneg.obtenerEstadoAcademico(request.getParameter("listaEstadosAcademicos"));
		ArrayList<Notas> detalleCurso= cneg.obtenerAlumnosCurso(cur);
				
		if(request.getParameter("btnFiltroLegajo")!= null) {
			
			detalleCurso = cneg.obtenerAlumnoLegajo(cur, alu);
			
		}
		
		if(request.getParameter("btnFiltroEstadoAcademico")!=null) {
			
			detalleCurso= cneg.obtenerAlumnosEstadoAcademico(cur, listaEA);
		}
		

		request.setAttribute("estadosAcademicos", ea);
		request.setAttribute("detalleCurso", detalleCurso);
		request.setAttribute("curso", cur);
		
		RequestDispatcher rd = request.getRequestDispatcher("/CursoDetalle.jsp");  
        rd.forward(request, response);
	}
	
	

}