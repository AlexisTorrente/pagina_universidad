package daoImpl;

import dao.UsuarioDao;
import entidades.Alumno;
import entidades.Curso;
import entidades.EstadoAcademico;
import entidades.Localidad;
import entidades.Materia;
import entidades.Pais;
import entidades.Provincia;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDaoImpl implements UsuarioDao{
	
	private String host = "jdbc:mysql://localhost:3306/";
	private String usuariobd = "root";
	private String pass = "root";
	private String dbName = "lab4_tpfinal";
	private static final String modificar = "UPDATE usuarios SET correoUsuario= ?, claveUsuario= ? WHERE idUsuario= ?";

	
	public int LeerUsuario(Usuario user) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Statement statement;
		ResultSet rs;
		String query="select * from usuarios WHERE correoUsuario= '" + user.getCorreoUsuario() +"'";
		Connection conn = null;
		int TipoUsuario=0; // 0 = no existe / 1 = Admin / 2 = Profesors
		
		try{

			conn=DriverManager.getConnection(host + dbName, usuariobd, pass);
			statement=conn.createStatement();
			rs = statement.executeQuery(query);
		
			if(rs.next()) {
				
				if(user.getClaveUsuario().equals(rs.getString("claveUsuario"))) {
					
					
					if( rs.getBoolean("esAdmin")==true) {
						TipoUsuario = 1;

					}else {
						TipoUsuario = 2; 
					}
				}
				else {
					TipoUsuario= 0;
				}
				
			}
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
		
		}
		return TipoUsuario;
	}
	
	
	public boolean agregarUsuario(Usuario usuario) {
		boolean agrego=false;
		int esAdmin = usuario.isEsAdmin() ? 1: 0; // le asigna 1 si es TRUE y 0 si es FALSE.
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	
		int filas=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, usuariobd,pass);
			Statement st = cn.createStatement();
			String query = "Insert into usuarios(correoUsuario,claveUsuario,esAdmin) "
						+ "values ('"+usuario.getCorreoUsuario()+"','"+usuario.getClaveUsuario()+"','"+esAdmin+"')";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if (filas==1) {
			agrego=true;
		}
		
		return agrego;
	}
	
	
	public boolean existeCorreoUsuario(String correo) {
		
		boolean existe=false;
		String consulta = "SELECT * from usuarios WHERE correoUsuario='" + correo + "'";
	
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	
		ResultSet rs;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, usuariobd,pass);
			Statement st = cn.createStatement();
			rs=st.executeQuery(consulta);
			while(rs.next()) {
				String correoObtenido = rs.getString("correoUsuario");
				if(correo.equals(correoObtenido)) {
					existe=true;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return existe;
	}
	
	
public Usuario obtenerUsuario(String correo) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Statement statement;
		ResultSet rs;
		String query="SELECT * FROM usuarios WHERE correoUsuario= '"+ correo +"'";
		Connection conn = null;
		
		Usuario user = new Usuario();
		
		try{

			conn=DriverManager.getConnection(host + dbName, usuariobd, pass);
			statement=conn.createStatement();
			rs = statement.executeQuery(query);
		
			while(rs.next()) {
				
					
					
					user.setIdUsuario(rs.getInt(1));
					user.setCorreoUsuario(rs.getString(2));
					user.setClaveUsuario(rs.getString(3));
					user.setEsAdmin(rs.getBoolean(4));
							
					
		
			}
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
		
		}
		return user;
	}


@Override
public boolean actualizarUsuario(Usuario user) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	boolean actualizo=false;
	Connection cn = null;
	
	
		try{
			
			PreparedStatement statement;
			
			cn = DriverManager.getConnection(host+dbName, usuariobd,pass);
			statement = cn.prepareStatement(modificar);
			
			statement.setString(1, user.getCorreoUsuario());
			statement.setString(2, user.getClaveUsuario());
			statement.setInt(3, user.getIdUsuario());
			
			if(statement.executeUpdate() > 0)
			{
				actualizo = true;
			}
			
			cn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		
		return actualizo;
}


	
}
