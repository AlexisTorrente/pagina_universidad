package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.MateriaDao;

import entidades.Materia;

public class MateriaDaoImpl implements MateriaDao {
	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "Lab4_TPfinal";
	
	public Materia leerMateria(String idMateria) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from materias WHERE idMateria="+ idMateria;
		
		Materia m= new Materia();
		try{

			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				
				m.setIdMateria(rs.getString("idMateria"));
				m.setDescripcion(rs.getString("descripcionMateria"));
				
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return m;
	}

	@Override
	public ArrayList<Materia> leerMaterias() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Materia> listaMateria= new ArrayList<Materia>();
		String query = "Select * from materias";
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Materia m = new Materia();
				m.setIdMateria(rs.getString("idMateria"));
				m.setDescripcion(rs.getString("descripcionMateria"));
				listaMateria.add(m);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaMateria;
	}
}
