package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.PaisDao;
import entidades.Pais;

public class PaisDaoImpl implements PaisDao{

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "lab4_tpfinal";
	
	@Override
	public Pais leerPais(String idPais) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from paises WHERE idPais="+ idPais;
		Pais p= new Pais();
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				
				p.setIdPais(rs.getString("idPais"));
				p.setDescripcionPais(rs.getString("nombrePais"));
				
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return p;
	}

	@Override
	public ArrayList<Pais> leerPaises() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		ArrayList<Pais> listaPaises = new ArrayList<Pais>();
		String query = "Select * from paises";
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Pais p = new Pais();
				p.setIdPais(rs.getString("idPais"));
				p.setDescripcionPais(rs.getString("nombrePais"));
				listaPaises.add(p);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaPaises;
	}

}
