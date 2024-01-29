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
import negocio.NotasNeg;
import negocioImpl.AlumnoNegImpl;
import negocioImpl.CursoNegImpl;
import negocioImpl.EstadoAcademicoNegImpl;
import negocioImpl.NotasNegImpl;

/**
 * Servlet implementation class servletAlumnoCalificar
 */
@WebServlet("/servletAlumnoCalificar")
public class servletAlumnoCalificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	AlumnoNeg aneg= new AlumnoNegImpl();
	CursoNeg cneg= new CursoNegImpl();
	NotasNeg nneg= new NotasNegImpl();
	EstadoAcademicoNeg eaneg= new EstadoAcademicoNegImpl();
	
    public servletAlumnoCalificar() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idCurso = request.getParameter("idCurso");
		Curso cur= cneg.obtenerCurso(idCurso);	
		ArrayList<Notas> detalleCurso= cneg.obtenerAlumnosCurso(cur);
		ArrayList<EstadoAcademico> ea= eaneg.obtenerTodosLosEstadosAcademicos();
		boolean califico=false;
		
		request.setAttribute("estadosAcademicos", ea);
		request.setAttribute("califico", califico);
		request.setAttribute("curso", cur);
		request.setAttribute("detalleCurso", detalleCurso);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/AlumnoCalificar.jsp");  
        rd.forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idCurso = request.getParameter("idCurso");
		Curso cur= cneg.obtenerCurso(idCurso);
		ArrayList<Notas> detalleCurso= cneg.obtenerAlumnosCurso(cur);
		Alumno alu = aneg.obtenerAlumno((String)request.getParameter("txtLegajoAlumno"));
		ArrayList<EstadoAcademico> ea= eaneg.obtenerTodosLosEstadosAcademicos();
		EstadoAcademico listaEA = eaneg.obtenerEstadoAcademico(request.getParameter("listaEstadosAcademicos"));
		boolean califico=false;
		
		
		if(request.getParameter("btnFiltroLegajo")!= null) {
			
			detalleCurso = cneg.obtenerAlumnoLegajo(cur, alu);
			
		}
		
		if(request.getParameter("btnFiltroEstadoAcademico")!=null) {
			
			detalleCurso= cneg.obtenerAlumnosEstadoAcademico(cur, listaEA);
		}
		
		
		int cont=1;
				
		if(request.getParameter("btnCalificar") != null) {
			
			for(Notas not : detalleCurso) {
				
				not.setNotaPrimerParcial(Float.parseFloat(request.getParameter("txtNotaPrimerParcial"+ Integer.toString(cont))));
				not.setNotaSegundoParcial(Float.parseFloat(request.getParameter("txtNotaSegundoParcial"+ Integer.toString(cont))));
				not.setNotaPrimerRecuperatorio(Float.parseFloat(request.getParameter("txtNotaPrimerRecuperatorio"+ Integer.toString(cont))));
				not.setNotaSegundoRecuperatorio(Float.parseFloat(request.getParameter("txtNotaSegundoRecuperatorio"+ Integer.toString(cont))));
				not.setEstadoAcademico(eaneg.calcularEstadoAcademico(not));
				califico=nneg.calificarAlumno(not);
				
				cont++;
			}
			
			
		}
		request.setAttribute("estadosAcademicos", ea);
		request.setAttribute("curso", cur);
		request.setAttribute("califico", califico);
		request.setAttribute("detalleCurso", detalleCurso);
		RequestDispatcher rd = request.getRequestDispatcher("/AlumnoCalificar.jsp"); 
		
        rd.forward(request, response);

	}

}