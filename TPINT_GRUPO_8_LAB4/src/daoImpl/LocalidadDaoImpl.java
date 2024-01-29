package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.LocalidadDao;
import dao.PaisDao;
import dao.ProvinciaDao;
import entidades.Localidad;
import entidades.Materia;
import entidades.Pais;
import entidades.Provincia;

public class LocalidadDaoImpl implements LocalidadDao {
	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "lab4_tpfinal";
	
	PaisDao pdao= new PaisDaoImpl();
	ProvinciaDao provdao= new ProvinciaDaoImpl();
	
	@Override
	public Localidad leerLocalidad(String idLocalidad) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from Localidades WHERE idLocalidad="+ idLocalidad;

		Localidad l= new Localidad();
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
			
				//Pais p= pdao.leerPais(rs.getString("idPais"));
				Provincia prov= provdao.leerProvincia(rs.getString("idProvincia"));
				//l.setPais(p);
				l.setPorvincia(prov);
				l.setIdLocalidad(rs.getString("idLocalidad"));
				l.setDescripcionLocalidad(rs.getString("descripcionLocalidad"));

			}
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return l;
	}

	@Override
	public ArrayList<Localidad> leerLocalidades(String idProvincia) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
		//Pais pais = new Pais("1000", "Argentina");
		ProvinciaDaoImpl daoProvincia = new ProvinciaDaoImpl();
		Provincia provincia = daoProvincia.leerProvincia(idProvincia);
		String query = "Select * from Localidades where idProvincia="+idProvincia;
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Localidad localidad = new Localidad();
				//localidad.setPais(pais);
				localidad.setPorvincia(provincia);
				localidad.setIdLocalidad(rs.getString("idLocalidad"));
				localidad.setDescripcionLocalidad(rs.getString("descripcionLocalidad"));
				listaLocalidades.add(localidad);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaLocalidades;
	}
	
	@Override
	public ArrayList<Localidad> leerTodasLasLocalidades() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();
		String query = "Select * from Localidades";
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Localidad localidad = new Localidad();
				//Pais p= pdao.leerPais(rs.getString("idPais"));
				Provincia prov= provdao.leerProvincia(rs.getString("idProvincia"));
				//l.setPais(p);
				localidad.setPorvincia(prov);
				localidad.setIdLocalidad(rs.getString("idLocalidad"));
				localidad.setDescripcionLocalidad(rs.getString("descripcionLocalidad"));
				listaLocalidades.add(localidad);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaLocalidades;
	}

	

}
