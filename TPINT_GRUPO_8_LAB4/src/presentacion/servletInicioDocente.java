package presentacion;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Curso;
import entidades.Docente;
import negocio.CursoNeg;
import negocio.DocenteNeg;
import negocioImpl.CursoNegImpl;
import negocioImpl.DocenteNegImpl;

/**
 * Servlet implementation class servletInicioDocente
 */
@WebServlet("/servletInicioDocente")
public class servletInicioDocente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CursoNeg cneg= new CursoNegImpl();
	DocenteNeg docneg= new DocenteNegImpl();
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletInicioDocente() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String mail= (String)session.getAttribute("Fuser");
		Docente d= docneg.obtenerDocenteCorreo(mail);
		
		ArrayList<Curso> listaCursos = cneg.obtenerCursosDocente(d);
		
		
		request.setAttribute("listaCursos", listaCursos);
		
		RequestDispatcher rd = request.getRequestDispatcher("/InicioDocente.jsp");  
        rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Docente d= docneg.obtenerDocenteCorreo((String)session.getAttribute("Fuser"));
		
		ArrayList<Curso> listaCursos = cneg.obtenerCursosDocente(d);
		
		if(request.getParameter("btnAnioCurso")!= null) {
			
			listaCursos = cneg.obtenerCursosDocenteAnio(d, (String)request.getParameter("txtAnioCurso"));
		}

		request.setAttribute("listaCursos", listaCursos);
		
		RequestDispatcher rd = request.getRequestDispatcher("/InicioDocente.jsp");  
        rd.forward(request, response);
	}

}