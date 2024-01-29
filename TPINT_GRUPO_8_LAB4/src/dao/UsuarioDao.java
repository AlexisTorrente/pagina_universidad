package dao;

import entidades.Usuario;

public interface UsuarioDao {

	public int LeerUsuario(Usuario user);
	public boolean agregarUsuario(Usuario usuario);
	public boolean existeCorreoUsuario(String correo);
	public Usuario obtenerUsuario(String correo);
	public boolean actualizarUsuario(Usuario user);
	
	
	
}
