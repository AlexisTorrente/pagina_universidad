package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.CarreraDao;
import entidades.Carrera;
import entidades.Materia;
import entidades.Pais;

public class CarreraDaoImpl implements CarreraDao {
	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "Lab4_TPfinal";
	
	
	@Override
	public Carrera leerCarrera(String idCarrera) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from carreras WHERE idCarrera="+ idCarrera;
		
		Carrera c= new Carrera();
		try{

			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
			
				c.setIdCarrera(rs.getString("idCarrera"));
				c.setDescripcionCarrera(rs.getString("descripcionCarrera"));
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return c;
	}


	@Override
	public ArrayList<Carrera> leerCarrera() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Carrera> listaCarrera= new ArrayList<Carrera>();
		String query = "Select * from carreras";
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Carrera c = new Carrera();
				c.setIdCarrera(rs.getString("idCarrera"));
				c.setDescripcionCarrera(rs.getString("descripcionCarrera"));
				listaCarrera.add(c);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaCarrera;
	}

}
