package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.PaisDao;
import dao.ProvinciaDao;
import entidades.Pais;
import entidades.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "lab4_tpfinal";
	
	PaisDao pdao= new PaisDaoImpl();
	
	public Provincia leerProvincia(String idProvincia) {
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from provincias WHERE idProvincia="+ idProvincia;
		Provincia prov= new Provincia();
		try{

			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				
				//Pais p= pdao.leerPais(rs.getString("idPais"));
				
				//prov.setPais(p);
				prov.setIdProvincia(rs.getString("idProvincia"));
				prov.setDescripcionProvincia(rs.getString("descripcionProvincia"));
				
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return prov;
	}

	@Override
	public ArrayList<Provincia> leerProvincias() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();;
		//Pais pais = new Pais("1000", "Argentina");
		String query = "Select * from Provincias";
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Provincia provincia = new Provincia();
				//provincia.setPais(pais);
				provincia.setIdProvincia(rs.getString("idProvincia"));
				provincia.setDescripcionProvincia(rs.getString("descripcionProvincia"));
				listaProvincias.add(provincia);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaProvincias;
	}
	
	@Override
	public ArrayList<Provincia> leerProvinciasId(String idProvincia) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Provincia> listaProvincias = new ArrayList<Provincia>();;
		//Pais pais = new Pais("1000", "Argentina");
		String query = "select * from provincias WHERE idProvincia="+ idProvincia;
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Provincia provincia = new Provincia();
				//provincia.setPais(pais);
				provincia.setIdProvincia(rs.getString("idProvincia"));
				provincia.setDescripcionProvincia(rs.getString("descripcionProvincia"));
				listaProvincias.add(provincia);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaProvincias;
	}
}
