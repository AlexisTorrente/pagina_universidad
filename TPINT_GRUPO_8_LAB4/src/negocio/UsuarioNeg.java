package negocio;

import entidades.Usuario;

public interface UsuarioNeg {
	
	boolean AgregarUsuario(String correo, String clave);
	boolean VerificarCorreo(String correo);
	int BuscarUsuario(Usuario user);
	public Usuario llamarObtenerUsuario(String correo);
	public boolean llamarActualizarUsuario(Usuario user);
}
