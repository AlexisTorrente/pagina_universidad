package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DocentesDao;
import dao.LocalidadDao;
import dao.PaisDao;
import dao.ProvinciaDao;
import daoImpl.LocalidadDaoImpl;
import entidades.Docente;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;
import entidades.Usuario;

public class DocentesDaoImpl implements DocentesDao{

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "lab4_tpfinal";
	private static final String modificar = "UPDATE docentes SET dniDocente= ?, nombreYapellidoDocente= ?, correoDocente= ?, "
			+ "fechaNacimientoDocente= ?, nacionalidadDocente= ?, provinciaDocente= ?, localidadDocente= ?, direccionDocente= ?, "
			+ "telefonoDocente= ? WHERE legajoDocente= ?";
	
	
	ProvinciaDao provdao= new ProvinciaDaoImpl();
	PaisDao pdao= new PaisDaoImpl();
	LocalidadDao ldao= new LocalidadDaoImpl();
	
	@Override
	public Docente leerDocente(String legajoDocente) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from docentes WHERE legajoDocente= '"+ legajoDocente+"'";
		Docente d= new Docente();
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Provincia prov= provdao.leerProvincia(rs.getString("provinciaDocente"));
				Pais p= pdao.leerPais(rs.getString("nacionalidadDocente"));
				Localidad loc= ldao.leerLocalidad(rs.getString("localidadDocente"));
				
				d.setPais(p);
				d.setProvincia(prov);
				d.setLocalidad(loc);
				d.setCorreoDocente(rs.getString("correoDocente"));
				d.setDireccionDocente(rs.getString("direccionDocente"));
				d.setDniDocente(rs.getString("dniDocente"));
				d.setEstadoDocente(rs.getBoolean("estadoDocente"));
				d.setFechaNacimientoDocente(rs.getString("fechaNacimientoDocente"));
				d.setLegajoDocente(rs.getString("legajoDocente"));
				d.setNombreYApellidoDocente(rs.getString("nombreYApellidoDocente"));
				d.setTelefonoDocente(rs.getString("telefonoDocente"));
			}

			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return d;
	}
	
	
	public boolean existeDniDocente(String dni) {
		
		boolean existe=false;
		
		String consulta = "SELECT * from docentes WHERE dniDocente='" + dni + "'";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	
		ResultSet rs;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			rs=st.executeQuery(consulta);
			while(rs.next()) {
				String dniObtenido = rs.getString("dniDocente");
				if(dni.equals(dniObtenido)) {
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
	
	
	public boolean agregarDocente(Docente docente) {
		boolean agrego=false;
		int estadoDocente = docente.isEstadoDocente() ? 1: 0; // le asigna 1 si es TRUE y 0 si es FALSE.
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	
		int filas=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query = "Insert into docentes(legajoDocente,dniDocente,nombreYapellidoDocente,correoDocente,fechaNacimientoDocente,nacionalidadDocente,provinciaDocente,localidadDocente,direccionDocente,telefonoDocente,estadoDocente) "
						+ "values ('"+docente.getLegajoDocente()+"','"+docente.getDniDocente()+
						"','"+docente.getNombreYApellidoDocente()+"','"+docente.getCorreoDocente()+
						"','"+docente.getFechaNacimientoDocente()+"','"+docente.getPais().getIdPais()+
						"','"+docente.getProvincia().getIdProvincia()+"','"+docente.getLocalidad().getIdLocalidad()+
						"','"+docente.getDireccionDocente()+"','"+docente.getTelefonoDocente()+"','"+estadoDocente+"')";
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
	
	
	public String proximoLegajoDocente() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="SELECT MAX(legajoDocente)+1 FROM docentes";
		String proximoLegajo = null;
		try{

			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				proximoLegajo = rs.getString(1);			
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			if(proximoLegajo == null) {
				proximoLegajo = "1";
			}
		
		}
		return proximoLegajo;
	}

	@Override
	public ArrayList<Docente> leerDocentes() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Docente> listaDocentes = new ArrayList<Docente>();
		
		String query = "SELECT * FROM docentes where estadoDocente=1";
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Docente doc = new Docente();
				
				Provincia prov= provdao.leerProvincia(rs.getString("provinciaDocente"));
				Pais p= pdao.leerPais(rs.getString("nacionalidadDocente"));
				Localidad loc= ldao.leerLocalidad(rs.getString("localidadDocente"));
				
				doc.setLegajoDocente(rs.getString("legajoDocente"));
				doc.setDniDocente(rs.getString("dniDocente"));
				doc.setNombreYApellidoDocente(rs.getString("nombreYapellidoDocente"));
				doc.setCorreoDocente(rs.getString("correoDocente"));
				doc.setFechaNacimientoDocente((rs.getDate("fechaNacimientoDocente")).toString());
				doc.setPais(p);
				doc.setProvincia(prov);
				doc.setLocalidad(loc);
				doc.setDireccionDocente(rs.getString("direccionDocente"));
				doc.setTelefonoDocente(rs.getString("telefonoDocente"));
				
				listaDocentes.add(doc);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaDocentes;
	}
	
	public boolean bajaDocente(String legajo)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean mod = false;
		String query = "UPDATE docentes SET estadoDocente=0 WHERE legajoDocente="+legajo;
		try 
		{
			statement = conexion.prepareStatement(query);
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				mod = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return mod;
	}


	@Override
	public boolean actualizarDocente(Docente doc) {
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
				
				cn = DriverManager.getConnection(host + dbName, user, pass);
				statement = cn.prepareStatement(modificar);
				
				statement.setString(1, doc.getDniDocente());
				statement.setString(2, doc.getNombreYApellidoDocente());
				statement.setString(3, doc.getCorreoDocente());
				statement.setString(4, doc.getFechaNacimientoDocente());
				statement.setString(5, doc.getPais().getIdPais());
				statement.setString(6, doc.getProvincia().getIdProvincia());
				statement.setString(7, doc.getLocalidad().getIdLocalidad());
				statement.setString(8, doc.getDireccionDocente());
				statement.setString(9, doc.getTelefonoDocente());
				statement.setString(10, doc.getLegajoDocente());
				
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


	@Override
	public ArrayList<Docente> leerDocentesProvincia(String idProvincia) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Docente> listaDocentes = new ArrayList<Docente>();
		
		String query = "SELECT * FROM docentes where estadoDocente=1 and provinciaDocente="+idProvincia;
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Docente doc = new Docente();
				
				Provincia prov= provdao.leerProvincia(rs.getString("provinciaDocente"));
				Pais p= pdao.leerPais(rs.getString("nacionalidadDocente"));
				Localidad loc= ldao.leerLocalidad(rs.getString("localidadDocente"));
				
				doc.setLegajoDocente(rs.getString("legajoDocente"));
				doc.setDniDocente(rs.getString("dniDocente"));
				doc.setNombreYApellidoDocente(rs.getString("nombreYapellidoDocente"));
				doc.setCorreoDocente(rs.getString("correoDocente"));
				doc.setFechaNacimientoDocente((rs.getDate("fechaNacimientoDocente")).toString());
				doc.setPais(p);
				doc.setProvincia(prov);
				doc.setLocalidad(loc);
				doc.setDireccionDocente(rs.getString("direccionDocente"));
				doc.setTelefonoDocente(rs.getString("telefonoDocente"));
				
				listaDocentes.add(doc);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaDocentes;
	}
	
	@Override
	public ArrayList<Docente> leerDocentesLocalidad(String idLocalidad) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Docente> listaDocentes = new ArrayList<Docente>();
		
		String query = "SELECT * FROM docentes where estadoDocente=1 and localidadDocente="+idLocalidad;
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Docente doc = new Docente();
				
				Provincia prov= provdao.leerProvincia(rs.getString("provinciaDocente"));
				Pais p= pdao.leerPais(rs.getString("nacionalidadDocente"));
				Localidad loc= ldao.leerLocalidad(rs.getString("localidadDocente"));
				
				doc.setLegajoDocente(rs.getString("legajoDocente"));
				doc.setDniDocente(rs.getString("dniDocente"));
				doc.setNombreYApellidoDocente(rs.getString("nombreYapellidoDocente"));
				doc.setCorreoDocente(rs.getString("correoDocente"));
				doc.setFechaNacimientoDocente((rs.getDate("fechaNacimientoDocente")).toString());
				doc.setPais(p);
				doc.setProvincia(prov);
				doc.setLocalidad(loc);
				doc.setDireccionDocente(rs.getString("direccionDocente"));
				doc.setTelefonoDocente(rs.getString("telefonoDocente"));
				
				listaDocentes.add(doc);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaDocentes;
	}
	
	@Override
	public ArrayList<Docente> leerDocentesDni(String dni) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Docente> listaDocentes = new ArrayList<Docente>();
		
		String query = "SELECT * FROM docentes where estadoDocente=1 and dniDocente="+dni;
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Docente doc = new Docente();
				
				Provincia prov= provdao.leerProvincia(rs.getString("provinciaDocente"));
				Pais p= pdao.leerPais(rs.getString("nacionalidadDocente"));
				Localidad loc= ldao.leerLocalidad(rs.getString("localidadDocente"));
				
				doc.setLegajoDocente(rs.getString("legajoDocente"));
				doc.setDniDocente(rs.getString("dniDocente"));
				doc.setNombreYApellidoDocente(rs.getString("nombreYapellidoDocente"));
				doc.setCorreoDocente(rs.getString("correoDocente"));
				doc.setFechaNacimientoDocente((rs.getDate("fechaNacimientoDocente")).toString());
				doc.setPais(p);
				doc.setProvincia(prov);
				doc.setLocalidad(loc);
				doc.setDireccionDocente(rs.getString("direccionDocente"));
				doc.setTelefonoDocente(rs.getString("telefonoDocente"));
				
				listaDocentes.add(doc);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaDocentes;
	}
	
	@Override
	public ArrayList<Docente> leerDocentesNombre(String nombre) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Docente> listaDocentes = new ArrayList<Docente>();
		
		String query = "SELECT * FROM docentes where estadoDocente=1 and nombreYapellidoDocente LIKE '%"+nombre+"%'";
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Docente doc = new Docente();
				
				Provincia prov= provdao.leerProvincia(rs.getString("provinciaDocente"));
				Pais p= pdao.leerPais(rs.getString("nacionalidadDocente"));
				Localidad loc= ldao.leerLocalidad(rs.getString("localidadDocente"));
				
				doc.setLegajoDocente(rs.getString("legajoDocente"));
				doc.setDniDocente(rs.getString("dniDocente"));
				doc.setNombreYApellidoDocente(rs.getString("nombreYapellidoDocente"));
				doc.setCorreoDocente(rs.getString("correoDocente"));
				doc.setFechaNacimientoDocente((rs.getDate("fechaNacimientoDocente")).toString());
				doc.setPais(p);
				doc.setProvincia(prov);
				doc.setLocalidad(loc);
				doc.setDireccionDocente(rs.getString("direccionDocente"));
				doc.setTelefonoDocente(rs.getString("telefonoDocente"));
				
				listaDocentes.add(doc);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaDocentes;
	}
	
	@Override
	public ArrayList<Docente> leerDocentesLegajo(String legajo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Docente> listaDocentes = new ArrayList<Docente>();
		
		String query = "SELECT * FROM docentes where estadoDocente=1 and legajoDocente="+legajo;
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Docente doc = new Docente();
				
				Provincia prov= provdao.leerProvincia(rs.getString("provinciaDocente"));
				Pais p= pdao.leerPais(rs.getString("nacionalidadDocente"));
				Localidad loc= ldao.leerLocalidad(rs.getString("localidadDocente"));
				
				doc.setLegajoDocente(rs.getString("legajoDocente"));
				doc.setDniDocente(rs.getString("dniDocente"));
				doc.setNombreYApellidoDocente(rs.getString("nombreYapellidoDocente"));
				doc.setCorreoDocente(rs.getString("correoDocente"));
				doc.setFechaNacimientoDocente((rs.getDate("fechaNacimientoDocente")).toString());
				doc.setPais(p);
				doc.setProvincia(prov);
				doc.setLocalidad(loc);
				doc.setDireccionDocente(rs.getString("direccionDocente"));
				doc.setTelefonoDocente(rs.getString("telefonoDocente"));
				
				listaDocentes.add(doc);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaDocentes;
	}


	@Override
	public Docente leerDocenteCorreo(String correoDocente) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from docentes WHERE correoDocente= '"+ correoDocente+"'";
		Docente d= new Docente();
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Provincia prov= provdao.leerProvincia(rs.getString("provinciaDocente"));
				Pais p= pdao.leerPais(rs.getString("nacionalidadDocente"));
				Localidad loc= ldao.leerLocalidad(rs.getString("localidadDocente"));
				
				d.setPais(p);
				d.setProvincia(prov);
				d.setLocalidad(loc);
				d.setCorreoDocente(rs.getString("correoDocente"));
				d.setDireccionDocente(rs.getString("direccionDocente"));
				d.setDniDocente(rs.getString("dniDocente"));
				d.setEstadoDocente(rs.getBoolean("estadoDocente"));
				d.setFechaNacimientoDocente(rs.getString("fechaNacimientoDocente"));
				d.setLegajoDocente(rs.getString("legajoDocente"));
				d.setNombreYApellidoDocente(rs.getString("nombreYApellidoDocente"));
				d.setTelefonoDocente(rs.getString("telefonoDocente"));
			}

			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return d;
	}
	

	
}
