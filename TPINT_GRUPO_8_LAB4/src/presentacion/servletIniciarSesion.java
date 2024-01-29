package presentacion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Usuario;
import negocio.UsuarioNeg;
import negocioImpl.UsuarioNegImpl;

@WebServlet("/servletIniciarSesion")
public class servletIniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public servletIniciarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAceptar")!=null) {
			
		 Usuario User = new Usuario();
		 User.setCorreoUsuario(request.getParameter("txtCorreo"));		 
		 User.setClaveUsuario(request.getParameter("txtContrasenia"));

		UsuarioNeg Us=new UsuarioNegImpl();
		
		int num = Us.BuscarUsuario(User);
		
		if(num==1 || num==2) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("Fuser", request.getParameter("txtCorreo"));
			sesion.setAttribute("TipoUsuario", num);

		}

		request.setAttribute("DevolverUsuario", num);	
		
		}
		

		RequestDispatcher rd = request.getRequestDispatcher("/IniciarSesion.jsp");
		rd.forward(request, response);
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
	}

}
