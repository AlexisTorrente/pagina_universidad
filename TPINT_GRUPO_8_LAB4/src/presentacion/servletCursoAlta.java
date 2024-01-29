package presentacion;

import java.io.IOException;
import java.util.ArrayList;

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
import negocio.AlumnoNeg;
import negocio.CarreraNeg;
import negocio.CursoNeg;
import negocio.MateriaNeg;
import negocioImpl.AlumnoNegImpl;
import negocioImpl.CarreraNegImpl;
import negocioImpl.CursoNegImpl;
import negocioImpl.MateriaNegImpl;

/**
 * Servlet implementation class servletCursoAlta
 */
@WebServlet("/servletCursoAlta")
public class servletCursoAlta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AlumnoNeg alum = new AlumnoNegImpl();
	CarreraNeg car=new CarreraNegImpl();
	MateriaNeg mat=new MateriaNegImpl();
	CursoNeg cu= new CursoNegImpl();
    public servletCursoAlta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Param")!=null) {
		AlumnoDao alum = new AlumnoDaoImpl();
		CarreraDao car=new CarreraDaoImpl();
		MateriaDao mat=new MateriaDaoImpl();
		CursosDaoImpl cu= new CursosDaoImpl();
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
	    agregarCurso(request, response);
}
	

	private void agregarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AlumnoDao alum = new AlumnoDaoImpl();
		CarreraDao car1=new CarreraDaoImpl();
		MateriaDao mat1=new MateriaDaoImpl();
		CursosDaoImpl cu= new CursosDaoImpl();
		CursoNegImpl nc= new CursoNegImpl ();
		ArrayList<Alumno> listaA= alum.leerAlumnos();
		ArrayList<Carrera> listaC= car1.leerCarrera();
		ArrayList<Materia> listaM= mat1.leerMaterias();
		

		request.setAttribute("ListaA", listaA);
		request.setAttribute("ListaC", listaC);
		request.setAttribute("ListaM", listaM);
		
		
		
		request.setAttribute("error", null);
		String error="";
	String mate="";
			int idCurso = cu.proximoId();
		if(request.getParameter("btAceptar") != null) {
Carrera car=new Carrera();
Materia mat=new Materia();
Docente doc=new Docente();
	
             mate= String.valueOf(request.getParameter("Materia"));
			String Id=Integer.toString(nc.proximoId());
		    mat.setIdMateria(String.valueOf(request.getParameter("Materia")));
		    car.setIdCarrera(String.valueOf(request.getParameter("Carrera")));
		    doc.setLegajoDocente(String.valueOf(request.getParameter("txtLegajoDocente")));
		    String Semestre=String.valueOf(request.getParameter("Semestre"));
		    String anio=request.getParameter("txtAnio");
		    if(nc.VerificarLegajo(doc)) {
		  if(nc.Verfaño(anio))  {
		if(nc.agregarCurso(Id,mat,car,doc,Semestre,anio)) {
			request.setAttribute("pId", idCurso);
			request.setAttribute("mat", mate);
			RequestDispatcher rd = request.getRequestDispatcher("/NotaAlta.jsp");  
		    rd.forward(request, response);

		}
		else{
			error="No se pudo agregar el curso en la base de datos.";
			request.setAttribute("pId", idCurso);
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("/CursoAlta.jsp");  
		    rd.forward(request, response);
		}
		
		  }	else {
			  error="Año invalido.";
				request.setAttribute("pId", idCurso);
				request.setAttribute("error", error);
				RequestDispatcher rd = request.getRequestDispatcher("/CursoAlta.jsp");  
			    rd.forward(request, response);
		  }	
		    }
		  else {
			  error="Legajo docente invalido";
				request.setAttribute("pId", idCurso);
				request.setAttribute("error", error);
				RequestDispatcher rd = request.getRequestDispatcher("/CursoAlta.jsp");  
			    rd.forward(request, response);
		  }
		}
		
		
		
		/*request.setAttribute("pId", idCurso);
		request.setAttribute("mat", mate);
		
		RequestDispatcher rd = request.getRequestDispatcher("/CursoAlta.jsp");  
	    rd.forward(request, response);*/
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}