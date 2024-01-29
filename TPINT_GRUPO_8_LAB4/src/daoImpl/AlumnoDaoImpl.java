package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.AlumnoDao;
import dao.LocalidadDao;
import dao.PaisDao;
import dao.ProvinciaDao;
import entidades.Alumno;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;


public class AlumnoDaoImpl implements AlumnoDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "lab4_tpfinal";
	
	ProvinciaDao provdao= new ProvinciaDaoImpl();
	PaisDao pdao= new PaisDaoImpl();
	LocalidadDao ldao= new LocalidadDaoImpl();
	
	public Alumno leerAlumno(String legajoAlumno) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from Alumnos WHERE legajoAlumno="+ legajoAlumno;

		Alumno a= new Alumno();
		try{

			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				
				Provincia prov= provdao.leerProvincia(rs.getString("provinciaAlumno"));
				Pais p= pdao.leerPais(rs.getString("nacionalidadAlumno"));
				Localidad loc= ldao.leerLocalidad(rs.getString("localidadAlumno"));
				
				a.setNombreYApellidoAlumno(rs.getString("nombreYApellidoAlumno"));
				a.setDniAlumno(rs.getString("dniAlumno"));
				a.setDireccionAlumno(rs.getString("direccionAlumno"));
				a.setEstadoAlumno(rs.getBoolean("estadoAlumno"));
				a.setFechaNacimientoAlumno(rs.getString("fechaNacimientoAlumno"));
				a.setLegajoAlumno(rs.getString("legajoAlumno"));
				a.setPais(p);
				a.setProvincia(prov);
				a.setLocalidad(loc);
				a.setDireccionAlumno(rs.getString("direccionAlumno"));
				a.setTelefonoAlumno(rs.getString("telefonoAlumno"));
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return a;
	}

	public ArrayList<Alumno> leerAlumnos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		String query = "SELECT * FROM alumnos where estadoAlumno=1";
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Alumno alum = new Alumno();
				Pais pais = pdao.leerPais(rs.getString("nacionalidadAlumno"));
				Provincia prov = provdao.leerProvincia(rs.getString("provinciaAlumno"));
				Localidad loc = ldao.leerLocalidad(rs.getString("localidadAlumno"));

				
				alum.setLegajoAlumno(rs.getString("legajoAlumno"));
				alum.setDniAlumno(rs.getString("dniAlumno"));
				alum.setNombreYApellidoAlumno(rs.getString("nombreYApellidoAlumno"));
				alum.setFechaNacimientoAlumno(rs.getString("fechaNacimientoAlumno"));
				alum.setPais(pais);
				alum.setProvincia(prov);
				alum.setLocalidad(loc);
				alum.setDireccionAlumno(rs.getString("direccionAlumno"));
				alum.setTelefonoAlumno(rs.getString("telefonoAlumno"));
				listaAlumnos.add(alum);
				
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaAlumnos;
	}

	public boolean altaAlumno(Alumno alumno) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String legajo  =alumno.getLegajoAlumno();
		String dni = alumno.getDniAlumno();
		String nombre = alumno.getNombreYApellidoAlumno();
		String fecha = alumno.getFechaNacimientoAlumno();
		String idPais = alumno.getPais().getIdPais();
		String idProvincia = alumno.getProvincia().getIdProvincia();
		String idLocalidad = alumno.getLocalidad().getIdLocalidad();
		String direccion = alumno.getDireccionAlumno();
		String telefono = alumno.getTelefonoAlumno();
		boolean estado = true;
		
		int filas=0;
		Connection cn = null;
		
		String query = "INSERT INTO alumnos values('"+legajo+"','"+dni+"','"+nombre+"','"+fecha+"','"+idPais+"','"+idProvincia+"','"+idLocalidad+"','"+direccion+"','"+telefono+"',"+estado+")";
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
		
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return filas == 1;
	}

	public String proximoLegajo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="SELECT MAX(legajoAlumno)+1 FROM alumnos";
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
		
		}
		
		if(proximoLegajo == null)
			proximoLegajo = "1000";
		return proximoLegajo;
	}

	public ArrayList<Alumno> buscarXlegajo(String lalum) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="SELECT * FROM alumnos where estadoAlumno=1 and legajoAlumno="+ lalum;

		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()){
				Alumno alum = new Alumno();
				Pais pais = pdao.leerPais(rs.getString("nacionalidadAlumno"));
				Provincia prov = provdao.leerProvincia(rs.getString("provinciaAlumno"));
				Localidad loc = ldao.leerLocalidad(rs.getString("localidadAlumno"));

				
				alum.setLegajoAlumno(rs.getString("legajoAlumno"));
				alum.setDniAlumno(rs.getString("dniAlumno"));
				alum.setNombreYApellidoAlumno(rs.getString("nombreYApellidoAlumno"));
				alum.setFechaNacimientoAlumno(rs.getString("fechaNacimientoAlumno"));
				alum.setPais(pais);
				alum.setProvincia(prov);
				alum.setLocalidad(loc);
				alum.setDireccionAlumno(rs.getString("direccionAlumno"));
				alum.setTelefonoAlumno(rs.getString("telefonoAlumno"));
				listaAlumnos.add(alum);			
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaAlumnos;
	}

	public ArrayList<Alumno> buscarXNombre(String nombre) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="SELECT * FROM alumnos WHERE estadoAlumno=1 and nombreYapellidoAlumno LIKE '%"+nombre+"%'";
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()){
				Alumno alum = new Alumno();
				Pais pais = pdao.leerPais(rs.getString("nacionalidadAlumno"));
				Provincia prov = provdao.leerProvincia(rs.getString("provinciaAlumno"));
				Localidad loc = ldao.leerLocalidad(rs.getString("localidadAlumno"));

				
				alum.setLegajoAlumno(rs.getString("legajoAlumno"));
				alum.setDniAlumno(rs.getString("dniAlumno"));
				alum.setNombreYApellidoAlumno(rs.getString("nombreYApellidoAlumno"));
				alum.setFechaNacimientoAlumno(rs.getString("fechaNacimientoAlumno"));
				alum.setPais(pais);
				alum.setProvincia(prov);
				alum.setLocalidad(loc);
				alum.setDireccionAlumno(rs.getString("direccionAlumno"));
				alum.setTelefonoAlumno(rs.getString("telefonoAlumno"));
				listaAlumnos.add(alum);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaAlumnos;
	}

	public boolean existeDni(String dni) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from Alumnos";
		boolean existe = false;
		try{

			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				String dniBase = rs.getString("dniAlumno");
				if(dni.equals(dniBase)) {
					existe = true;
				}

			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return existe;
	}

	public boolean bajaAlumno(String legajo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int filas=0;
		Connection cn = null;
		
		String query = "UPDATE alumnos SET estadoAlumno=0 WHERE legajoAlumno="+legajo;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return filas==1;
	}

	public boolean modificarAlumno(Alumno alumno) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String legajo = alumno.getLegajoAlumno();
		String nombre = alumno.getNombreYApellidoAlumno();
		String fecha = alumno.getFechaNacimientoAlumno();
		String idPais = alumno.getPais().getIdPais();
		String idProvincia = alumno.getProvincia().getIdProvincia();
		String idLocalidad = alumno.getLocalidad().getIdLocalidad();
		String direccion = alumno.getDireccionAlumno();
		String telefono = alumno.getTelefonoAlumno();
		
		int filas=0;
		Connection cn = null;
		
		String query = "UPDATE alumnos SET nombreYapellidoAlumno = '"+nombre+"', fechaNacimientoAlumno='"+fecha+"', nacionalidadAlumno='"+idPais+"', provinciaAlumno='"+idProvincia+"', localidadAlumno='"+idLocalidad+"', direccionAlumno='"+direccion+"', telefonoAlumno='"+telefono+"' WHERE legajoAlumno='"+legajo+"'";	
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();			
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return filas == 1;
	}

	public ArrayList<Alumno> buscarXdni(String dni) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="SELECT * FROM alumnos WHERE estadoAlumno=1 and dniAlumno='"+dni+"'";

		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()){
				Alumno alum = new Alumno();
				Pais pais = pdao.leerPais(rs.getString("nacionalidadAlumno"));
				Provincia prov = provdao.leerProvincia(rs.getString("provinciaAlumno"));
				Localidad loc = ldao.leerLocalidad(rs.getString("localidadAlumno"));

				
				alum.setLegajoAlumno(rs.getString("legajoAlumno"));
				alum.setDniAlumno(rs.getString("dniAlumno"));
				alum.setNombreYApellidoAlumno(rs.getString("nombreYApellidoAlumno"));
				alum.setFechaNacimientoAlumno(rs.getString("fechaNacimientoAlumno"));
				alum.setPais(pais);
				alum.setProvincia(prov);
				alum.setLocalidad(loc);
				alum.setDireccionAlumno(rs.getString("direccionAlumno"));
				alum.setTelefonoAlumno(rs.getString("telefonoAlumno"));
				listaAlumnos.add(alum);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaAlumnos;
	}

	public ArrayList<Alumno> buscarXProvincia(String idProvincia) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="SELECT * FROM alumnos WHERE estadoAlumno=1 and provinciaAlumno='"+idProvincia+"'";

		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()){
				Alumno alum = new Alumno();
				Pais pais = pdao.leerPais(rs.getString("nacionalidadAlumno"));
				Provincia prov = provdao.leerProvincia(rs.getString("provinciaAlumno"));
				Localidad loc = ldao.leerLocalidad(rs.getString("localidadAlumno"));

				
				alum.setLegajoAlumno(rs.getString("legajoAlumno"));
				alum.setDniAlumno(rs.getString("dniAlumno"));
				alum.setNombreYApellidoAlumno(rs.getString("nombreYApellidoAlumno"));
				alum.setFechaNacimientoAlumno(rs.getString("fechaNacimientoAlumno"));
				alum.setPais(pais);
				alum.setProvincia(prov);
				alum.setLocalidad(loc);
				alum.setDireccionAlumno(rs.getString("direccionAlumno"));
				alum.setTelefonoAlumno(rs.getString("telefonoAlumno"));
				listaAlumnos.add(alum);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaAlumnos;
	}

	public ArrayList<Alumno> buscarXNacionalidad(String idPais) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="SELECT * FROM alumnos WHERE estadoAlumno=1 and nacionalidadAlumno='"+idPais+"'";

		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()){
				Alumno alum = new Alumno();
				Pais pais = pdao.leerPais(rs.getString("nacionalidadAlumno"));
				Provincia prov = provdao.leerProvincia(rs.getString("provinciaAlumno"));
				Localidad loc = ldao.leerLocalidad(rs.getString("localidadAlumno"));

				
				alum.setLegajoAlumno(rs.getString("legajoAlumno"));
				alum.setDniAlumno(rs.getString("dniAlumno"));
				alum.setNombreYApellidoAlumno(rs.getString("nombreYApellidoAlumno"));
				alum.setFechaNacimientoAlumno(rs.getString("fechaNacimientoAlumno"));
				alum.setPais(pais);
				alum.setProvincia(prov);
				alum.setLocalidad(loc);
				alum.setDireccionAlumno(rs.getString("direccionAlumno"));
				alum.setTelefonoAlumno(rs.getString("telefonoAlumno"));
				listaAlumnos.add(alum);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaAlumnos;
	}
	
	public ArrayList<Alumno> buscarDadosDeBaja() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="SELECT * FROM alumnos WHERE estadoAlumno=0";

		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()){
				Alumno alum = new Alumno();
				Pais pais = pdao.leerPais(rs.getString("nacionalidadAlumno"));
				Provincia prov = provdao.leerProvincia(rs.getString("provinciaAlumno"));
				Localidad loc = ldao.leerLocalidad(rs.getString("localidadAlumno"));

				
				alum.setLegajoAlumno(rs.getString("legajoAlumno"));
				alum.setDniAlumno(rs.getString("dniAlumno"));
				alum.setNombreYApellidoAlumno(rs.getString("nombreYApellidoAlumno"));
				alum.setFechaNacimientoAlumno(rs.getString("fechaNacimientoAlumno"));
				alum.setPais(pais);
				alum.setProvincia(prov);
				alum.setLocalidad(loc);
				alum.setDireccionAlumno(rs.getString("direccionAlumno"));
				alum.setTelefonoAlumno(rs.getString("telefonoAlumno"));
				listaAlumnos.add(alum);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaAlumnos;
	}

	
}
