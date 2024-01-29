package negocioImpl;

import daoImpl.UsuarioDaoImpl;
import entidades.Usuario;
import negocio.UsuarioNeg;


public class UsuarioNegImpl implements UsuarioNeg {

	UsuarioDaoImpl du = new UsuarioDaoImpl();

	@Override
	public boolean AgregarUsuario(String correo, String clave) {
		Usuario us = new Usuario();
		
		us.setCorreoUsuario(correo);
		us.setClaveUsuario(clave);
		us.setEsAdmin(false);
		
		
		return du.agregarUsuario(us);
	}
	
	@Override
	public boolean VerificarCorreo(String correo) {
		
		return !du.existeCorreoUsuario(correo);
	}

	public int BuscarUsuario(Usuario user) {
		
		return du.LeerUsuario(user);
	}

	@Override
	public Usuario llamarObtenerUsuario(String correo) {
		
		return du.obtenerUsuario(correo);
	}

	@Override
	public boolean llamarActualizarUsuario(Usuario user) {
		return du.actualizarUsuario(user);
	}
	
	
	
}
