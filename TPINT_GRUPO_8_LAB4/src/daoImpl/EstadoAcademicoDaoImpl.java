package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.EstadoAcademicoDao;
import entidades.EstadoAcademico;
import entidades.Materia;

public class EstadoAcademicoDaoImpl implements EstadoAcademicoDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "Lab4_TPfinal";
	
	
	@Override
	public EstadoAcademico leerEstadoAcademico(String idEstadoAcademico) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from estadoAcademico WHERE idEstadoAcademico="+ idEstadoAcademico;

		EstadoAcademico ea= new EstadoAcademico();
		try{

			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				
				ea.setIdEstadoAcademico(rs.getString("idEstadoAcademico"));
				ea.setDescripcionEstadoAcademico(rs.getString("descripcionEstadoAcademico"));
				
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return ea;
	}
	
public ArrayList<EstadoAcademico> leerTodosLosEstadosAcademicos() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from estadoAcademico ";

		ArrayList<EstadoAcademico> listaEA= new ArrayList<EstadoAcademico>();
		try{

			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				EstadoAcademico ea = new EstadoAcademico();
				
				ea.setIdEstadoAcademico(rs.getString("idEstadoAcademico"));
				ea.setDescripcionEstadoAcademico(rs.getString("descripcionEstadoAcademico"));
				listaEA.add(ea);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaEA;
	}


}
