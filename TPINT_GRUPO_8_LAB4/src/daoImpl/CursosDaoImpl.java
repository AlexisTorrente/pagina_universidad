package daoImpl;

import entidades.Alumno;
import entidades.Carrera;
import entidades.Curso;
import entidades.Docente;
import entidades.EstadoAcademico;
import entidades.Materia;
import entidades.Notas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.AlumnoDao;
import dao.CarreraDao;
import dao.CursosDao;
import dao.DocentesDao;
import dao.EstadoAcademicoDao;
import dao.MateriaDao;

public class CursosDaoImpl implements CursosDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "Lab4_TPfinal";
	
	
	MateriaDao mdao= new MateriaDaoImpl();
	CarreraDao cdao= new CarreraDaoImpl();
	DocentesDao docdao= new DocentesDaoImpl();
	AlumnoDao adao= new AlumnoDaoImpl();
	EstadoAcademicoDao eadao= new EstadoAcademicoDaoImpl();
	
	
	public Curso leerCurso(String idCurso) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from cursos WHERE idCurso="+ idCurso;

		Curso c = new Curso();
		try{

			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()) {
				
				Materia mat= mdao.leerMateria(rs.getString("idMateriaCurso"));
				Carrera car= cdao.leerCarrera(rs.getString("idCarreraCurso"));
				Docente doc= docdao.leerDocente(rs.getString("legajoDocenteCurso"));
				
				c.setIdCurso(rs.getString("idCurso"));
				c.setCarrera(car);
				c.setDocente(doc);
				c.setMateria(mat);
				c.setAnioCurso(rs.getString("anioCurso"));
				c.setSemestreCurso(rs.getString("semestreCurso"));
				
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return c;
	}
	
	@Override
	public ArrayList<Curso> leerCursosDocente(Docente d) {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from cursos WHERE legajoDocenteCurso="+ d.getLegajoDocente();

		ArrayList<Curso> lista = new ArrayList<Curso>();
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()){
				
				Curso cur = new Curso();
				
				
				Materia mat= mdao.leerMateria(rs.getString("idMateriaCurso"));
				Carrera car= cdao.leerCarrera(rs.getString("idCarreraCurso"));
				Docente doc= docdao.leerDocente(rs.getString("legajoDocenteCurso"));
				

				cur.setIdCurso(rs.getString("idCurso"));
				cur.setCarrera(car);
				cur.setDocente(doc);
				cur.setMateria(mat);
				cur.setAnioCurso(rs.getString("anioCurso"));
				cur.setSemestreCurso(rs.getString("semestreCurso"));
				lista.add(cur);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return lista;
	}
	
	@Override
	public ArrayList<Notas> leerAlumnosCurso(Curso cur) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from notas WHERE idCurso="+ cur.getIdCurso();

		
		ArrayList<Notas> lista = new ArrayList<Notas>();
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()){
				
				Notas notas = new Notas();
				
				
				Alumno alumno= adao.leerAlumno(rs.getString("legajoAlumno"));
				Curso curso= leerCurso(rs.getString("idCurso"));
				Materia materia= mdao.leerMateria(rs.getString("idMateria"));
				EstadoAcademico estAcad= eadao.leerEstadoAcademico(rs.getString("idEstadoAcademico"));
				
				notas.setCurso(curso);
				notas.setMateria(materia);
				notas.setAlumno(alumno);
				notas.setEstadoAcademico(estAcad);
				notas.setNotaPrimerParcial(rs.getFloat("notaPrimerParcial"));
				notas.setNotaSegundoParcial(rs.getFloat("notaSegundoParcial"));
				notas.setNotaPrimerRecuperatorio(rs.getFloat("notaPrimerRecuperatorio"));
				notas.setNotaSegundoRecuperatorio(rs.getFloat("notaSegundoRecuperatorio"));
				
				
				lista.add(notas);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return lista;
	}

	public boolean agregarCurso(Curso c) {
		boolean agrego=false;
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
			String query = "Insert into cursos(idCurso,idMateriaCurso,idCarreraCurso,"
					+ "legajoDocenteCurso,semestreCurso,anioCurso) "
					+ "values ('"+c.getIdCurso()+"','"+c.getMateria().getIdMateria()+
					"','"+c.getCarrera().getIdCarrera()+"','"+c.getDocente().getLegajoDocente()+
					"','"+c.getSemestreCurso()+"','"+c.getAnioCurso()+"')";
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

	public int proximoId() {
		String consulta = "SELECT MAX(idCurso) FROM cursos";
		PreparedStatement statement;
		ResultSet resultSet; 
		Conexion conexion = Conexion.getConexion();
		String ml="0"; 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(consulta);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				ml = resultSet.getString(1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		conexion.cerrarConexion();
		if(ml==null) {
			
			return 1000;
		}
		else {
			
			return Integer.parseInt(ml)+1;
		}
	}
	
	public  ArrayList<Notas> leerAlumnoLegajo(Curso cur, Alumno alu) {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			Connection conn = null;
			Statement statement;
			ResultSet rs;
			String query="select * from notas WHERE idCurso="+ cur.getIdCurso()+" and legajoAlumno= '"+ alu.getLegajoAlumno()+"'";
	
			
			ArrayList<Notas> listaAlumno = new  ArrayList<Notas>();
			
			try{
				
				conn=DriverManager.getConnection(host + dbName, user, pass);
				
				statement = conn.createStatement();
				
				rs = statement.executeQuery(query);
				
				while(rs.next()){
									
					Notas alumnoLegajo= new Notas();
					
					Alumno alumno= adao.leerAlumno(rs.getString("legajoAlumno"));
					Curso curso= leerCurso(rs.getString("idCurso"));
					Materia materia= mdao.leerMateria(rs.getString("idMateria"));
					EstadoAcademico estAcad= eadao.leerEstadoAcademico(rs.getString("idEstadoAcademico"));
					
					alumnoLegajo.setCurso(curso);
					alumnoLegajo.setMateria(materia);
					alumnoLegajo.setAlumno(alumno);
					alumnoLegajo.setEstadoAcademico(estAcad);
					alumnoLegajo.setNotaPrimerParcial(rs.getFloat("notaPrimerParcial"));
					alumnoLegajo.setNotaSegundoParcial(rs.getFloat("notaSegundoParcial"));
					alumnoLegajo.setNotaPrimerRecuperatorio(rs.getFloat("notaPrimerRecuperatorio"));
					alumnoLegajo.setNotaSegundoRecuperatorio(rs.getFloat("notaSegundoRecuperatorio"));
					listaAlumno.add(alumnoLegajo);
				}
				
				conn.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
			
			}
			return listaAlumno;
		}
	
	@Override
	public ArrayList<Notas> leerAlumnosEstadoAcademico(Curso cur, EstadoAcademico ea) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn = null;
		Statement statement;
		ResultSet rs;
		String query="select * from notas WHERE idCurso="+ cur.getIdCurso()+" and idEstadoAcademico= '"+ ea.getIdEstadoAcademico()+"'";
	
		
		ArrayList<Notas> listaAlumnos = new  ArrayList<Notas>();
		
		try{
			
			conn=DriverManager.getConnection(host + dbName, user, pass);
			
			statement = conn.createStatement();
			
			rs = statement.executeQuery(query);
			
			while(rs.next()){
								
				Notas alumnosEA= new Notas();
				
				Alumno alumno= adao.leerAlumno(rs.getString("legajoAlumno"));
				Curso curso= leerCurso(rs.getString("idCurso"));
				Materia materia= mdao.leerMateria(rs.getString("idMateria"));
				EstadoAcademico estAcad= eadao.leerEstadoAcademico(rs.getString("idEstadoAcademico"));
				
				alumnosEA.setCurso(curso);
				alumnosEA.setMateria(materia);
				alumnosEA.setAlumno(alumno);
				alumnosEA.setEstadoAcademico(estAcad);
				alumnosEA.setNotaPrimerParcial(rs.getFloat("notaPrimerParcial"));
				alumnosEA.setNotaSegundoParcial(rs.getFloat("notaSegundoParcial"));
				alumnosEA.setNotaPrimerRecuperatorio(rs.getFloat("notaPrimerRecuperatorio"));
				alumnosEA.setNotaSegundoRecuperatorio(rs.getFloat("notaSegundoRecuperatorio"));
				listaAlumnos.add(alumnosEA);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		return listaAlumnos;
	}
	
	@Override
	public ArrayList<Curso> leerCursosDocenteAnio(Docente d, String anio) {

	
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	Connection conn = null;
	Statement statement;
	ResultSet rs;
	String query="select * from cursos WHERE legajoDocenteCurso="+ d.getLegajoDocente()+" and anioCurso="+ anio;

	ArrayList<Curso> lista = new ArrayList<Curso>();
	
	try{
		
		conn=DriverManager.getConnection(host + dbName, user, pass);
		
		statement = conn.createStatement();
		
		rs = statement.executeQuery(query);
		
		while(rs.next()){
			
			Curso cur = new Curso();
			
			
			Materia mat= mdao.leerMateria(rs.getString("idMateriaCurso"));
			Carrera car= cdao.leerCarrera(rs.getString("idCarreraCurso"));
			Docente doc= docdao.leerDocente(rs.getString("legajoDocenteCurso"));
			

			cur.setIdCurso(rs.getString("idCurso"));
			cur.setCarrera(car);
			cur.setDocente(doc);
			cur.setMateria(mat);
			cur.setAnioCurso(rs.getString("anioCurso"));
			cur.setSemestreCurso(rs.getString("semestreCurso"));
			lista.add(cur);
		}
		
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
	
	}
	return lista;
}

	public boolean VerificarLegajo(Docente doc) {
boolean existe=false;
		
		String consulta = "SELECT * FROM docentes  WHERE legajodocente="+doc.getLegajoDocente() ;
		
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
				String legajoObtenido = rs.getString("legajodocente");
				if(doc.getLegajoDocente().equals(legajoObtenido)) {
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

	public boolean Verfaño(String anio) {
		int i=0;
		
		int a=Integer.parseInt(anio);
		if(a>=2021) {
		  
		         return true;
		      }
		  
		   return false;
	}
}
